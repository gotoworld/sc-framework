package com.wu1g.framework.exception;

/**
 * @Description: 系统内部异常
 * @ClassName: sysException
 * @author 郝庾飞
 * @date 2016年9月17日 下午3:20:42
 *
 */
public class SysException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6878142870884828535L;

	public SysException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SysException(ErrorCode error, Object data) {
		super(error, data);
		// TODO Auto-generated constructor stub
	}

	public SysException(ErrorCode error) {
		super(error);
		// TODO Auto-generated constructor stub
	}


}
