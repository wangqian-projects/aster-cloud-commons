package com.aster.cloud.commons.redis;

import com.aster.cloud.commons.core.SpringContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 静态工具类
 *
 * @author 王骞
 * @date 2021-02-03
 */
public class RedisUtil {


    private static RedisTemplate<String, Object> getRedisTemplate() {
        return SpringContextHolder.getBean(RedisAutoConfiguration.class).getRedisTemplate();
    }

    /**
     * 写入缓存
     *
     * @param key   键
     * @param value 值
     **/
    public static void setValue(String key, Object value) {
        ValueOperations<String, Object> valueOperations = getRedisTemplate().opsForValue();
        valueOperations.set(key, value);
    }

    /**
     * 写入缓存
     *
     * @param key        键
     * @param value      值
     * @param expireTime key/value的有效期
     * @param timeUnit   时间类型（时、分、秒 等）
     **/
    public static void setValue(String key, Object value, Long expireTime, TimeUnit timeUnit) {
        ValueOperations<String, Object> operations = getRedisTemplate().opsForValue();
        operations.set(key, value, expireTime, timeUnit);
    }

    /**
     * 如果key不存在，则写入缓存
     *
     * @param key        键
     * @param value      值
     * @param expireTime key/value的有效期
     * @param timeUnit   时间类型（时、分、秒 等）
     **/
    public static boolean setIfNotExist(String key, Object value, Long expireTime, TimeUnit timeUnit) {
        ValueOperations<String, Object> operations = getRedisTemplate().opsForValue();
        return operations.setIfAbsent(key, value, expireTime, timeUnit);
    }

    /**
     * 写入缓存
     *
     * @param key      键
     * @param value    值
     * @param strategy 生存策略
     **/
    public static void setValue(String key, Object value, RedisSurviveStrategy strategy) {
        if (strategy.getSurviveTime() == null || strategy.getSurviveTime() == 0) {
            throw new NullPointerException("RedisUtil.setValue : strategy.surviveTime can not be null");
        }

        if (strategy.getSurviveTimeUnit() == null) {
            throw new NullPointerException("RedisUtil.setValue : strategy.surviveTimeUnit can not be null");
        }

        setValue(key, value, strategy.getSurviveTime(), strategy.getSurviveTimeUnit());
    }

    /**
     * 写入管道缓存
     *
     * @param key    键
     * @param values 值
     **/
    public static void setPipelineValue(String key, Map<String, Object> values) {
        getRedisTemplate().executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
                values.forEach((k, v) -> setValue(key + k, v));
                return null;
            }
        });
    }

    /**
     * 批量给redis数据添加ttl时间
     *
     * @param keyList  键集
     * @param strategy 生存策略
     **/
    public static void expirePipelineKey(List<String> keyList, RedisSurviveStrategy strategy) {
        getRedisTemplate().executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
                keyList.forEach(key -> expire(key, strategy));
                return null;
            }
        });
    }


    /**
     * 批量给redis数据添加ttl时间
     *
     * @param keyList 键集
     **/
    public static void removePipelineKey(List<String> keyList) {
        getRedisTemplate().executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
                keyList.forEach(key -> removeValue(key));
                return null;
            }
        });
    }


    /**
     * 批量获取键列表对应的数据
     *
     * @param keyList 键集
     **/
    public static List<Object> getPipelineValue(List<String> keyList) {
        return getRedisTemplate().executePipelined(new SessionCallback<List<Object>>() {
            @Override
            public List<Object> execute(RedisOperations redisOperations) throws DataAccessException {
                for (Integer i = 0; i < keyList.size(); i++) {
                    redisOperations.opsForValue().get(keyList.get(i));
                }
                return null;
            }
        });
    }


    /**
     * 写入List类型缓存
     *
     * @param key    键
     * @param values List
     **/
    public static void setList(String key, List<Object> values) {
        ListOperations<String, Object> operations = getRedisTemplate().opsForList();
        operations.leftPushAll(key, values);
    }

    /**
     * 写入List类型缓存
     *
     * @param key   键
     * @param value 对象
     **/
    public static void setList(String key, Object value) {
        ListOperations<String, Object> operations = getRedisTemplate().opsForList();
        operations.leftPush(key, value);
    }

    /**
     * 写入Set类型缓存
     *
     * @param key   键
     * @param value 对象
     **/
    public static void setSet(String key, Object value) {
        SetOperations<String, Object> operations = getRedisTemplate().opsForSet();
        operations.add(key, value);
    }

    /**
     * 重新设置key的ttl
     *
     * @param key 键
     **/
    public static void expire(String key, RedisSurviveStrategy strategy) {
        getRedisTemplate().expire(key, strategy.getSurviveTime(), strategy.getSurviveTimeUnit());
    }

    /**
     * 获取List缓存
     *
     * @param key 键
     **/
    public static <T> Set<T> getSet(String key) {
        SetOperations<String, Object> operations = getRedisTemplate().opsForSet();
        return (Set<T>) operations.members(key);
    }

    /**
     * 删除Set缓存
     *
     * @param key   键
     * @param value 值
     **/
    public static void removeSetValue(String key, Object value) {
        SetOperations<String, Object> operations = getRedisTemplate().opsForSet();
        operations.remove(key, value);
    }

    /**
     * 删除key
     *
     * @param key 键
     **/
    public static void deleteKey(String key) {
        getRedisTemplate().delete(key);
    }

    /**
     * 获取List缓存
     **/
    public static <T> List<T> getList(String key) {
        ListOperations<String, Object> operations = getRedisTemplate().opsForList();
        return (List<T>) operations.range(key, 0, -1);
    }

    /**
     * 删除List缓存
     *
     * @param key   键
     * @param value 值
     **/
    public static void removeListValue(String key, Object value) {
        ListOperations<String, Object> operations = getRedisTemplate().opsForList();
        operations.remove(key, 1, value);
    }

    /**
     * 删除缓存
     *
     * @param key 键
     **/
    public static void removeValue(String key) {
        getRedisTemplate().delete(key);
    }

    /**
     * 获取缓存
     *
     * @param key 键
     **/
    public static Object getValue(String key) {
        ValueOperations<String, Object> valueOperations = getRedisTemplate().opsForValue();
        return valueOperations.get(key);
    }

    public static void setHashFieldValue(String key, String field, Object value) {
        HashOperations<String, String, Object> operations = getRedisTemplate().opsForHash();
        operations.put(key, field, value);
    }

    public static Object getHashFieldValue(String key, String field) {
        HashOperations<String, String, Object> operations = getRedisTemplate().opsForHash();
        return operations.get(key, field);
    }
}
