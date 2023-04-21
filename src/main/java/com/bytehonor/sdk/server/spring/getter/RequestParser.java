package com.bytehonor.sdk.server.spring.getter;

import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.HttpConstants;
import com.bytehonor.sdk.lang.spring.constant.QueryLogic;
import com.bytehonor.sdk.lang.spring.constant.SqlOperator;
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

    public static QueryCondition and(HttpServletRequest request) {
        return QueryCondition.create(QueryLogic.AND, doMakePager(request));
    }

    public static QueryCondition or(HttpServletRequest request) {
        return QueryCondition.create(QueryLogic.OR, doMakePager(request));
    }

    public static QueryCondition and(Class<?> clazz, HttpServletRequest request) {
        return doParse(QueryLogic.AND, clazz, request);
    }

    public static QueryCondition or(Class<?> clazz, HttpServletRequest request) {
        return doParse(QueryLogic.OR, clazz, request);
    }

    /**
     * page和offset都存在则优先page
     * 
     * @param request
     * @return
     */
    private static QueryPager doMakePager(HttpServletRequest request) {
        Objects.requireNonNull(request, "request");

        int limit = RequestGetter.limit(request);
        int offset = HttpConstants.OFFSET_DEF;
        int page = RequestGetter.page(request);
        if (page > 1) {
            offset = (page - 1) * limit;
        } else {
            String offsetVal = RequestGetter.optional(request, HttpConstants.OFFSET_KEY);
            if (SpringString.isEmpty(offsetVal) == false) {
                offset = IntegerGetter.optional(offsetVal, HttpConstants.OFFSET_DEF);
            }
        }

        boolean counted = true;
        if (offset > 0) {
            counted = RequestGetter.counted(request);
        }
        return QueryPager.of(counted, offset, limit);
    }

    private static QueryCondition doParse(QueryLogic logic, Class<?> clazz, HttpServletRequest request) {
        Objects.requireNonNull(logic, "logic");
        Objects.requireNonNull(clazz, "clazz");
        Objects.requireNonNull(request, "request");

        QueryCondition condition = QueryCondition.create(logic, doMakePager(request));

        MetaModel model = MetaModelUtils.parse(clazz);
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String key = names.nextElement();
            if (IGNORES.contains(key)) {
                continue;
            }
            condition.add(doMakeMatcher(model, key, request.getParameter(key)));
        }

        condition.order(doMakeOrder(model, request.getParameter(HttpConstants.SORT_KEY)));
        return condition;
    }

    private static KeyMatcher doMakeMatcher(MetaModel model, String raw, String value) {
        if (SpringString.isEmpty(raw) || SpringString.isEmpty(value)) {
            return KeyMatcher.non(); // value为空字符则丢弃
        }

        RequestKeyOpt keyOpt = RequestKeyOpt.parse(raw);
        String key = keyOpt.getKey();
        String opt = keyOpt.getOpt();

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

    private static QueryOrder doMakeOrder(MetaModel model, String value) {
        if (SpringString.isEmpty(value)) {
            return null;
        }

        RequestKeyOpt keyOpt = RequestKeyOpt.parse(value);
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
