package com.bytehonor.sdk.framework.server.web.response.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.concept.applet.response.JsonResponse;
import com.bytehonor.sdk.framework.server.web.response.ResponseBuilder;

/**
 * @author lijianqiang
 *
 */
public final class JsonResponseBuilder implements ResponseBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(JsonResponseBuilder.class);

    @Override
    public JsonResponse<?> build(Object body) {
        JsonResponse<?> jsonResponse = (JsonResponse<?>) body;

        if (LOG.isDebugEnabled()) {
            LOG.debug("code:{}, message:{}", jsonResponse.getCode(), jsonResponse.getMessage());
        }
        return jsonResponse;
    }

}
