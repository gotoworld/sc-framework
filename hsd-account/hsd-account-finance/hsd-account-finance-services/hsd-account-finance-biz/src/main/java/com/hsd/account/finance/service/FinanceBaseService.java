package com.hsd.account.finance.service;

import com.hsd.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class FinanceBaseService extends BaseService {
    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;
}
