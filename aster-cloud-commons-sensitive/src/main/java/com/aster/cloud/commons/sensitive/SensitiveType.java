package com.aster.cloud.commons.sensitive;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.BiFunction;

/**
 * 脱敏类型枚举
 *
 * @author 王骞
 * @date 2021-01-15
 */
@NoArgsConstructor
@AllArgsConstructor
public enum SensitiveType {

    /**
     * 无类型
     */
    NUll(HideUtils::hideValue),
    /**
     * 中文名 [李**]
     */
    NAME((origin, sensitive) -> {
        if (StrUtil.isBlank(origin)) {
            return null;
        }
        return origin.substring(0, 1) + HideUtils.hideAllValue(origin, sensitive, 2);
    }),

    /**
     * 中文名全隐藏 [***]
     */
    NAME_HIDE_ALL((origin, sensitive) -> {
        return HideUtils.hideAllValue(origin, sensitive, 3);
    }),

    /**
     * 身份证号 [110110********1234]
     */
    ID_CARD((origin, sensitive) -> {
        sensitive.put(6, 4);
        return HideUtils.hideValue(origin, sensitive);
    }),
    /**
     * 座机号 [****0000]
     */
    FIXED_PHONE((origin, sensitive) -> {
        sensitive.put(0, 4);
        return HideUtils.hideValue(origin, sensitive);
    }),
    /**
     * 手机号[176******03]
     */
    MOBILE_PHONE((origin, sensitive) -> {
        sensitive.put(3, 2);
        return HideUtils.hideValue(origin, sensitive);
    }),
    /**
     * 密码 全部替换6位长度
     */
    PASSWORD((origin, sensitive) -> {
        return HideUtils.hideAllValue(origin, sensitive, 6);
    }),
    /**
     * 地址
     */
    ADDRESS((origin, sensitive) -> {
        sensitive.put(3, 0);
        return HideUtils.hideValue(origin, sensitive);
    }),
    /**
     * 电子邮件 [g**@163.com]
     */
    EMAIL((origin, sensitive) -> {
        if (origin == null) {
            return null;
        }
        int index = StrUtil.indexOf(origin, '@');
        if (index <= 1) {
            return origin;
        }
        sensitive.put(1, 0);
        String preEmail = HideUtils.hideValue(origin.substring(0, index), sensitive);
        return preEmail + origin.substring(index);
    }),
    /**
     * 银行卡 [622202************1234]
     */
    BANK_CARD((origin, sensitive) -> {
        sensitive.put(6, 4);
        return HideUtils.hideValue(origin, sensitive);
    }),

    /**
     * 公司开户银行联号 [12********]
     */
    SHOPS_CODE((origin, sensitive) -> {
        sensitive.put(2, 0);
        return HideUtils.hideValue(origin, sensitive);
    });

    @Getter
    private BiFunction<String, SensitiveDTO, String> function;

}
