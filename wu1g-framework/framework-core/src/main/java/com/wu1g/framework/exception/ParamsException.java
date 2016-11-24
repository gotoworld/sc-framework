package com.wu1g.framework.exception;

/**
 * @Description: 参数异常
 * @ClassName: ParamsException
 * @author 郝庾飞
 * @date 2016年9月17日 下午3:32:55
 *
 */
public class ParamsException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7137006668984255381L;

	public ParamsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParamsException(ErrorCode error, Object data) {
		super(error, data);
		// TODO Auto-generated constructor stub
	}

	public ParamsException(ErrorCode error) {
		super(error);
		// TODO Auto-generated constructor stub
	}

	
}
