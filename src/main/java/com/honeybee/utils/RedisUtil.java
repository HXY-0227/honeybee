package com.honeybee.utils;

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
     * DEL
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * DEL
     * @param keys key集合
     */
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * EXISTS
     * @param key key
     * @return 判断结果
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * EXPIRE
     * @param key key
     * @param timeout 过期时间
     * @param unit 时间粒度
     * @return 结果
     */
    public boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * TTL
     * @param key key
     * @return 结果
     */
    public Long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * PERSIST
     * @param key
     * @return 结果
     */
    public boolean persist(String key) {
        return redisTemplate.persist(key);
    }

    /**
     * KEYS
     * @param pattern
     * @return 获取所有的key
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**************************String的操作**************************/

    /**
     * SET
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * SETNX
     * @param key key
     * @param value value
     * @return 是否设置成功
     */
    public boolean setNx(String key, Object value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * GET
     * @param key key
     * @return 返回给定的key值
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key).toString();
    }

    /**
     * MGET
     * @param keys keys
     * @return 返回所有给定的key值
     */
    public List<String> mGet(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * GETRANGE
     * @param key key
     * @param start 起始偏移量
     * @param end 结束偏移量
     * @return 截取到的子字符串
     */
    public String getRange(String key, long start, long end) {
        return redisTemplate.opsForValue().get(key, start, end);
    }

    /**
     * STRLEN
     * @param key key
     * @return 长度
     */
    public Long strLen(String key) {
        return redisTemplate.opsForValue().size(key);
    }

    /**
     * INCRBY
     * @param key key
     * @param increment 增长量
     * @return 增长后的value
     */
    public Long incrBy(String key, long increment) {
        return redisTemplate.opsForValue().increment(key, increment);
    }

    /**
     * APPEND
     * @param key
     * @param value
     * @return 追加value后字符串的长度
     */
    public Integer append(String key, String value) {
        return redisTemplate.opsForValue().append(key, value);
    }

    /**************************Hash的操作**************************/

    /**
     * HSET
     * @param key key
     * @param field field
     */
    public void hSet(String key, Object field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * HMSET
     * @param key key
     * @param field field-value
     */
    public void hmSet(String key, Map<String, String> field) {
        redisTemplate.opsForHash().putAll(key, field);
    }

    /**
     * HGET
     * @param key key
     * @param field field
     * @return 给定key对应的值
     */
    public Object hGet(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * HGETALL
     * @param key key
     * @param fields fields
     * @return 哈希中所有的key和value
     */
    public List<Object> hGetAll(String key, Collection<Object> fields) {
        return redisTemplate.opsForHash().multiGet(key, fields);
    }

    /**
     * HKEYS
     * @param key
     * @return 哈希中所有键的集合
     */
    public Set<Object> hKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * HVALS
     * @param key key
     * @return 哈希中所有值的集合
     */
    public List<Object> hVals(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * HEXISTS
     * @param key key
     * @param field field
     * @return 是否存在
     */
    public boolean hExists(String key, Object field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * HDEL
     * @param key key
     * @return 移除成功的数量
     */
    public Long hDel(String key, Object... fields) {
        return redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * HLEN
     * @param key key
     * @return 哈希长度
     */
    public Long hLen(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    /**************************List的操作**************************/

    /**
     * LPUSH
     * @param key key
     * @param value value
     * @return 执行命令完毕后列表长度
     */
    public Long lPush(String key, Object... value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }
    /**
     * LPUSH
     * @param key key
     * @param values values
     * @return 执行命令完毕后列表长度
     */
    public Long lPush(String key, Collection<Object> values) {
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * LSET
     * @param key key
     * @param index 索引
     * @param value value
     */
    public void lSet(String key, Long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * LINDEX
     * @param key key
     * @param index index
     * @return 下标为index的元素
     */
    public Object lIndex(String key, Long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * LRANGE
     * @param key key
     * @param start 开始索引
     * @param end 结束索引
     * @return 指定范围的元素列表
     */
    public List<Object> lRange(String key, Long start, Long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * LREM
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
     * LLEN
     * @param key key
     * @return 列表的长度
     */
    public Long lLen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**************************Set的操作**************************/

    /**
     * SADD
     * @param key key
     * @param members members
     * @return 添加成功的元素数量
     */
    public Long sAdd(String key, Object... members) {
        return redisTemplate.opsForSet().add(key, members);
    }

    /**
     * SCARD
     * @param key key
     * @return
     *      key存在时：集合的基数。
     *      key不存在时：返回 0
     */
    public Long sCard(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * SISMEMBER
     * @param key key
     * @param member member
     * @return 该元素是否给定集合的成员
     */
    public boolean sIsMember(String key, Object member) {
        return redisTemplate.opsForSet().isMember(key, member);
    }

    /**
     * SMEMBER
     * @param key key
     * @return 集合的所有元素集合
     */
    public Set<Object> sMember(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * SMOVE
     * @param sourceKey sourceKey
     * @param destinationKey destinationKey
     * @param member member
     * @return 是否移动成功
     */
    public boolean sMove(String sourceKey, Object member, String destinationKey) {
        return redisTemplate.opsForSet().move(sourceKey, member, destinationKey);
    }

    /**
     * SREM
     * @param key key
     * @param members members
     * @return 移除成功的个数
     */
    public Long sRem(String key, Object... members) {
        return redisTemplate.opsForSet().remove(key, members);
    }

    /**
     * SDIFF
     * @param key1 key1
     * @param key2 key2
     * @return 差集
     */
    public Set<Object> sDiff(String key1, String key2) {
        return redisTemplate.opsForSet().difference(key1, key2);
    }

    /**
     * SINTER
     * @param key1 key1
     * @param key2 key2
     * @return 交集
     */
    public Set<Object> sInter(String key1, String key2) {
        return redisTemplate.opsForSet().intersect(key1, key2);
    }

    /**
     * SUNION
     * @param key1 key1
     * @param key2 key2
     * @return 并集
     */
    public Set<Object> sUnion(String key1, String key2) {
        return redisTemplate.opsForSet().union(key1, key2);
    }

    /**************************SortedSet的操作**************************/

    /**
     * ZADD
     * @param key key
     * @param member member
     * @param score score
     * @return 是否添加成功
     */
    public boolean zAdd(String key, Object member, double score) {
        return redisTemplate.opsForZSet().add(key, member, score);
    }

    /**
     * ZCARD
     * @param key key
     * @return
     *      key存在且是有序集类型时，返回有序集的基数
     *      key不存在时，返回 0
     */
    public Long zCard(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * ZCOUNT
     * @param key key
     * @param minScore scoreMin
     * @param maxScore scoreMax
     * @return 指定区间成员的数量
     */
    public Long zCount(String key, double minScore, double maxScore) {
        return redisTemplate.opsForZSet().count(key, minScore, maxScore);
    }

    /**
     * ZINCRBY
     * @param key key
     * @param member member
     * @param score increment
     * @return member成员的新score 值
     */
    public Double zIncrby(String key, Object member, double score) {
        return redisTemplate.opsForZSet().incrementScore(key, member, score);
    }

    /**
     * ZRANGE
     * @param key key
     * @param start start point
     * @param end end point
     * @return 指定区间的元素集合
     */
    public Set<Object> zRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * ZRANGEBYSCORE
     * @param key key
     * @param minScore minScore
     * @param maxScore maxScore
     * @return 指定分数区间的元素集合
     */
    public Set<Object> zRangeByScore(String key, double minScore, double maxScore) {
        return redisTemplate.opsForZSet().rangeByScore(key, minScore, maxScore);
    }

    /**
     * ZREM
     * @param key key
     * @param members members
     * @return 成功移除成员的数量
     */
    public Long zRem(String key, Object... members) {
        return redisTemplate.opsForZSet().remove(key, members);
    }

    /**
     * ZREMRANGEBYSCORE
     * @param key key
     * @param minScore minScore
     * @param maxScore maxScore
     * @return 成功移除成员的数量
     */
    public Long zRemRangyScore(String key, double minScore, double maxScore) {
        return redisTemplate.opsForZSet().removeRangeByScore(key, minScore, maxScore);
    }

    /**
     * ZSCORE
     * @param key key
     * @param member member
     * @return 指定成员的分数
     */
    public double zScore(String key, Object member) {
        return redisTemplate.opsForZSet().score(key, member);
    }



}
