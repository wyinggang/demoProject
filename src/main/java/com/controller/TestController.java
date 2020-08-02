package com.controller;

import com.aop.Monitoring;
import com.redis.DistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("api/v1")
public class TestController {

    @Autowired  private  RedisTemplate redisTemplate;
    @Autowired  private  DistributedLock distributedLock;


    @Monitoring(uri = "/test",method = "TsetMethod")
    @RequestMapping("/test/{value}")
    public String TsetMethod(@PathVariable("value")String value){

//        redisTemplate.opsForZSet().add("sortedSet","java",1);
//        redisTemplate.opsForZSet().add("sortedSet","python",2);
        boolean key = distributedLock.getLock("distributedLockKey", value, 20);
        System.out.println(key?"获取锁成功":"获取锁失败");
        redisTemplate.opsForValue().set("test","testValue");
        System.out.println("执行业务代码");
        boolean b = distributedLock.releaseLock("distributedLockKey", value);
        System.out.println(b?"释放锁成功":"释放锁失败");
        return "finish";
    }

    @GetMapping("/testCache/{value}")
    @Cacheable(value = "testCache",key = "#value")
    public String TestCache(@PathVariable("value")String value){
        System.out.println("11111111111");
        return "success";
    }

}
