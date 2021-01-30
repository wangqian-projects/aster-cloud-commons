package com.aster.cloud.commons.core.api;

import io.swagger.annotations.ApiModelProperty;

/**
 * 定义业务状态码接口
 * 规范: api中含有业务状态码时, 响应对象必须含有resultCode字段;
 * 并在接口文档清晰描述业务状态码含义
 *
 * @author 王骞
 * @date 2020-10-19
 */
public interface IResultCode {

    @ApiModelProperty(value = "业务状态码")
    int getResultCode();

}
