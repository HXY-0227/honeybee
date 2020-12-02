package com.honeybee.common.configure;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author : HXY
 * @date : 2020-08-04 23:45
 **/
public class LocalCache<K,V> {
    private Cache<K, V> localCache = null;

    private void init() {
        localCache = CacheBuilder.newBuilder()
                .initialCapacity(10)
                .maximumSize(500)
                .expireAfterWrite(60, TimeUnit.SECONDS)
                .build();
    }

    public Object get(String key) {
        return localCache.getIfPresent(key);
    }
}
