package com.aster.cloud.commons.core.api.param;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 支持序列化的 Function
 * @author 王骞
 * @date 2020-05-13
 */
@FunctionalInterface
public interface SFunction<T, R> extends Function<T, R>, Serializable {
}
