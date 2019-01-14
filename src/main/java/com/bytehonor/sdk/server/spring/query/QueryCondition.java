package com.bytehonor.sdk.server.spring.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.server.spring.constant.HttpConstants;

/**
 * 
 * @author lijianqiang
 *
 * @param <T>
 */
public final class QueryCondition<T> {

	private static final Logger LOG = LoggerFactory.getLogger(QueryCondition.class);

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

	public String orderBySql() {
		if (order != null) {
			return order.toSql();
		}
		return "";
	}

	public String offsetLimitSql() {
		if (limit > HttpConstants.LIMIT_MAX_TOP) {
			LOG.warn("limit:{} > {}, has reset", limit, HttpConstants.LIMIT_MAX_TOP);
			limit = HttpConstants.LIMIT_MAX_TOP;
		}
		StringBuilder sb = new StringBuilder(" LIMIT ").append(offset).append(",").append(limit);
		return sb.toString();
	}

}
