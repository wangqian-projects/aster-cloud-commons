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
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "响应状态码")
    private int code;

    @ApiModelProperty(value = "数据")
    private T data;

    @ApiModelProperty(value = "返回标记：成功标记=true，失败标记=false")
    private Boolean success;

    @ApiModelProperty(value = "返回信息")
    private String msg;


    public static <T> Result<T> success() {
        return restResult(null, true, null);
    }

    public static <T> Result<T> success(T data) {
        return restResult(data, true, null);
    }

    public static <T> Result<T> success(T data, String msg) {
        return restResult(data, true, msg);
    }

    public static <T> Result<T> failed() {
        return restResult(null, false, null);
    }

    public static <T> Result<T> failed(String msg) {
        return restResult(null, false, msg);
    }

    public static <T> Result<T> failed(T data) {
        return restResult(data, false, null);
    }

    public static <T> Result<T> failed(T data, String msg) {
        return restResult(data, false, msg);
    }

    private static <T> Result<T> restResult(T data, boolean success, String msg) {
        Result<T> apiResult = new Result<>();
        apiResult.setCode(success ? 200 : 500);
        apiResult.setData(data);
        apiResult.setSuccess(success);
        apiResult.setMsg(msg);
        return apiResult;
    }

}
