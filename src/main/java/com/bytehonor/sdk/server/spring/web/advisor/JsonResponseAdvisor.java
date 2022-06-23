package com.bytehonor.sdk.server.spring.web.advisor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.bytehonor.sdk.server.spring.annotation.ResponseNotWrap;
import com.bytehonor.sdk.server.spring.config.SpringBootStandardProperties;
import com.bytehonor.sdk.server.spring.web.error.ErrorConvertor;
import com.bytehonor.sdk.server.spring.web.response.ResponseConvertor;

@ControllerAdvice
public final class JsonResponseAdvisor implements ResponseBodyAdvice<Object> {

    private static final Logger LOG = LoggerFactory.getLogger(JsonResponseAdvisor.class);

    private static final Class<ResponseNotWrap> IGNORE = ResponseNotWrap.class;

    @Autowired
    private SpringBootStandardProperties properties;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(IGNORE) == false;
    }

    @Override
    public final Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {

        if (LOG.isDebugEnabled()) {
            LOG.debug("MediaType: {}", selectedContentType.toString());
        }

        if (body == null) {
            LOG.error("body null, uri{}", request.getURI().getPath());
            response.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY);
            return ErrorConvertor.bodyNull();
        }

        if (MediaType.TEXT_HTML.equals(selectedContentType)) {
            return body;
        }

        if (returnType.hasMethodAnnotation(IGNORE)) {
            return body;
        }

        if (properties.isForceHttpStatus()) {
            response.setStatusCode(HttpStatus.OK);
        }

        return ResponseConvertor.convert(body);
    }
}
