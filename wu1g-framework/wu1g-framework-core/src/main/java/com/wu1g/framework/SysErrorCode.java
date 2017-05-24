package com.wu1g.framework;


import com.wu1g.framework.exception.ErrorCode;

/**
 * Created by Administrator on 16-9-14.
 */
public enum SysErrorCode implements ErrorCode {

	SUCCESS(0, "操作成功!"), 
	REQ_ERROR(400, "请求错误!"), 
	SERVICE_ERROR(500, "内部服务错误!"), 
	AUTH_FAIL(401, "授权失败或超时,请登录访问!"),
	TIMEOUT(408, "请求超时!"),
	REQ_PARAMS_ERROR(406, "请求参数错误!"),

    ONE_LOGIN(420, "您已经在另一台手机登录"),

    ;
    private Integer code;
    private String message;

    SysErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    SysErrorCode(Integer code) {
        this.code = code;
    }
    
    public static String getMsg(int code) {
    	for(SysErrorCode errorCode : SysErrorCode.values()) {
    		if(code == errorCode.getCode()) {
    			return errorCode.getMessage();
    		}
    	}
    	return null;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

	@Override
	public void setMessage(String msg) {
		this.message = msg;
	}

}
