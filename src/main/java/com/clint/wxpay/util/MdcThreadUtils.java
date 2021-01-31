package com.clint.wxpay.util;

import lombok.experimental.UtilityClass;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Clint
 * Date: 2020-09-18 16:39
 * Description: 针对子线程日志丢失traceId, 对线程进行包装
 */
@UtilityClass
public class MdcThreadUtils {
  /**
   * log MDC 中的key值
   */
  String TRACE_ID = "traceId";

  public String traceId() {
    StringBuilder uid = new StringBuilder();
    for (int i = 0; i < 16; i++) {
      uid.append(Integer.toHexString(ThreadLocalRandom.current().nextInt(16)));
    }
    return uid.toString();
  }

  public void setTraceIdIfAbsent() {
    if (MDC.get(TRACE_ID) == null) {
      MDC.put(TRACE_ID, traceId());
    }
  }

  public <T> Callable<T> wrap(final Callable<T> callable) {
    final Map<String, String> context = MDC.getCopyOfContextMap();
    return () -> {
      if (context == null) {
        MDC.clear();
      } else {
        MDC.setContextMap(context);
      }
      setTraceIdIfAbsent();
      try {
        return callable.call();
      } finally {
        MDC.clear();
      }
    };
  }

  public Runnable wrap(final Runnable runnable) {
    final Map<String, String> context = MDC.getCopyOfContextMap();
    return () -> {
      if (context == null) {
        MDC.clear();
      } else {
        MDC.setContextMap(context);
      }
      setTraceIdIfAbsent();
      try {
        runnable.run();
      } finally {
        MDC.clear();
      }
    };
  }

}
