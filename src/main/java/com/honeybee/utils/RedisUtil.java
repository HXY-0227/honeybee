package com.honeybee.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 * @author HXY
 * @version 1.0
 */
public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**************************key的操作**************************/

    /**
     * del
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * del
     * @param keys key集合
     */
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * exists
     * @param key key
     * @return 判断结果
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * expire
     * @param key key
     * @param timeout 过期时间
     * @param unit 时间粒度
     * @return 结果
     */
    public boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * ttl
     * @param key key
     * @return 结果
     */
    public Long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * persist
     * @param key
     * @return 结果
     */
    public boolean persist(String key) {
        return redisTemplate.persist(key);
    }

    /**
     * keys
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**************************String的操作**************************/

    /**
     * set
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * get
     * @param key key
     * @return value
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * mGet
     * @param keys key集合
     * @return value集合
     */
    public List<String> mGet(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * getRange
     * @param key key
     * @param start 起始偏移量
     * @param end 结束偏移量
     * @return value
     */
    public String getRange(String key, long start, long end) {
        return redisTemplate.opsForValue().get(key, start, end);
    }

    /**
     * strLen
     * @param key key
     * @return 长度
     */
    public Long strLen(String key) {
        return redisTemplate.opsForValue().size(key);
    }

    /**
     * incrby
     * @param key key
     * @param increment 增长量
     * @return 增长后的value
     */
    public Long incrBy(String key, long increment) {
        return redisTemplate.opsForValue().increment(key, increment);
    }

    /**
     * append
     * @param key
     * @param value
     * @return
     */
    public Integer append(String key, String value) {
        return redisTemplate.opsForValue().append(key, value);
    }
}
