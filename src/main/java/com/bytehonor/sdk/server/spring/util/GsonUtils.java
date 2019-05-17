package com.bytehonor.sdk.server.spring.util;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GsonUtils {

    // private static String GSON_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static Gson GSON;

    private static Gson GSON_WITH_DATE;

    // private static Gson GSON_IGNORE_NULL;

    static {
        GSON = new Gson();
        // GSON_IGNORE_NULL = new GsonBuilder().serializeNulls().create();
        GSON_WITH_DATE = create();
    }

    public static String toJson(Object object) {
        return GSON.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return GSON.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return GSON.fromJson(json, typeOfT);
    }

    public static String toJsonDate2Long(Object object) {
        Gson gson = create();
        return gson.toJson(object);
    }

    public static <T> T fromJsonLong2Date(String json, Class<T> classOfT) {
        return GSON_WITH_DATE.fromJson(json, classOfT);
    }

    public static <T> T fromJsonLong2Date(String json, Type typeOfT) {
        return GSON_WITH_DATE.fromJson(json, typeOfT);
    }

    //
    private static Gson create() {
        GsonBuilder gb = new GsonBuilder().serializeNulls();
        gb.registerTypeAdapter(Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
        gb.registerTypeAdapter(Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
        Gson gson = gb.create();
        return gson;
    }

    private static class DateDeserializer implements JsonDeserializer<Date> {

        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            return new Date(json.getAsJsonPrimitive().getAsLong());
        }
    }

    private static class DateSerializer implements JsonSerializer<Date> {
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.getTime());
        }
    }
}
