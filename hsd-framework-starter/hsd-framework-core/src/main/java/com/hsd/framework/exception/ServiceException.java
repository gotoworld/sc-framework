package com.hsd.framework.exception;

import com.hsd.framework.util.CommonConstant;

public class ServiceException extends BaseException {
    private static final long serialVersionUID = 1690624130527369260L;

	public ServiceException() {
		super();
	}

	public ServiceException(ErrorCode error, String msg) {
		super(error, error.getCode() + CommonConstant.FEIGN_ERROR_SYMBOL_STRING + msg);
	}

	public ServiceException(ErrorCode error) {
		super(error, error.getCode() + CommonConstant.FEIGN_ERROR_SYMBOL_STRING);
	}
}
