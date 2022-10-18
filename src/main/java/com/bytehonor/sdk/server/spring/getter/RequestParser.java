package com.bytehonor.sdk.server.spring.getter;

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

    private static final Set<String> IGNORES = Sets.newHashSet(HttpConstants.COUNT_KEY, HttpConstants.LIMIT_KEY,
            HttpConstants.OFFSET_KEY, HttpConstants.PAGE_KEY, HttpConstants.SORT_KEY, "token");

    public static QueryCondition parse(Class<?> clazz, HttpServletRequest request) {
        Objects.requireNonNull(clazz, "clazz");
        Objects.requireNonNull(request, "request");

        int offset = RequestGetter.offset(request);
        int limit = RequestGetter.limit(request);
        boolean counted = RequestGetter.counted(request);

        QueryCondition condition = QueryCondition.and(offset, limit);
        condition.count(counted);

        MetaModel model = MetaModelUtils.parse(clazz);
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String key = names.nextElement();
            if (IGNORES.contains(key)) {
                continue;
            }
            condition.add(doMakeMatcher(model, key, request.getParameter(key)));
        }

        condition.setOrder(doMakeOrder(request.getParameter(HttpConstants.SORT_KEY)));
        return condition;
    }

    private static KeyMatcher doMakeMatcher(MetaModel model, String raw, String value) {
        if (SpringString.isEmpty(raw)) {
            return null;
        }
        String key = raw;
        String opt = SqlOperator.EQ.getKey();
        List<String> list = StringSplitUtils.split(raw, '.');
        int size = list.size();
        if (size == 2) {
            key = list.get(0);
            opt = list.get(1);
        }
        LOG.info("doMakeMatcher key:{}, opt:{}, raw:{}", key, opt, raw);

        MetaModelField field = model.getIfPresent(key);
        if (field == null) {
            LOG.warn("doMakeMatcher field null, key:{}, raw:{}", key, raw);
            return null;
        }

        SqlOperator sopt = SqlOperator.keyOf(opt);

        return KeyMatcher.of(field.getKey(), value, field.getType(), sopt);
    }

    private static QueryOrder doMakeOrder(String value) {
        if (SpringString.isEmpty(value)) {
            return null;
        }
        List<String> list = StringSplitUtils.split(value, '.');
        if (list.size() != 2) {
            LOG.warn("doMakeOrder invalid value:{}", value);
            return null;
        }
        String key = list.get(0);
        String opt = list.get(1);
        if (SpringString.isEmpty(key) || SpringString.isEmpty(opt)) {
            LOG.warn("doMakeOrder failed value:{}", value);
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
