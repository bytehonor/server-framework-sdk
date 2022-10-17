package com.bytehonor.sdk.server.spring.getter;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.HttpConstants;
import com.bytehonor.sdk.lang.spring.match.KeyMatcher;
import com.bytehonor.sdk.lang.spring.meta.MetaModel;
import com.bytehonor.sdk.lang.spring.meta.MetaModelField;
import com.bytehonor.sdk.lang.spring.meta.MetaModelUtils;
import com.bytehonor.sdk.lang.spring.query.QueryCondition;
import com.bytehonor.sdk.lang.spring.query.QueryOrder;
import com.bytehonor.sdk.lang.spring.string.SpringString;
import com.google.common.collect.Sets;

/**
 * @author lijianqiang
 *
 */
public class RequestParser {

    private static final Logger LOG = LoggerFactory.getLogger(RequestParser.class);

    private static final Set<String> PAGES = Sets.newHashSet(HttpConstants.LIMIT_KEY, HttpConstants.OFFSET_KEY,
            HttpConstants.PAGE_KEY);

    private static final String EQ = "eq";
    private static final String NEQ = "neq";
    private static final String LT = "lt";
    private static final String ELT = "elt";
    private static final String GT = "gt";
    private static final String EGT = "egt";
    private static final String LIKE = "lk";
    private static final String IN = "in";

    private final HttpServletRequest request;
    private QueryOrder order;
    private final List<KeyMatcher> matchers;

    private final Class<?> clazz;

    public RequestParser(Class<?> clazz, HttpServletRequest request) {
        Objects.requireNonNull(clazz, "clazz");
        Objects.requireNonNull(request, "request");
        this.request = request;
        this.order = null;
        this.matchers = new ArrayList<KeyMatcher>();
        this.clazz = clazz;
    }

    public static RequestParser create(Class<?> clazz, HttpServletRequest request) {
        return new RequestParser(clazz, request);
    }

    public QueryCondition build() {

        MetaModel model = MetaModelUtils.parse(clazz);
        LOG.info("name:{}", model.getName());

        List<MetaModelField> fields = model.getFields();
        for (MetaModelField field : fields) {
            LOG.info("key:{}, column:{}, type:{}", field.getKey(), field.getColumn(), field.getType());
        }

        int offset = RequestGetter.offset(request);
        int limit = RequestGetter.limit(request);
        Enumeration<String> names = request.getParameterNames();
        Set<String> keys = new HashSet<String>();

        while (names.hasMoreElements()) {
            doParse(names.nextElement());
        }

//        for (String key : keys) {
//            if (PAGES.contains(key)) {
//                continue;
//            }
//            if (HttpConstants.SORT_KEY.equals(key)) {
//                doMakeOrder(key);
//                continue;
//            }
//            doMake(key);
//        }

        QueryCondition condition = QueryCondition.and(offset, limit);
        for (KeyMatcher matcher : matchers) {
            condition.add(matcher);
        }

        condition.setOrder(order);
        return condition;
    }

    private void doParse(String key) {
        if (PAGES.contains(key)) {
            return;
        }

        KeyValueAction ka = KeyValueAction.parse(key, request.getAttribute(key));
        doMake(ka);
    }

    private void doMakeOrder(String key) {

    }

    private void doMake(KeyValueAction ka) {
        if (ka == null || SpringString.isEmpty(ka.getKey())) {
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
