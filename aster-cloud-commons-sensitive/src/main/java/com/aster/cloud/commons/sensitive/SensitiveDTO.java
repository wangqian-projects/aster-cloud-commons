package com.aster.cloud.commons.sensitive;

import lombok.Data;

/**
 * @author 王骞
 * @date 2021-01-15
 */
@Data
public class SensitiveDTO {

    private SensitiveType type;

    private int prefixHoldLen;

    private int suffixHoldLen;

    private String mask;

    public SensitiveDTO(Sensitive sensitive) {
        type = sensitive.value();
        prefixHoldLen = sensitive.prefixHoldLen();
        suffixHoldLen = sensitive.suffixHoldLen();
        mask = sensitive.mask();
    }

    public void put(int prefixHoldLen, int suffixHoldLen) {
        this.prefixHoldLen = prefixHoldLen;
        this.suffixHoldLen = suffixHoldLen;
    }
}
