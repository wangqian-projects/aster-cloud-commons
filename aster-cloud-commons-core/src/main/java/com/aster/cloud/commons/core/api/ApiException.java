package com.aster.cloud.commons.core.api;


/**
 * REST API 请求异常类
 *
 * @author 王骞
 * @date 2020-10-19
 */
public class ApiException extends RuntimeException {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5885155226898287919L;

    public ApiException(String msg) {
        super(msg);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
