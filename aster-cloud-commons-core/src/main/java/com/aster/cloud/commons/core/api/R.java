package com.aster.cloud.commons.core.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * RESPONSE响应对象
 *
 * @author 王骞
 * @date 2020-10-19
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "响应信息主体")
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "返回标记：成功标记=true，失败标记false")
    private Boolean success;

    @ApiModelProperty(value = "返回信息")
    private String msg;

    @ApiModelProperty(value = "数据")
    private T data;

    public static <T> R<T> success(T data) {
        return restResult(data, true, null);
    }

    public static <T> R<T> success(T data, String msg) {
        return restResult(data, true, msg);
    }

    public static <T> R<T> failed() {
        return restResult(null, false, null);
    }

    public static <T> R<T> failed(String msg) {
        return restResult(null, false, msg);
    }

    public static <T> R<T> failed(T data) {
        return restResult(data, false, null);
    }

    public static <T> R<T> failed(T data, String msg) {
        return restResult(data, false, msg);
    }

    private static <T> R<T> restResult(T data, boolean success, String msg) {
        R<T> apiResult = new R<T>();
        apiResult.setData(data);
        apiResult.setSuccess(success);
        apiResult.setMsg(msg);
        return apiResult;
    }

}
