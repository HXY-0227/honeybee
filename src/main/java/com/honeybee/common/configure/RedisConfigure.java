package com.honeybee.common.configure;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis配置类
 * @author HXY
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)  //redis自动配置
public class RedisConfigure extends CachingConfigurerSupport {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 配置连接工厂
        redisTemplate.setConnectionFactory(factory);
        // key的序列化类型
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        // PropertyAccessor.ALL指定要序列化的域，field,get和set
        // JsonAutoDetect.Visibility.ANY表示修饰符范围，ANY是包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        /**
         * 序列化策略：
         * 1.  默认使用JdkSerializationRedisSerializer序列化，存储的是字节数组，无法阅读，但空间小
         * 2.  StringRedisSerializer：Key或者value为字符串的场景，对：new String(bytes, charset)和string.getBytes(charset)进行封装。轻量级和高效的策略。
         * 3.  JacksonJsonRedisSerializer：将java对象序列化成json格式存储在redis中，也可以将json格式的数据转换成java对象。
         * 4.  jackson2JsonRedisSerializer： 和JacksonJsonRedisSerializer差不多
         * 5.  OxmSerializer：提供了将javabean与xml之间的转换能力
         *
         */
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        return redisTemplate;
    }

}
