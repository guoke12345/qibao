/**
 * BBD Service Inc
 * All Rights Reserved @2018
 */
package com.qibao.common.exception;

/**
 * @author Leckie
 * @version $Id: BusinessException.java, v0.1 2018/3/7 13:46 Leckie Exp $$
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -9063608132331602495L;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause, false, false);
    }

    /**
     * 业务异常无需深入记录堆栈信息
     * 重写fillInStackTrace方法降低开销
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
