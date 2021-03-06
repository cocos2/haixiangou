/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rxkj.haixiangou.net.rxadapter;

import com.rxkj.haixiangou.model.BaseResult;
import com.rxkj.haixiangou.net.callback.ResponseFilter;
import com.rxkj.haixiangou.net.callback.ResponseFilterImpl;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.subscriptions.Subscriptions;

public final class LinkRxCallAdapterFactory extends CallAdapter.Factory {

  public static LinkRxCallAdapterFactory create() {
    return new LinkRxCallAdapterFactory();
  }

  private LinkRxCallAdapterFactory() {
  }

  @Override
  public CallAdapter<?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {

    Class<?> rawType = getRawType(returnType);
    boolean isSingle = "rx.Single".equals(rawType.getCanonicalName());
    if (rawType != Observable.class && !isSingle) {
      return null;
    }
    if (!(returnType instanceof ParameterizedType)) {
      String name = isSingle ? "Single" : "Observable";
      throw new IllegalStateException(
          name + " return type must be parameterized" + " as " + name + "<Foo> or " +
              name + "<? extends Foo>");
    }

    CallAdapter<Observable<?>> callAdapter = getCallAdapter(returnType);
    if (isSingle) {
      return SingleHelper.makeSingle(callAdapter);
    }
    return callAdapter;
  }

  private CallAdapter<Observable<?>> getCallAdapter(Type returnType) {

    Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);

    return new SimpleCallAdapter(observableType);
  }

  static final class CallOnSubscribe<T> implements Observable.OnSubscribe<Response<T>> {

    private final Call<T> originalCall;

    private ResponseFilter<T> mFilter;

    CallOnSubscribe(Call<T> originalCall) {
      this.originalCall = originalCall;
    }

    public void setFilter(ResponseFilter<T> filter) {
      mFilter = filter;
    }

    @Override public void call(final Subscriber<? super Response<T>> subscriber) {
      final Call<T> call = originalCall.clone();

      subscriber.add(Subscriptions.create(new Action0() {
        @Override public void call() {
          call.cancel();
        }
      }));

      try {
        Response<T> response = call.execute();
        if (!subscriber.isUnsubscribed()) {
          if (mFilter != null) {
            mFilter.doFilter(response.body());
          }
          subscriber.onNext(response);
        }
      } catch (Throwable t) {
        Exceptions.throwIfFatal(t);
        if (!subscriber.isUnsubscribed()) {
          subscriber.onError(t);
        }
        return;
      }

      if (!subscriber.isUnsubscribed()) {
        subscriber.onCompleted();
      }
    }
  }

  static final class SimpleCallAdapter implements CallAdapter<Observable<?>> {

    private final Type responseType;

    SimpleCallAdapter(Type responseType) {
      this.responseType = responseType;
    }

    @Override public Type responseType() {
      return responseType;
    }

    @Override public <R> Observable<R> adapt(Call<R> call) {
      CallOnSubscribe callOnSubscribe = new CallOnSubscribe<>(call);
      ResponseFilter<BaseResult> filter = new ResponseFilterImpl();
      callOnSubscribe.setFilter(filter);
      return Observable.create(callOnSubscribe).flatMap(new Func1<Response<R>, Observable<R>>() {
        @Override public Observable<R> call(Response<R> response) {

          Integer code = response.code();

          if (response.isSuccessful() && code != 204 && code != 205) {
            return Observable.just(response.body());
          }
          return Observable.error(new RetrofitHttpException(response));
        }
      });
    }
  }
}
