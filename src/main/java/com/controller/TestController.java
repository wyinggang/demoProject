package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class TestController {

    @Autowired  private  RedisTemplate redisTemplate;

    @RequestMapping("/test")
    public String TsetMethod(){

        redisTemplate.opsForValue().set("test","testValue");
        redisTemplate.opsForZSet().add("sortedSet","java",1);
        redisTemplate.opsForZSet().add("sortedSet","python",2);
        return "index";
    }


    @GetMapping("/testCache/{value}")
    @Cacheable(value = "testCache",key = "#value")
    public String TestCache(@PathVariable("value")String value){
        System.out.println("11111111111");
        return "success";
    }




}
