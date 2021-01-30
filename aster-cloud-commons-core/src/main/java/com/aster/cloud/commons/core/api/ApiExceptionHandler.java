package com.aster.cloud.commons.core.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * REST API 异常拦截处理器
 *
 * @author 王骞
 * @date 2021-01-30
 */
@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public R<String> handlerException(ApiException e) {
        log.error("ApiException: ");
        return R.failed(e.getMessage());
    }

}
