package com.bytehonor.sdk.server.spring.getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.HttpConstants;
import com.bytehonor.sdk.lang.spring.constant.QueryLogic;
import com.bytehonor.sdk.lang.spring.constant.SqlOperator;
import com.bytehonor.sdk.lang.spring.core.KeyValueMap;
import com.bytehonor.sdk.lang.spring.getter.BooleanGetter;
import com.bytehonor.sdk.lang.spring.getter.IntegerGetter;
import com.bytehonor.sdk.lang.spring.match.KeyMatcher;
import com.bytehonor.sdk.lang.spring.meta.MetaModel;
import com.bytehonor.sdk.lang.spring.meta.MetaModelField;
import com.bytehonor.sdk.lang.spring.meta.MetaModelUtils;
import com.bytehonor.sdk.lang.spring.query.QueryCondition;
import com.bytehonor.sdk.lang.spring.query.QueryOrder;
import com.bytehonor.sdk.lang.spring.query.QueryPager;
import com.bytehonor.sdk.lang.spring.string.SpringString;
import com.bytehonor.sdk.lang.spring.string.StringSplitUtils;
import com.google.common.collect.Sets;

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

    public static QueryCondition and(Class<?> clazz, HttpServletRequest request) {
        KeyValueMap map = RequestGetter.map(request);
        return condition(clazz, QueryLogic.AND, map);
    }

    public static QueryCondition or(Class<?> clazz, HttpServletRequest request) {
        KeyValueMap map = RequestGetter.map(request);
        return condition(clazz, QueryLogic.OR, map);
    }

    public static QueryCondition condition(Class<?> clazz, QueryLogic logic, KeyValueMap map) {
        Objects.requireNonNull(logic, "logic");
        Objects.requireNonNull(clazz, "clazz");
        Objects.requireNonNull(map, "map");

        MetaModel model = MetaModelUtils.parse(clazz);

        QueryCondition condition = QueryCondition.create(logic, pager(map));

        List<KeyMatcher> matchers = matchers(model, map.map());
        for (KeyMatcher matcher : matchers) {
            condition.add(matcher);
        }

        condition.order(order(model, map.get(HttpConstants.SORT_KEY)));
        return condition;
    }

    /**
     * page和offset都存在时，offset会覆盖page
     * 
     * @param request
     * @return
     */
    public static QueryPager pager(KeyValueMap map) {
        Objects.requireNonNull(map, "map");

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

    public static List<KeyMatcher> matchers(MetaModel model, Map<String, String> map) {
        if (map.isEmpty()) {
            return new ArrayList<KeyMatcher>();
        }
        List<KeyMatcher> matchers = new ArrayList<KeyMatcher>(map.size() * 2);
        for (Entry<String, String> entry : map.entrySet()) {
            if (IGNORES.contains(entry.getKey())) {
                continue;
            }
            matchers.add(matcher(model, entry.getKey(), entry.getValue()));
        }
        return matchers;
    }

    public static KeyMatcher matcher(MetaModel model, String raw, String value) {
        if (SpringString.isEmpty(raw) || SpringString.isEmpty(value)) {
            return KeyMatcher.non(); // value为空字符则丢弃
        }

        KeyOptPair pair = KeyOptPair.parse(raw);
        String key = pair.getKey();
        String opt = pair.getOpt();

        SqlOperator operator = SqlOperator.keyOf(opt);
        if (operator == null) {
            LOG.warn("doMakeMatcher opt null, opt:{}, raw:{}", opt, raw);
            return KeyMatcher.non();
        }

        MetaModelField field = model.getIfPresent(key);
        if (field == null) {
            LOG.debug("doMakeMatcher field null, key:{}, raw:{}", key, raw);
            return KeyMatcher.non();
        }

        LOG.debug("doMakeMatcher key:{}, opt:{}, raw:{}", key, opt, raw);
        if (SqlOperator.IN.equals(operator)) {
            List<String> values = StringSplitUtils.split(value);
            return KeyMatcher.in(field.getKey(), values, field.getType());
        }
        return KeyMatcher.of(field.getKey(), value, field.getType(), operator);
    }

    public static QueryOrder order(MetaModel model, String value) {
        if (SpringString.isEmpty(value)) {
            return null;
        }

        KeyOptPair keyOpt = KeyOptPair.parse(value);
        String key = keyOpt.getKey();
        String opt = keyOpt.getOpt();
        if (SpringString.isEmpty(key) || SpringString.isEmpty(opt)) {
            LOG.warn("doMakeOrder failed, value:{}", value);
            return null;
        }

        if (model.contains(key) == false) {
            LOG.warn("doMakeOrder not field, value:{}", value);
            return null;
        }

        LOG.info("doMakeOrder key:{}, opt:{}, value:{}", key, opt, value);
        if (HttpConstants.SORT_DESC.equals(opt)) {
            return QueryOrder.descOf(key);
        }

        if (HttpConstants.SORT_ASC.equals(opt)) {
            return QueryOrder.ascOf(key);
        }
        return null;
    }
}
