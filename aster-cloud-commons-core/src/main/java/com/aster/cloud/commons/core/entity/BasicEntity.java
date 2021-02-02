package com.aster.cloud.commons.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 王骞
 * @date 2021-01-31
 */
@Data
@ApiModel(value = "超实体类")
public class BasicEntity {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "租户ID")
    private Integer tenantId;

    @ApiModelProperty(value = "建立人员")
    private String createBy;

    @ApiModelProperty(value = "更新人员")
    private String updateBy;

    @ApiModelProperty(value = "建立时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标志")
    private Boolean delFlag;

}
