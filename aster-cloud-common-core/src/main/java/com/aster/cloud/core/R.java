package com.aster.cloud.core;

import com.aster.cloud.core.constant.RCodeConstant;
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

    @ApiModelProperty(value = "返回标记：成功标记=1，失败标记=0")
    private int code;

    @ApiModelProperty(value = "返回信息")
    private String message;

    @ApiModelProperty(value = "数据")
    private T data;

    public static <T> R<T> ok(T data) {
        return restResult(data, RCodeConstant.SUCCESS, null);
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, RCodeConstant.SUCCESS, msg);
    }

    public static <T> R<T> failed() {
        return restResult(null, RCodeConstant.FAIL, null);
    }

    public static <T> R<T> failed(String msg) {
        return restResult(null, RCodeConstant.FAIL, msg);
    }

    public static <T> R<T> failed(T data) {
        return restResult(data, RCodeConstant.FAIL, null);
    }

    public static <T> R<T> failed(T data, String msg) {
        return restResult(data, RCodeConstant.FAIL, msg);
    }

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> apiResult = new R<T>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMessage(msg);
        return apiResult;
    }

}
