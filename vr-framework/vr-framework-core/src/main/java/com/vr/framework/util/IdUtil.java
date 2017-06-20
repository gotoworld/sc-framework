package com.vr.framework.util;


public class IdUtil {

	/**
	 * <p>
	 * 生成UUID主键
	 * @param len 位数
	 * @return 主键
	 */
	public static String createUUID(int len) {

		String uuid = java.util.UUID.randomUUID().toString()
				.replaceAll("-", "");
		return uuid.substring(0, len);
	}
}
