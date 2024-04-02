package com.bytehonor.sdk.server.spring.web.advisor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.bytehonor.sdk.server.spring.annotation.ResponseNotWrap;
import com.bytehonor.sdk.server.spring.annotation.ResponseWrap;
import com.bytehonor.sdk.server.spring.web.response.ResponseConvertor;

/**
 * @author lijianqiang
 *
 */
@ControllerAdvice
public final class ResponseBeautifyAdvisor implements ResponseBodyAdvice<Object> {

    private static final Logger LOG = LoggerFactory.getLogger(ResponseBeautifyAdvisor.class);

    private static final Class<ResponseWrap> ACCEPT = ResponseWrap.class;

    private static final Class<ResponseNotWrap> IGNORE = ResponseNotWrap.class;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(ACCEPT);
    }

    @Override
    public final Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {

        if (LOG.isDebugEnabled()) {
            LOG.debug("MediaType: {}", selectedContentType.toString());
        }

        if (body == null) {
            LOG.error("BODY NULL, uri{}", request.getURI().getPath());
            response.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY);
            return ResponseConvertor.bodyNull();
        }

        if (MediaType.TEXT_HTML.equals(selectedContentType)) {
            LOG.info("TEXT_HTML, uri{}", request.getURI().getPath());
            return body;
        }

        if (returnType.hasMethodAnnotation(IGNORE)) {
            LOG.info("ResponseNotWrap, uri{}", request.getURI().getPath());
            return body;
        }

        // 强制修改http状态头为200
        response.setStatusCode(HttpStatus.OK);

        // 结果统一转换
        return ResponseConvertor.convert(body);
    }
}
