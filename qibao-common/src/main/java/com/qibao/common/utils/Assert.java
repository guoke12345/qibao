package com.qibao.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * 自定义断言，这里可以自定义抛出的异常类型
 */
public class Assert {

    public static <T> void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new RuntimeException(message);
        }
    }

    public static <T> void assertFalse(boolean condition, String message) {
        assertTrue(!condition, message);
    }

    public static <T> void empty(Collection<T> c, String message) throws RuntimeException {
        if (c != null && c.size() > 0) {
            throw new RuntimeException(message);
        }
    }

    public static <T> void notEmpty(Collection<T> c, String message) throws RuntimeException {
        if (c == null || c.size() == 0) {
            throw new RuntimeException(message);
        }
    }

    public static <T> void notEmpty(Collection<T> c) throws RuntimeException {
        if (c == null || c.size() == 0) {
            throw new RuntimeException();
        }
    }

    public static void notNull(Object object, String message) throws RuntimeException {
        if (object == null) {
            throw new RuntimeException(message);
        }
    }

    public static void notNull(Object object) throws RuntimeException {
        if (object == null) {
            throw new RuntimeException();
        }
    }

    public static void isNull(Object object, String message) throws RuntimeException {
        if (object != null) {
            throw new RuntimeException(message);
        }
    }

    public static void isNull(Object object) throws RuntimeException {
        if (object != null) {
            throw new RuntimeException();
        }
    }

    public static void notBlank(String text, String message) throws RuntimeException {
        if (StringUtils.isBlank(text)) {
            throw new RuntimeException(message);
        }
    }

    public static void notBlank(String text) throws RuntimeException {
        if (StringUtils.isBlank(text)) {
            throw new RuntimeException();
        }
    }
}