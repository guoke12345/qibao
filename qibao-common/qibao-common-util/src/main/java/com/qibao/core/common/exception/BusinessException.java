package com.qibao.core.common.exception;

/**
 * Created by giozola on 2017/7/7.
 */
public class BusinessException extends RuntimeException{
    public BusinessException(String str){
        super(str,new Throwable(str));
    }
    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
