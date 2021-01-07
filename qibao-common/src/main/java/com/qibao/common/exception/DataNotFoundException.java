/**
 * BBD Service Inc
 * All Rights Reserved @2018
 */
package com.qibao.common.exception;

/**
 * @author Leckie
 * @version $Id: DataNotFoundException.java, v0.1 2018/3/7 13:52 Leckie Exp $$
 */
public class DataNotFoundException extends DataIllegalException {

    private static final long serialVersionUID = -1734102074326933337L;

    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(Throwable cause) {
        super(cause);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
