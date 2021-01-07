/**
 * BBD Service Inc
 * All Rights Reserved @2018
 */
package com.qibao.common.exception;

/**
 * 数据不合适抛出的异常
 * 主要用于揭示客户端提交的数据有逻辑问题
 * 如主键却找不到数据
 *
 * @author Leckie
 * @version $Id: DataUnavailableException.java, v0.1 2018/3/7 13:49 Leckie Exp $$
 */
public class DataIllegalException extends BusinessException {

    private static final long serialVersionUID = -1734102074326966147L;

    public DataIllegalException() {
        super();
    }

    public DataIllegalException(String message) {
        super(message);
    }

    public DataIllegalException(Throwable cause) {
        super(cause);
    }

    public DataIllegalException(String message, Throwable cause) {
        super(message, cause);
    }

}
