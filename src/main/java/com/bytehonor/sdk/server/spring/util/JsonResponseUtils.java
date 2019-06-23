package com.bytehonor.sdk.server.spring.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.protocol.common.code.StandardCode;
import com.bytehonor.sdk.protocol.common.result.JsonResponse;
import com.bytehonor.sdk.server.spring.exception.InternalRestfulException;

public class JsonResponseUtils {

    private static final Logger LOG = LoggerFactory.getLogger(JsonResponseUtils.class);

    public static <T> T safeGet(JsonResponse<T> response) {
        if (response == null) {
            throw new InternalRestfulException(StandardCode.INTERNAL_ERROR, "RESPONSE NULL");
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("JsonResponse ErrorCode:{}", response.getCode());
        }
        if (response.getCode() != StandardCode.OK) {
            if (LOG.isInfoEnabled()) {
                for (String err : response.getTrace()) {
                    LOG.info("[trace]:{}", err);
                }
            }
            throw new InternalRestfulException(response.getCode(), response.getMessage());
        }
        T data = response.getData();
        if (data == null) {
            throw new InternalRestfulException(StandardCode.INTERNAL_ERROR, "RESPONSE BODY NULL");
        }
        return data;
    }

    public static <S> JsonResponse<S> feignFallback() {
        JsonResponse<S> result = new JsonResponse<S>();
        result.setCode(StandardCode.FEIGN_FALLBACK);
        result.setMessage("远程服务不可用");
        return result;
    }

}
