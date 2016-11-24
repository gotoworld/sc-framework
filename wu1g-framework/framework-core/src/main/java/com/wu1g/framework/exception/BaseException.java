/**
 *
 */
package com.wu1g.framework.exception;

/**
 * @Description: 异常基类
 * @ClassName: BaseException
 * @author 郝庾飞
 * @date 2016年9月17日 上午11:55:09
 *
 */
public abstract class BaseException extends RuntimeException implements ExceptionScalable {
    private static final long serialVersionUID = 5692243177785821696L;

    private ErrorCode error;
    private Object data = "";

    public BaseException() {
    }

    public BaseException(ErrorCode error) {
        this.error = error;
    }

    public BaseException(ErrorCode error, Object data) {
        this.error = error;
        this.data = data;
    }
    
    public BaseException(ErrorCode error, String msg, Object data) {
    	this.error = error;
    	this.error.setMessage(msg);
    	this.data = data;
    }

    @Override
    public ErrorCode getError() {
        return error;
    }

	@Override
	public Object getData() {
		return data;
	}

}
