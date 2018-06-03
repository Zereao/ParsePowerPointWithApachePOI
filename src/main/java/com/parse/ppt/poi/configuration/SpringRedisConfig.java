package com.parse.ppt.poi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @author Jupiter
 * @version 2018/05/28/11:30
 */
//@Configuration
//@Lazy
public class SpringRedisConfig {
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.pass}")
    private String password;
    @Value("${redis.usePool}")
    private String usePool;
    @Value("${redis.maxTotal}")
    private int maxTotal;
    @Value("${redis.maxIdle}")
    private int maxIdle;
    @Value("${redis.blackWhenExhausted}")
    private boolean blackWhenExhausted;
    @Value("${redis.maxWaitMillis}")
    private long maxWaitMillis;
    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;

    // 连接池配置
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setBlockWhenExhausted(blackWhenExhausted);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        return jedisPoolConfig;
    }

    // Redis 链接密码配置
    @Bean
    public RedisPassword redisPassword() {
        return RedisPassword.of(password);
    }

    // 连接配置
    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(host);
        configuration.setPassword(redisPassword());
        configuration.setPort(port);
        return configuration;
    }

    // 客户端配置     这里，着重理解！！！！！！！！
    @Bean
    public JedisClientConfiguration jedisClientConfiguration() {
        return JedisClientConfiguration.builder()
                .usePooling().poolConfig(jedisPoolConfig())
                .and()
                .readTimeout(Duration.ZERO).connectTimeout(Duration.ZERO)
                .build();
    }

    // 连接工厂
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory(redisStandaloneConfiguration(), jedisClientConfiguration());
    }

    // RedisTemplate
    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }

}
