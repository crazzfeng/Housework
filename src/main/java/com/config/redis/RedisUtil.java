package com.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redicache 工具类
 *
 * @author yufeng li
 * @title: RedisUtil
 * @description:
 * @date 2019/11/7 17:47
 */
@SuppressWarnings("unchecked")
@Component
public class RedisUtil {


    //@Autowired
    //private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 批量删除对应的value
     *
     * @param keys 键值
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern 键值匹配规则
     */
    public void removePattern(final String pattern) {
        Set<String> keys = stringRedisTemplate.keys(pattern);
        if (keys.size() > 0){
            stringRedisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key 键值
     */
    public void remove(final String key) {
        if (exists(key)) {
            stringRedisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key 键值
     * @return：
     */
    public boolean exists(final String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key 键值
     * @return：
     */
    public String get(final String key) {
        String result = null;
        stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        result = operations.get(key);
        if (result == null) {
            return null;
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key   键值
     * @param value 值
     * @return：
     */
    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key   键值
     * @param value 值
     * @return：
     */
    public boolean set(final String key, String value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(key, value);
            stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean hmset(String key, Map<Object, Object> value) {
        boolean result = false;
        try {
            stringRedisTemplate.opsForHash().putAll(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Map<Object, Object> hmget(String key) {
        Map<Object, Object> result = null;
        try {
            HashOperations<String, Object, Object> opsForHash = stringRedisTemplate.opsForHash();
            result = opsForHash.entries(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
