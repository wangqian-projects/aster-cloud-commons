package com.aster.cloud.commons.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * 存活策略
 *
 * @author 王骞
 * @date 2021-02-03
 */
@Getter
@AllArgsConstructor
public class RedisSurviveStrategy {

    private final Long surviveTime;

    private final TimeUnit surviveTimeUnit;

    /*
      缓存默认存活时间
     */
    private static final Long DEFAULT_SURVIVE_TIME = 2L;

    /*
      缓存默认存活时间单位
     */
    private static final TimeUnit DEFAULT_SURVIVE_TIMEUNIT = TimeUnit.HOURS;

    public static RedisSurviveStrategy defaultStrategy() {
        return new RedisSurviveStrategy(DEFAULT_SURVIVE_TIME, DEFAULT_SURVIVE_TIMEUNIT);
    }

    public static RedisSurviveStrategy customerStrategy(Long surviveTime, TimeUnit surviveTimeUnit) {
        return new RedisSurviveStrategy(surviveTime, surviveTimeUnit);
    }

    public static RedisSurviveStrategy noTTLStrategy() {
        return new RedisSurviveStrategy(-1L, DEFAULT_SURVIVE_TIMEUNIT);
    }

}
