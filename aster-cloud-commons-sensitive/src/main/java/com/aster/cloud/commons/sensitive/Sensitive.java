package com.aster.cloud.commons.sensitive;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 脱敏注解
 *
 * @author 王骞
 * @date 2021-01-15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveSerializer.class)
public @interface Sensitive {

    String DEFAULT_MASK = "*";

    /**
     * 脱敏类型
     * @return SensitiveTypeEnum
     */
    SensitiveType value() default SensitiveType.NUll;

    /**
     * 前缀不打码的长度
     */
    int prefixHoldLen() default 0;

    /**
     * 后缀不打码的长度
     */
    int suffixHoldLen() default 0;

    /**
     * 用什么字符打码, 默认*
     */
    String mask() default DEFAULT_MASK;

}
