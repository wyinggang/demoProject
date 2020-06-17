//package com.redis;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.*;
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//
//@Configuration
//@EnableCaching
//public class RedisConfig extends CachingConfigurerSupport {
//
//        /**
//         * 配置自定义redisTemplate
//         *
//         * @param connectionFactory
//         * @return
//         */
//        @Bean
//        public RedisTemplate<String, Object> redisTemplate (RedisConnectionFactory connectionFactory){
//            RedisTemplate<String, Object> template = new RedisTemplate<>();
//            template.setConnectionFactory(connectionFactory);
//            //使用StringRedisSerializer来序列化和反序列化redis的key值
//            template.setKeySerializer(new StringRedisSerializer());
//            template.setHashKeySerializer(new StringRedisSerializer());
//            template.setValueSerializer(jackson2JsonRedisSerializer());
//            template.setHashValueSerializer(jackson2JsonRedisSerializer());
//            //初始化连接池配置
//            template.afterPropertiesSet();
//            return template;
//        }
//
//        /**
//         * json序列化
//         * @return
//         */
//        @Bean
//        public RedisSerializer<Object> jackson2JsonRedisSerializer () {
//            //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
//            Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
//
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//            mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//            serializer.setObjectMapper(mapper);
//            return serializer;
//        }
//
//        /**
//         * 配置缓存管理器
//         * @param redisConnectionFactory
//         * @return
//         */
//        @Bean
//        public CacheManager cacheManager (RedisConnectionFactory redisConnectionFactory){
//            // 生成一个默认配置，通过config对象即可对缓存进行自定义配置
//            RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
//            // 设置缓存的默认过期时间，也是使用Duration设置
//            config = config.entryTtl(Duration.ofMinutes(1))
//                    // 设置 key为string序列化
//                    .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
//                    // 设置value为json序列化
//                    .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer()))
//                    // 不缓存空值
//                    .disableCachingNullValues();
//
//            // 设置一个初始化的缓存空间set集合
//            Set<String> cacheNames = new HashSet<>();
//            cacheNames.add("timeGroup");
//            cacheNames.add("user");
//
//            // 对每个缓存空间应用不同的配置
//            Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
//            configMap.put("timeGroup", config);
//            configMap.put("user", config.entryTtl(Duration.ofSeconds(120)));
//
//            // 使用自定义的缓存配置初始化一个cacheManager
//            RedisCacheManager cacheManager = RedisCacheManager
//                    .builder(redisConnectionFactory)
//                    // 一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
//                    .initialCacheNames(cacheNames)
//                    .withInitialCacheConfigurations(configMap)
//                    .build();
//            return cacheManager;
//        }
//
//        /**
//         * 缓存的key是 包名+方法名+参数列表
//         */
//        @Bean
//        public KeyGenerator keyGenerator () {
//            return (target, method, objects) -> {
//                StringBuilder sb = new StringBuilder();
//                sb.append(target.getClass().getName());
//                sb.append("::" + method.getName() + ":");
//                for (Object obj : objects) {
//                    sb.append(obj.toString());
//                }
//                return sb.toString();
//            };
//        }
//
//
//}
