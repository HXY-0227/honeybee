package com.honeybee.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 * @author HXY
 * @version 1.0
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    // 默认过期时间 单位：秒
    public static final long DEFAULT_EXPIRE_TIME = 24 * 60 * 60;

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
     * @return values
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
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * get
     * @param key key
     * @return value
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * mGet
     * @param keys keys
     * @return values
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

    /**************************Hash的操作**************************/

    /**
     * hSet
     * @param key key
     * @param field field
     */
    public void hSet(String key, Object field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * hmSet
     * @param key key
     * @param field field-value
     */
    public void hmSet(String key, Map<String, String> field) {
        redisTemplate.opsForHash().putAll(key, field);
    }

    /**
     * hGet
     * @param key key
     * @param field field
     * @return value
     */
    public Object hGet(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * hGetAll
     * @param key key
     * @param fields fields
     * @return values
     */
    public List<Object> hGetAll(String key, Collection<Object> fields) {
        return redisTemplate.opsForHash().multiGet(key, fields);
    }

    /**
     * hKeys
     * @param key
     * @return fields
     */
    public Set<Object> hKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * hVals
     * @param key key
     * @return values
     */
    public List<Object> hVals(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * hExists
     * @param key key
     * @param field field
     * @return 是否存在
     */
    public boolean hExists(String key, Object field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * hDel
     * @param key key
     */
    public void hDel(String key, Object... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * hLen
     * @param key key
     * @return length
     */
    public Long hLen(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    /**************************List的操作**************************/

    /**
     * lpush
     * @param key key
     * @param value value
     * @return 执行命令完毕后列表长度
     */
    public Long lPush(String key, Object... value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }
    /**
     * lpush
     * @param key key
     * @param values values
     * @return 执行命令完毕后列表长度
     */
    public Long lPush(String key, Collection<Object> values) {
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * lSet
     * @param key key
     * @param index 索引
     * @param value value
     */
    public void lSet(String key, Long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * lIndex
     * @param key key
     * @param index index
     * @return 元素值
     */
    public Object lIndex(String key, Long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * lRange
     * @param key key
     * @param start 开始索引
     * @param end 结束索引
     * @return 指定范围的元素列表
     */
    public List<Object> lRange(String key, Long start, Long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * lRem
     * @param key key
     * @param count
     *        count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count
     *        count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值
     *        count = 0 : 移除表中所有与 value 相等的值
     * @param value value
     * @return 移除元素的数量
     */
    public Long lRem(String key, long count, String value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * lLen
     * @param key key
     * @return length
     */
    public Long lLen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**************************Set的操作**************************/



}
