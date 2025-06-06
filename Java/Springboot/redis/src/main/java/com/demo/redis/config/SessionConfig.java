package com.demo.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.*;

/*
* 会话配置类:配置基于redis的会话存储仓库
* */
@Configuration
public class SessionConfig {
    @Bean
    public RedisOperations<String, Object> redisOperations(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisIndexedSessionRepository sessionRepository(RedisOperations<String, Object> redisOperations) {
        RedisIndexedSessionRepository sessionRepository = new RedisIndexedSessionRepository(redisOperations);
        // 可设置会话超时时间，单位为秒
        // 注意：setDefaultMaxInactiveInterval 自 3.0.0 起已弃用，后续需要替换
        sessionRepository.setDefaultMaxInactiveInterval(1800);
        return sessionRepository;
    }
}
