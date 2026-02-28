package com.bytehonor.sdk.framework.server.web.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.concept.applet.response.JsonResponse;
import com.bytehonor.sdk.framework.server.web.constant.StandardCode;

/**
 * @author lijianqiang
 *
 */
public class ResponseConvertor {

    private static final Logger LOG = LoggerFactory.getLogger(ResponseConvertor.class);

    public static JsonResponse<?> convert(Object body) {
        if (body == null) {
            bodyNull();
        }

        try {
            ResponseBuilder builder = ResponseBuilderFactory.get(body);
            return builder.build(body);
        } catch (Exception e) {
            LOG.error("build error", e);
            return error();
        }

    }

    /**
     * Null响应
     * 
     * @return
     */
    public static JsonResponse<?> bodyNull() {
        JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
        jsonResponse.setCode(StandardCode.INTERNAL_ERROR);
        jsonResponse.setMessage("METHOD_RETURN_NULL");
        return jsonResponse;
    }

    /**
     * 系统错误
     * 
     * @return
     */
    public static JsonResponse<?> error() {
        JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
        jsonResponse.setCode(StandardCode.FRAMEWORK_ERROR);
        jsonResponse.setMessage("SYSTEM_ERROR");
        return jsonResponse;
    }
}
