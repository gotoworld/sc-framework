package com.hsd.framework.cache;

import com.hsd.Application;
import com.hsd.framework.cache.util.IdGeneratorIsRedis;
import com.hsd.framework.cache.util.RedisHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class RedisHelperTest {
    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private IdGeneratorIsRedis idGeneratorIsRedis;

    @Test
    public void testRedisCluster(){
        redisHelper.set("name2","testName1");
        for (int i=0;i<1000;i++)
        System.out.println(redisHelper.get("name2"));
    }

    @Test
    public void testIdGenerator(){
        for (int i=0;i<1000;i++)
        System.out.println(idGeneratorIsRedis.nextId());
    }
}
