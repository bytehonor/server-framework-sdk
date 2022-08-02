package com.bytehonor.sdk.server.spring.web.advisor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bytehonor.sdk.define.spring.response.JsonResponse;
import com.bytehonor.sdk.server.spring.web.error.ErrorConvertor;

/**
 * 
 * @author lijianqiang
 *
 */
@ControllerAdvice
public class ErrorResponseAdvisor {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorResponseAdvisor.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public final JsonResponse<?> globalErrorHandler(Exception ex) {
        LOG.error("globalErrorHandler", ex);
        return ErrorConvertor.convert(ex);
    }

}
