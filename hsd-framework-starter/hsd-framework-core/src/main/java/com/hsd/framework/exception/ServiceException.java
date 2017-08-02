package com.hsd.framework.exception;

public class ServiceException extends BaseException {
    private static final long serialVersionUID = 1690624130527369260L;

	public ServiceException() {
		super();
	}

	public ServiceException(ErrorCode error, String msg) {
		super(error, error.getCode() + ":" + msg);
	}

	public ServiceException(ErrorCode error) {
		super(error, error.getCode() + ":");
	}
	
	public ServiceException(ErrorCode error, String msg, Object data) {
		super(error, error.getCode() + ":" + msg, data);
	}
}
