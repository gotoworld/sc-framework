package com.hsd.framework.cache;

public class CacheException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public CacheException(String info) {
		super(info);
	}

	public CacheException(Exception e) {
		super(e);
	}

}
