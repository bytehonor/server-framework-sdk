package com.bytehonor.sdk.basic.server.util;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.basic.server.exception.SpringServerException;
import com.bytehonor.sdk.protocol.common.code.StandardCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {

    private static final Logger LOG = LoggerFactory.getLogger(JacksonUtils.class);

    private static final ObjectMapper JACKSON_MAPPER = new ObjectMapper();

    static {
        JACKSON_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T fromJson(String json, Class<T> valueType) {
        Objects.requireNonNull(json, "json");
        Objects.requireNonNull(valueType, "valueType");
        try {
            return JACKSON_MAPPER.readValue(json, valueType);
        } catch (Exception e) {
            LOG.error("error:{}, json:{}", e.getMessage(), json);
            throw new SpringServerException(StandardCode.FRAMEWORK_ERROR, e.getMessage());
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> valueTypeRef) {
        Objects.requireNonNull(json, "json");
        Objects.requireNonNull(valueTypeRef, "valueTypeRef");
        try {
            return JACKSON_MAPPER.readValue(json, valueTypeRef);
        } catch (Exception e) {
            LOG.error("error:{}, json:{}", e.getMessage(), json);
            throw new SpringServerException(StandardCode.FRAMEWORK_ERROR, e.getMessage());
        }
    }

    public static String toJson(Object value) {
        Objects.requireNonNull(value, "value");
        try {
            return JACKSON_MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            LOG.error("toJson", e);
            return null;
        }
    }
}
