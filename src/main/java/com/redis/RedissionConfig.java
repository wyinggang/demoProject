package com.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissionConfig {

//    @Bean
//    public RedissonClient getClient() {
//
//        Config config = new Config();
//
//        config.useSingleServer().setAddress("49.235.60.52:6379");
//
//        RedissonClient redisson = Redisson.create(config);
//
//        return redisson;
//    }

}
