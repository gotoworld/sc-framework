package com.hsd.framework.exception;

/**
 * @Description: 异常基类
 */
public class FeignServiceException extends RuntimeException  {
    private static final long serialVersionUID = 5692243177785821696L;

    private int error ;
    private Object data = "";

    public FeignServiceException(int error) {
        this(error, "", "");
    }

    public FeignServiceException(int error, String msg) {
        this(error, msg, "");
    }

    public FeignServiceException(int error, String msg, Object data) {
        super(msg );
        this.error = error;
        this.data = data;
    }

    public int getError() {
        return error;
    }


	public Object getData() {
		return data;
	}

}
