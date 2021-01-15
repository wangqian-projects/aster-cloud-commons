package com.aster.cloud.commons.sensitive;

import lombok.Data;

/**
 * 测试用户类
 *
 * @author 王骞
 * @date 2021-01-15
 */
@Data
public class UserVO {
    @Sensitive(SensitiveType.NAME)
    private String name1 = "";
    @Sensitive(SensitiveType.NAME_HIDE_ALL)
    private String name2 = "琳";
    @Sensitive(SensitiveType.MOBILE_PHONE)
    private String telephone = "17300000003";
    @Sensitive(SensitiveType.PASSWORD)
    private String password = "123456aster";
    @Sensitive(SensitiveType.EMAIL)
    private String email = "925548289@qq.com";
}
