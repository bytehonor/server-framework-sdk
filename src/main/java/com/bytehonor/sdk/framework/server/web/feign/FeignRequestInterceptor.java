package com.bytehonor.sdk.framework.server.web.feign;

import com.bytehonor.sdk.framework.server.web.constant.ServerFeignConstants;
import com.bytehonor.sdk.framework.server.web.context.ServerContext;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        if (attributes != null) {
//            HttpServletRequest request = attributes.getRequest();
//            // 假设AccessToken在请求头中传递
//            String accessToken = request.getHeader("Authorization");
//            template.header("Authorization", "Bearer " + accessToken);
//        }
        template.header(ServerFeignConstants.X_FEIGN, ServerContext.self().getId());
    }
}
