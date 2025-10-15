package com.bytehonor.sdk.framework.server.web.advisor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bytehonor.sdk.framework.server.exception.ErrorConvertor;
import com.bytehonor.sdk.framework.spring.response.JsonResponse;

/**
 * 
 * @author lijianqiang
 *
 */
@ControllerAdvice
public class GlobalExceptionAdvisor {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionAdvisor.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public final JsonResponse<?> globalErrorHandler(Exception ex) {
        LOG.error("globalErrorHandler", ex);
        return ErrorConvertor.convert(ex);
    }

}
