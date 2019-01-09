package com.bytehonor.sdk.server.spring.query;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import com.bytehonor.sdk.server.spring.getter.RequestGetter;

public class QueryConditionBuilder {

	public static <T> QueryCondition<T> build(HttpServletRequest request, T model) {
		Objects.requireNonNull(request, "request");
		Objects.requireNonNull(model, "model");
		int offset = RequestGetter.getOffset(request);
		int limit = RequestGetter.getLimit(request);
		return build(offset, limit, model);
	}

	public static <T> QueryCondition<T> build(int offset, int limit, T model) {
		Objects.requireNonNull(model, "model");
		QueryCondition<T> queryCondition = new QueryCondition<T>();
		queryCondition.setLimit(limit);
		queryCondition.setOffset(offset);
		queryCondition.setModel(model);
		return queryCondition;
	}
}
