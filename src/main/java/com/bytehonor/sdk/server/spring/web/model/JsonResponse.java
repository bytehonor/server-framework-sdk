package com.bytehonor.sdk.server.spring.web.model;

import java.io.Serializable;

import com.bytehonor.sdk.server.spring.web.constant.StandardCode;

/**
 * Json Response
 * 
 * @author lijianqiang
 *
 * @param <T>
 */
public final class JsonResponse<T> implements Serializable {

    private static final long serialVersionUID = 2464992888381774481L;

    private Integer code;

    private String message;

    private T data;

    public static JsonResponse<DataString> ok(String data) {
        return success(DataString.of(data));
    }

    public static JsonResponse<DataInteger> ok(Integer data) {
        return success(DataInteger.of(data));
    }

    public static JsonResponse<DataLong> ok(Long data) {
        return success(DataLong.of(data));
    }

    public static JsonResponse<DataBoolean> ok(Boolean data) {
        return success(DataBoolean.of(data));
    }

    public static <R> JsonResponse<R> success(R data) {
        JsonResponse<R> result = new JsonResponse<R>();
        result.setCode(StandardCode.OK);
        result.setMessage(StandardCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static <R> JsonResponse<R> error(int code, String message, R data) {
        JsonResponse<R> result = new JsonResponse<R>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <R> JsonResponse<R> error(int code, String message) {
        return error(code, message, null);
    }

    public static <T> T safeGet(JsonResponse<T> response) {
        if (response == null) {
            throw new RuntimeException("RESPONSE NULL");
        }
        if (response.getCode() != StandardCode.OK) {
            throw new RuntimeException(response.toString());
        }
        T data = response.getData();
        if (data == null) {
            throw new RuntimeException("RESPONSE BODY NULL");
        }
        return data;
    }

    public static <T> boolean isError(JsonResponse<T> response) {
        if (response == null) {
            return true;
        }
        return response.getCode() != StandardCode.OK;
    }

    public static <S> JsonResponse<S> fallback() {
        return fallback(null);
    }

    public static <S> JsonResponse<S> fallback(S data) {
        JsonResponse<S> result = new JsonResponse<S>();
        result.setCode(StandardCode.FEIGN_FALLBACK);
        result.setMessage("远程服务不可用");
        result.setData(data);
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[code:").append(this.code).append(", message:").append(this.message).append("]");
        return sb.toString();
    }

}
