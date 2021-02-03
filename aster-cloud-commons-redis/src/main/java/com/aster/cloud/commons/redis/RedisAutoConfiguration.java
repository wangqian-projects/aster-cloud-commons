package com.aster.cloud.commons.redis;

import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 自动配置
 *
 * @author 王骞
 * @date 2021-02-03
 */
@Data
@Component
public class RedisAutoConfiguration {

    @Resource
    RedisTemplate<String, Object> redisTemplate;

}
