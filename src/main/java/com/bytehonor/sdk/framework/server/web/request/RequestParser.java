package com.bytehonor.sdk.framework.server.web.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.Java;
import com.bytehonor.sdk.framework.lang.constant.HttpConstants;
import com.bytehonor.sdk.framework.lang.constant.QueryLogic;
import com.bytehonor.sdk.framework.lang.constant.SqlOperator;
import com.bytehonor.sdk.framework.lang.core.KeyValueMap;
import com.bytehonor.sdk.framework.lang.getter.BooleanGetter;
import com.bytehonor.sdk.framework.lang.getter.IntegerGetter;
import com.bytehonor.sdk.framework.lang.meta.MetaModel;
import com.bytehonor.sdk.framework.lang.meta.MetaModelField;
import com.bytehonor.sdk.framework.lang.meta.MetaModelUtils;
import com.bytehonor.sdk.framework.lang.query.QueryCondition;
import com.bytehonor.sdk.framework.lang.query.QueryFilter.QueryFilterColumn;
import com.bytehonor.sdk.framework.lang.query.QueryOrder.QueryOrderColumn;
import com.bytehonor.sdk.framework.lang.query.QueryPager;
import com.bytehonor.sdk.framework.lang.string.StringKit;
import com.bytehonor.sdk.framework.lang.string.StringSplitUtils;
import com.google.common.collect.Sets;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 
 * @author lijianqiang
 *
 */
public class RequestParser {

    private static final Logger LOG = LoggerFactory.getLogger(RequestParser.class);

    // private static final char SPL_DOT = '.';

    private static final Set<String> IGNORES = Sets.newHashSet(HttpConstants.COUNT_KEY, HttpConstants.LIMIT_KEY,
            HttpConstants.OFFSET_KEY, HttpConstants.PAGE_KEY, HttpConstants.SORT_KEY, "token");

    public static QueryCondition and(HttpServletRequest request) {
        KeyValueMap map = RequestGetter.map(request);
        return QueryCondition.create(QueryLogic.AND, pager(map));
    }

    public static QueryCondition and(Class<?> clazz, HttpServletRequest request) {
        KeyValueMap map = RequestGetter.map(request);
        return condition(clazz, QueryLogic.AND, map);
    }

    public static QueryCondition or(Class<?> clazz, HttpServletRequest request) {
        KeyValueMap map = RequestGetter.map(request);
        return condition(clazz, QueryLogic.OR, map);
    }

    public static QueryCondition condition(Class<?> clazz, QueryLogic logic, KeyValueMap map) {
        Java.requireNonNull(logic, "logic");
        Java.requireNonNull(clazz, "clazz");
        Java.requireNonNull(map, "map");

        MetaModel model = MetaModelUtils.parse(clazz);

        QueryCondition condition = QueryCondition.create(logic, pager(map));
        condition.filters(filters(model, map.map()));
        condition.orders(orders(model, map.get(HttpConstants.SORT_KEY)));
        return condition;
    }

    public static QueryPager pager(HttpServletRequest request) {
        KeyValueMap map = RequestGetter.map(request);
        return pager(map);
    }

    /**
     * page和offset都存在时，offset会覆盖page
     * 
     * @param request
     * @return
     */
    public static QueryPager pager(KeyValueMap map) {
        Java.requireNonNull(map, "map");

        int limit = HttpConstants.LIMIT_DEF;
        if (map.has(HttpConstants.LIMIT_KEY)) {
            limit = IntegerGetter.optional(map.get(HttpConstants.LIMIT_KEY), HttpConstants.LIMIT_DEF);
        }

        int offset = HttpConstants.OFFSET_DEF;
        if (map.has(HttpConstants.PAGE_KEY)) {
            int page = IntegerGetter.optional(map.get(HttpConstants.PAGE_KEY), HttpConstants.PAGE_DEF);
            offset = (page - 1) * limit;
        }
        if (map.has(HttpConstants.OFFSET_KEY)) {
            offset = IntegerGetter.optional(map.get(HttpConstants.OFFSET_KEY), HttpConstants.OFFSET_DEF);
        }

        boolean counted = true;
        if (offset > 0 && map.has(HttpConstants.COUNT_KEY)) {
            counted = BooleanGetter.optional(map.get(HttpConstants.COUNT_KEY), false);

        }
        return QueryPager.of(counted, offset, limit);
    }

    public static List<QueryFilterColumn> filters(MetaModel model, Map<String, String> map) {
        if (map.isEmpty()) {
            return new ArrayList<QueryFilterColumn>();
        }
        List<QueryFilterColumn> filters = new ArrayList<QueryFilterColumn>(map.size() * 2);
        for (Entry<String, String> entry : map.entrySet()) {
            if (IGNORES.contains(entry.getKey())) {
                continue;
            }
            filters.add(filter(model, entry.getKey(), entry.getValue()));
        }
        return filters;
    }

    public static QueryFilterColumn filter(MetaModel model, String raw, String value) {
        if (StringKit.isEmpty(raw) || StringKit.isEmpty(value)) {
            return QueryFilterColumn.non(); // value为空字符则丢弃
        }

        KeyOptPair pair = KeyOptPair.parse(raw);
        String key = pair.getKey();
        String opt = pair.getOpt();

        SqlOperator operator = SqlOperator.keyOf(opt);
        if (operator == null) {
            LOG.warn("filter opt null, opt:{}, raw:{}", opt, raw);
            return QueryFilterColumn.non();
        }

        MetaModelField field = model.getIfPresent(key);
        if (field == null) {
            LOG.debug("filter field null, key:{}, raw:{}", key, raw);
            return QueryFilterColumn.non();
        }

        LOG.debug("filter key:{}, opt:{}, raw:{}", key, opt, raw);
        if (SqlOperator.IN.equals(operator)) {
            List<String> values = StringSplitUtils.split(value);
            return QueryFilterColumn.in(field.getUnderline(), values, field.getType());
        }
        return QueryFilterColumn.of(field.getUnderline(), value, field.getType(), operator);
    }

    public static List<QueryOrderColumn> orders(MetaModel model, String value) {
        List<QueryOrderColumn> columns = new ArrayList<QueryOrderColumn>();
        if (StringKit.isEmpty(value)) {
            return columns;
        }

        List<String> list = StringSplitUtils.split(value);
        for (String src : list) {
            columns.add(order(model, src));
        }
        return columns;
    }

    public static QueryOrderColumn order(MetaModel model, String value) {
        KeyOptPair keyOpt = KeyOptPair.parse(value);
        String key = keyOpt.getKey();
        String opt = keyOpt.getOpt();
        if (StringKit.isEmpty(key) || StringKit.isEmpty(opt)) {
            LOG.warn("doMakeOrder failed, value:{}", value);
            return new QueryOrderColumn();
        }

        if (model.contains(key) == false) {
            LOG.warn("doMakeOrder not field, value:{}", value);
            return new QueryOrderColumn();
        }

        LOG.info("doMakeOrder key:{}, opt:{}, value:{}", key, opt, value);
        if (HttpConstants.SORT_DESC.equals(opt)) {
            return QueryOrderColumn.desc(key);
        }

        if (HttpConstants.SORT_ASC.equals(opt)) {
            return QueryOrderColumn.asc(key);
        }
        return new QueryOrderColumn();
    }
}
