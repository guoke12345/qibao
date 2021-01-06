package com.qibao.core.common.exception;

public class SystemException extends RuntimeException {

	/**
	 * @Fields serialVersionUID :
	*/ 
	private static final long serialVersionUID = -6841604109197328263L;
	

	public SystemException(String message) {
		super(message,new Throwable(message));
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

}