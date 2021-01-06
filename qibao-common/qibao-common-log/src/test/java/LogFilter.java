package com.qibao.common.log;

import com.qibao.core.util.UUIDGenerator;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * spring boot 日志拦截器 time key 配置
 */

public class LogFilter implements HandlerInterceptor {
    public static final String KEY_PREFIX          = "【";
    public static final String KEY_SUFFIX          = "】";
    public static void start() {
        MDC.remove("timeKey");
        MDC.put("timeKey",createTimeKey());
    }

    public static String createTimeKey() {
        String timeKey = KEY_PREFIX + UUIDGenerator.getUUIDSlip() + KEY_SUFFIX;
        return timeKey;
    }

    @Override
    public  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        start();
        return true;
    }
}
