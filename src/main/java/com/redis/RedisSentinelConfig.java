//package com.redis;
//
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisSentinelConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import redis.clients.jedis.JedisPoolConfig;
//import java.util.HashSet;
//import java.util.Set;
//
//@Configuration
//@PropertySource("classpath:application.properties")
//@Slf4j
//public class RedisSentinelConfig {
//
//    @Value("${spring.redis.timeout}")
//    private int timeout;
//
//    @Value("${spring.redis.jedis.pool.max-idle}")
//    private int maxIdle;
//
//    @Value("${spring.redis.jedis.pool.min-idle}")
//    private int minIdle;
//
//    @Value("${spring.redis.jedis.pool.max-wait}")
//    private int maxWaitMillis;
//
//    @Value("${spring.redis.jedis.pool.max-active}")
//    private int maxActive;
//
//    @Value("${spring.redis.database}")
//    private int database6;
//
//
////    @Bean("redis6")
////    public RedisTemplate<String, Object> getRedisTemplate6() {
////        RedisConnectionFactory factory = this.getRedisSentinelConnectionFactory(database6); // 建立Redis的连接
////        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
////        redisTemplate.setConnectionFactory(factory);
////        redisTemplate.setKeySerializer(new StringRedisSerializer()); // key的序列化类型
////        redisTemplate.setValueSerializer(new StringRedisSerializer()); // value的序列化类型
//////        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer()); // value的序列化类型
////        return redisTemplate;
////    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(factory);
//
//        // 使用Jackson2JsonRedisSerialize 替换默认的jdkSerializeable序列化
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//
//        // key采用String的序列化方式
//        template.setKeySerializer(stringRedisSerializer);
//        // hash的key也采用String的序列化方式
//        template.setHashKeySerializer(stringRedisSerializer);
//        // value序列化方式采用jackson
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        // hash的value序列化方式采用jackson
//        template.setHashValueSerializer(jackson2JsonRedisSerializer);
//        template.afterPropertiesSet();
//        return template;
//
//    }
//
//
//
////    /**
////     * @Author: zhangbuzheng
////     * @Description: 哨兵模式
////     * @param: database
////     * @Return: org.springframework.data.redis.connection.RedisConnectionFactory
////     */
////    public RedisConnectionFactory getRedisSentinelConnectionFactory(int database) { // 是负责建立Factory的连接工厂类
////        Set<String> setRedisNode = new HashSet<>();
////        setRedisNode.add("172.18.0.2:26379");
////        setRedisNode.add("172.18.0.4:26379");
////        setRedisNode.add("172.18.0.6:26379");
////        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration("mymaster", setRedisNode);
////
////        JedisPoolConfig poolConfig = new JedisPoolConfig(); // 进行连接池配置
////        poolConfig.setMaxTotal(maxActive);
////        poolConfig.setMaxIdle(maxIdle);
////        poolConfig.setMinIdle(minIdle);
////        poolConfig.setMaxWaitMillis(maxWaitMillis);
////
////        JedisConnectionFactory jedisFactory = new JedisConnectionFactory(redisSentinelConfiguration,poolConfig);
//////        jedisFactory.setHostName(host);
//////        jedisFactory.setPort(port);
//////        jedisFactory.setPassword(password);
////        jedisFactory.setDatabase(database);
////        jedisFactory.setTimeout(timeout);
////
//////        jedisFactory.setPoolConfig(poolConfig);
////        jedisFactory.afterPropertiesSet(); // 初始化连接池配置
////        return jedisFactory;
////    }
//
//
//}
