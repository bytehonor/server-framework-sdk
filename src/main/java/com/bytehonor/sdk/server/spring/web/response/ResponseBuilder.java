package com.bytehonor.sdk.server.spring.web.response;

import com.bytehonor.sdk.server.spring.web.model.JsonResponse;

/**
 * @author lijianqiang
 *
 */
public interface ResponseBuilder {

    JsonResponse<?> build(Object body);
}
