package com.rxkj.haixiangou.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionUtils {

  @SuppressWarnings("rawtypes") public static boolean isEmpty(Collection collection) {
    return collection == null || collection.isEmpty();
  }

  @SuppressWarnings("rawtypes") public static boolean isNotEmpty(Collection collection) {
    return !CollectionUtils.isEmpty(collection);
  }

  @SuppressWarnings({ "rawtypes", "unchecked" }) public static List[] split(List list, int num) {
    List[] lists = null;
    if (isNotEmpty(list) && num > 0) {
      int flag = list.size() % num == 0 ? 0 : 1;
      int count = list.size() / num + flag;
      lists = new ArrayList[count];

      for (int i = 0; i < count; i++) {
        int start = num * i;
        int end = num * (i + 1);
        if (i + 1 == count) {
          end = list.size();
        }
        lists[i] = new ArrayList(list.subList(start, end));
      }
    }
    return lists;
  }

  public static <T> List<T> asList(T[] array) {
    return asList(0, array);
  }

  public static <T> List<T> asList(int startPos, T[] array) {
    ArrayList<T> list = new ArrayList<>();
    if (array != null) {
      for (int i = startPos; i < array.length; i++) {
        list.add(array[i]);
      }
    }

    return list;
  }

  public static <T> List<T> checkListNull(List<T> list) {
    if (list == null) {
      list = new ArrayList<T>();
    }
    return list;
  }
}
