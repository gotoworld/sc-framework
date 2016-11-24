package com.wu1g.framework.exception;

/**
 * @Description: 请求超时
 * @ClassName: RequestOutTime
 * @author 郝庾飞
 * @date 2016年9月17日 下午3:30:38
 *
 */
public class RequestTimeOutException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6634469533564245709L;

	public RequestTimeOutException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RequestTimeOutException(ErrorCode error, Object data) {
		super(error, data);
		// TODO Auto-generated constructor stub
	}

	public RequestTimeOutException(ErrorCode error) {
		super(error);
		// TODO Auto-generated constructor stub
	}
	

}
