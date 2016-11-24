package com.wu1g.framework.exception;

/**
 * @Description: 抽象和扩展Exception信息
 * @ClassName: ExceptionScalable
 * @author 郝庾飞
 * @date 2016年9月14日 下午8:30:04
 *
 */
public interface ExceptionScalable{

	/**
	 * 错误对象
	 * @return
	 */
	ErrorCode getError();

	/**
	 * 引起执行异常的数据, 如输入数据，或执行语句等
	 * @return
     */
	Object getData();
}
