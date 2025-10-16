package com.bytehonor.sdk.framework.server.web.response.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.core.response.JsonResponse;
import com.bytehonor.sdk.framework.server.web.constant.StandardCode;
import com.bytehonor.sdk.framework.server.web.response.ResponseBuilder;

/**
 * @author lijianqiang
 *
 */
public final class DefaultResponseBuilder implements ResponseBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultResponseBuilder.class);

    @Override
    public JsonResponse<?> build(Object body) {

        JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
        jsonResponse.setCode(StandardCode.OK);
        jsonResponse.setMessage(StandardCode.SUCCESS);
        jsonResponse.setData(body);

        if (LOG.isDebugEnabled()) {
            LOG.debug("ErrorCode:{}", jsonResponse.getCode());
        }

        return jsonResponse;
    }

}
