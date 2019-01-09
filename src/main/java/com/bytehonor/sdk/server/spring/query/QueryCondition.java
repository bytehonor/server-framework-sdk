package com.bytehonor.sdk.server.spring.query;

/**
 * 
 * @author lijianqiang
 *
 * @param <T>
 */
public final class QueryCondition<T> {

	private int offset;

	private int limit;

	private T model;

	private QueryOrder order;

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public QueryOrder getOrder() {
		return order;
	}

	public void setOrder(QueryOrder order) {
		this.order = order;
	}

}
