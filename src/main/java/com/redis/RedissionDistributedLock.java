package com.redis;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

public class RedissionDistributedLock {

   @Autowired
    RedissonClient redissonClient;

   public void getLock(){

       RLock lock = redissonClient.getLock("lock");

       lock.lock();

       //todo 业务代码

       lock.unlock();

   }


}
