package com.bytehonor.sdk.server.spring.util;

import java.io.IOException;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {

    private static final Logger LOG = LoggerFactory.getLogger(JacksonUtils.class);

    private static final ObjectMapper JACKSON_MAPPER = new ObjectMapper();

    static {
        JACKSON_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T fromJson(String json, Class<T> valueType)
            throws JsonParseException, JsonMappingException, IOException {
        Objects.requireNonNull(json, "json");
        Objects.requireNonNull(valueType, "valueType");
        return JACKSON_MAPPER.readValue(json, valueType);
    }

    public static <T> T fromJson(String json, TypeReference<T> valueTypeRef)
            throws JsonParseException, JsonMappingException, IOException {
        Objects.requireNonNull(json, "json");
        Objects.requireNonNull(valueTypeRef, "valueTypeRef");
        return JACKSON_MAPPER.readValue(json, valueTypeRef);
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
