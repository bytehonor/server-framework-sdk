package com.bytehonor.sdk.server.spring.getter;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.HttpConstants;
import com.bytehonor.sdk.lang.spring.constant.SqlOperator;
import com.bytehonor.sdk.lang.spring.match.KeyMatcher;
import com.bytehonor.sdk.lang.spring.meta.MetaModel;
import com.bytehonor.sdk.lang.spring.meta.MetaModelField;
import com.bytehonor.sdk.lang.spring.meta.MetaModelUtils;
import com.bytehonor.sdk.lang.spring.query.QueryCondition;
import com.bytehonor.sdk.lang.spring.query.QueryOrder;
import com.bytehonor.sdk.lang.spring.string.SpringString;
import com.bytehonor.sdk.lang.spring.string.StringSplitUtils;
import com.google.common.collect.Sets;

/**
 * @author lijianqiang
 *
 */
public class RequestParser {

    private static final Logger LOG = LoggerFactory.getLogger(RequestParser.class);

    private static final Set<String> PAGES = Sets.newHashSet(HttpConstants.COUNT_KEY, HttpConstants.LIMIT_KEY,
            HttpConstants.OFFSET_KEY, HttpConstants.PAGE_KEY);

    private final HttpServletRequest request;
    private QueryOrder order;
    private final List<KeyMatcher> matchers;
    private final MetaModel model;

    public RequestParser(Class<?> clazz, HttpServletRequest request) {
        Objects.requireNonNull(clazz, "clazz");
        Objects.requireNonNull(request, "request");
        this.request = request;
        this.order = null;
        this.matchers = new ArrayList<KeyMatcher>();
        this.model = MetaModelUtils.parse(clazz);
    }

    public static RequestParser create(Class<?> clazz, HttpServletRequest request) {
        return new RequestParser(clazz, request);
    }

    public QueryCondition build() {
        int offset = RequestGetter.offset(request);
        int limit = RequestGetter.limit(request);
        boolean counted = RequestGetter.counted(request);
        Enumeration<String> names = request.getParameterNames();

        while (names.hasMoreElements()) {
            doParse(names.nextElement());
        }

        QueryCondition condition = QueryCondition.and(offset, limit);
        condition.count(counted);

        for (KeyMatcher matcher : matchers) {
            condition.add(matcher);
        }

        condition.setOrder(order);
        return condition;
    }

    private void doParse(String raw) {
        if (PAGES.contains(raw)) {
            return;
        }

        String value = request.getParameter(raw);
        if (HttpConstants.SORT_KEY.equals(raw)) {
            doMakeOrder(value);
            return;
        }

        String key = raw;
        String opt = SqlOperator.EQ.getKey();
        List<String> list = StringSplitUtils.split(raw, '.');
        int size = list.size();
        if (size == 2) {
            key = list.get(0);
            opt = list.get(1);
        }
        LOG.info("doParse key:{}, opt:{}, raw:{}", key, opt, raw);

        MetaModelField field = model.getIfPresent(key);
        if (field == null) {
            LOG.warn("field null, key:{}, raw:{}", key, raw);
            return;
        }

        SqlOperator sopt = SqlOperator.keyOf(opt);

        KeyMatcher matcher = KeyMatcher.of(field.getKey(), value, field.getType(), sopt);
        doAdd(matcher);
    }

    private void doMakeOrder(String value) {
        List<String> list = StringSplitUtils.split(value, '.');
        if (list.size() != 2) {
            LOG.warn("doMakeOrder invalid value:{}", value);
            return;
        }
        String key = list.get(0);
        String opt = list.get(1);
        if (SpringString.isEmpty(key) || SpringString.isEmpty(opt)) {
            LOG.warn("doMakeOrder failed value:{}", value);
            return;
        }
        if (HttpConstants.SORT_DESC.equals(opt)) {
            order = QueryOrder.descOf(key);
            return;
        }

        if (HttpConstants.SORT_ASC.equals(opt)) {
            order = QueryOrder.ascOf(key);
            return;
        }
    }

    private RequestParser doAdd(KeyMatcher matcher) {
        if (matcher != null) {
            this.matchers.add(matcher);
        }
        return this;
    }

}
