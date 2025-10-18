package com.bytehonor.sdk.framework.server.web.response;

import com.bytehonor.sdk.concept.spring.response.JsonResponse;

/**
 * @author lijianqiang
 *
 */
public interface ResponseBuilder {

    JsonResponse<?> build(Object body);
}
