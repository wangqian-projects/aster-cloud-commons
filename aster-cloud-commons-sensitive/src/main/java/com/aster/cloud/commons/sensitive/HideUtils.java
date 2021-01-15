package com.aster.cloud.commons.sensitive;

/**
 * 脱敏隐藏工具
 *
 * @author 王骞
 * @date 2021-01-15
 */
public class HideUtils {

    /**
     * 对字符串进行脱敏操作
     *
     * @param origin    原始字符串
     * @param sensitive 脱敏信息
     * @return 脱敏后结果
     */
    public static String hideValue(String origin, SensitiveDTO sensitive) {
        if (origin == null) {
            return null;
        }

        String mask = sensitive.getMask();
        int prefixNoMaskLen = sensitive.getPrefixHoldLen();
        int suffixNoMaskLen = sensitive.getSuffixHoldLen();
        StringBuilder sb = new StringBuilder();
        for (int i = 0, n = origin.length(); i < n; i++) {
            if (i < prefixNoMaskLen) {
                sb.append(origin.charAt(i));
                continue;
            }
            if (i > (n - suffixNoMaskLen - 1)) {
                sb.append(origin.charAt(i));
                continue;
            }
            sb.append(mask);
        }
        return sb.toString();
    }


    public static String hideAllValue(String origin, SensitiveDTO sensitive, int len) {
        if (origin == null) {
            return null;
        }
        String mask = sensitive.getMask();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(mask);
        }
        return sb.toString();
    }

}
