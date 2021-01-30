package com.aster.cloud.commons.core.api.param;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.aster.cloud.commons.core.api.ApiException;

import java.util.Collection;
import java.util.Map;

/**
 * 参数基本断言
 * 支持链式调用
 *
 * @author 王骞
 * @date 2021-01-30
 */
public class ParamAssert<T> {

    private final T entity;

    /**
     * 传入接口参数
     * @param entity 接口参数
     * @param <T> 泛型
     * @return ParamAssert
     */
    public static <T> ParamAssert<T> param(T entity) {
        return new ParamAssert<>(entity);
    }

    public ParamAssert(T entity) {
        this.entity = entity;
    }

    /**
     * 判断对象字段是否为空
     *
     * @param field 字段
     * @param msg 响应信息
     * @return ParamAssert
     */
    public ParamAssert<T> isBlank(SFunction<T, ?> field, String msg) {
        // 当前验证列的值
        Object value = field.apply(this.entity);
        if (isBlank(value)) {
           throw new ApiException(msg);
        }
        return this;
    }


    private boolean isBlank(Object value) {
        if (value == null) {
            return true;
        }
        if (value instanceof CharSequence) {
            return StrUtil.isBlankIfStr(value);
        }
        if (value instanceof Collection) {
            return CollectionUtil.isEmpty((Collection) value);
        }
        if (value instanceof Map)
            return MapUtil.isEmpty((Map) value);
        return false;
    }

}
