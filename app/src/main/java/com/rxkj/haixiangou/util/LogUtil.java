package com.rxkj.haixiangou.util;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

public class LogUtil {

  private static final String DEFAULT_TAG = "com.rxkj.haixiangou";

  static {
    Logger.init(DEFAULT_TAG)                 // default PRETTYLOGGER or use just init()
        //.methodCount(2)                 // default 2 配置调用方法输出数量
        //.hideThreadInfo()               // default shown 是否显示thread信息
        .logLevel(LogLevel.FULL)       // default LogLevel.FULL
        .methodOffset(1);            // default 0 1是去掉当前方法的调用
  }

  /** 日志总开关 **/
  private static boolean IS_DEBUGING = BaseUriUtil.VERSION_URI_TYPE != BaseUriUtil.VERSION_RELEASE;

  //通过chrome调试开关
  private static boolean CHROME_DEBUG = true;

  //json日志开关
  private static boolean JSON_DEBUG = true;

  private static boolean IS_JSON_DEBUG = IS_DEBUGING && JSON_DEBUG;

  private static boolean IS_CHROME_DEBUG = IS_DEBUGING && CHROME_DEBUG;

  // logcat level
  private static int LOGCAT_LEVEL = (IS_DEBUGING) ? 2 : 32;

  private final static int LOG_LEVEL_ERROR = 16;
  private final static int LOG_LEVEL_WARN = 8;
  private final static int LOG_LEVEL_INFO = 4;
  private final static int LOG_LEVEL_DEBUG = 2;

  private final static int LOG_LEVEL_VERBOSE = 0;
  private static boolean VERBOSE = (LOGCAT_LEVEL <= LOG_LEVEL_VERBOSE);
  private static boolean DEBUG = (LOGCAT_LEVEL <= LOG_LEVEL_DEBUG);
  private static boolean INFO = (LOGCAT_LEVEL <= LOG_LEVEL_INFO);
  private static boolean WARN = (LOGCAT_LEVEL <= LOG_LEVEL_WARN);
  private static boolean ERROR = (LOGCAT_LEVEL <= LOG_LEVEL_ERROR);

  public static boolean isDebug() {
    return IS_DEBUGING;
  }

  public static boolean isDebugChrome() {
    return IS_CHROME_DEBUG;
  }

  public static boolean isDebugJson() {
    return IS_JSON_DEBUG;
  }


  /**
   * 格式化输出日志
   * @param msg
   */
  public static void d(String msg) {
    if (DEBUG) {
      Logger.log(Logger.DEBUG, DEFAULT_TAG, msg, null);
    }
  }

  /**
   * 格式化输出日志
   */
  public static void d(String tag, String msg) {
    if (DEBUG) {
      Logger.log(Logger.DEBUG, tag, msg, null);
    }
  }

  /**
   * 格式化输出日志
   */
  public static void d(String tag, String msg, Throwable error) {
    if (DEBUG) {
      Logger.log(Logger.DEBUG, tag, msg, error);
    }
  }

  /**
   * 格式化输出日志
   * @param msg
   */
  public static void v(String msg) {
    if (VERBOSE) {
      Logger.log(Logger.VERBOSE, DEFAULT_TAG, msg, null);
    }
  }

  /**
   * 格式化输出日志
   * @param tag
   * @param msg
   */
  public static void v(String tag, String msg) {
    if (VERBOSE) {
      Logger.log(Logger.VERBOSE, tag, msg, null);
    }
  }

  /**
   * 格式化输出日志
   * @param tag
   * @param msg
   * @param error
   */
  public static void v(String tag, String msg, Throwable error) {
    if (VERBOSE) {
      Logger.log(Logger.VERBOSE, tag, msg, error);
    }
  }

  /**
   * 格式化输出日志
   * @param msg
   */
  public static void i(String msg) {
    if (INFO) {
      Logger.log(Logger.INFO, DEFAULT_TAG, msg, null);
    }
  }

  /**
   * 格式化输出日志
   */
  public static void i(String tag, String msg) {
    if (INFO) {
      Logger.log(Logger.INFO, tag, msg, null);
    }
  }

  /**
   * 格式化输出日志
   * @param tag
   * @param msg
   * @param error
   */
  public static void i(String tag, String msg, Throwable error) {
    if (INFO) {
      Logger.log(Logger.INFO, tag, msg, error);
    }
  }

  /**
   * 格式化输出日志
   * @param msg
   */
  public static void w(String msg) {
    if (WARN) {
      Logger.log(Logger.WARN, DEFAULT_TAG, msg, null);
    }
  }

  /**
   * 格式化输出日志
   * @param tag
   * @param msg
   */
  public static void w(String tag, String msg) {
    if (WARN) {
      Logger.log(Logger.WARN, tag, msg, null);
    }
  }

  /**
   * 格式化输出日志
   * @param tag
   * @param msg
   * @param error
   */
  public static void w(String tag, String msg, Throwable error) {
    if (WARN) {
      Logger.log(Logger.WARN, tag, msg,error);
    }
  }

  /**
   * 格式化输出日志
   * @param msg
   */
  public static void e(String msg) {
    if (ERROR) {
      Logger.log(Logger.ERROR, DEFAULT_TAG, msg,null);
    }
  }

  /**
   * 格式化输出日志
   * @param tag
   * @param msg
   */
  public static void e(String tag, String msg) {
    if (ERROR) {
      Logger.log(Logger.ERROR, tag, msg,null);
    }
  }

  /**
   * 格式化输出日志
   * @param tag
   * @param msg
   * @param error
   */
  public static void e(String tag, String msg, Throwable error) {
    if (ERROR) {
      Logger.log(Logger.ERROR, tag, msg,error);
    }
  }

  /**
   * 格式化输出json日志
   * @param msg
   */
  public static void json(String msg) {
    if (DEBUG) {
      Logger.json(msg);
    }
  }

  /**
   * 格式化输出xml日志
   */
  public static void xml(String msg) {
    if (DEBUG) {
      Logger.xml(msg);
    }
  }
}
