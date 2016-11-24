package com.wu1g.framework.exception;

/**
 * 请求无效异常
 * Created by taoping on 2016/8/16.
 */
public class WebException extends BaseException {
    private static final long serialVersionUID = 1690624130527369260L;

	public WebException() {
		super();
	}

	public WebException(ErrorCode error, Object data) {
		super(error, data);
	}

	public WebException(ErrorCode error) {
		super(error);
	}

    

}
