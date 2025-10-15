package com.bytehonor.sdk.framework.server.web.feign;

import feign.InvocationContext;
import feign.ResponseInterceptor;

public class FeignResponseInterceptor implements ResponseInterceptor {

    @Override
    public Object intercept(InvocationContext invocationContext, Chain chain) throws Exception {
        // invocationContext.returnType() // 是Feign方法申明的返回值
        return chain.next(invocationContext);
    }

}
