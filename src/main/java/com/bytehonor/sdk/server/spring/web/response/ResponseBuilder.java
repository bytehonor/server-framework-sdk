package com.bytehonor.sdk.server.spring.web.response;

import com.bytehonor.sdk.define.spring.result.JsonResponse;

/**
 * @author lijianqiang
 *
 */
public interface ResponseBuilder {

    JsonResponse<?> build(Object body);
}
