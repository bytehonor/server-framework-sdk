package com.bytehonor.sdk.server.spring.getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import com.bytehonor.sdk.lang.spring.function.ClassGetter;
import com.bytehonor.sdk.lang.spring.function.Getters;
import com.bytehonor.sdk.lang.spring.function.getter.GetBoolean;
import com.bytehonor.sdk.lang.spring.function.getter.GetInteger;
import com.bytehonor.sdk.lang.spring.function.getter.GetLong;
import com.bytehonor.sdk.lang.spring.function.getter.GetString;
import com.bytehonor.sdk.lang.spring.match.KeyMatcher;
import com.bytehonor.sdk.lang.spring.query.QueryCondition;
import com.bytehonor.sdk.lang.spring.query.QueryOrder;

/**
 * @author lijianqiang
 *
 */
public class RequestBuilder {

    private static final String NEQ = "_neq";
    private static final String LT = "_lt";
    private static final String ELT = "_elt";
    private static final String GT = "_gt";
    private static final String EGT = "_egt";
    private static final String LK = "_lk";
    private static final String IN = "_in";

    private final HttpServletRequest request;
    private QueryOrder order;
    private final List<KeyMatcher> matchers;

    public RequestBuilder(HttpServletRequest request) {
        Objects.requireNonNull(request, "request");
        this.request = request;
        this.order = null;
        this.matchers = new ArrayList<KeyMatcher>();
    }

    public static RequestBuilder create(HttpServletRequest request) {
        Objects.requireNonNull(request, "request");

        return new RequestBuilder(request);
    }

    public QueryCondition build() {
        int offset = RequestGetter.offset(request);
        int limit = RequestGetter.limit(request);

        QueryCondition condition = QueryCondition.and(offset, limit);
        for (KeyMatcher matcher : matchers) {
            condition.add(matcher);
        }

        condition.setOrder(order);
        return condition;
    }

    private RequestBuilder doAdd(KeyMatcher matcher) {
        if (matcher != null) {
            this.matchers.add(matcher);
        }
        return this;
    }

    /**
     * 等于
     * 
     * @param key
     * @param def
     * @return
     */
    public <T> RequestBuilder eq(GetString<T> getter, String def) {
        String field = Getters.field(getter);
        return this.doAdd(KeyMatcher.eq(field, RequestGetter.stringOptional(request, field, def)));
    }

    public <T> RequestBuilder eq(GetString<T> getter) {
        return eq(getter, null);
    }

    /**
     * 等于
     * 
     * @param key
     * @param def
     * @return
     */
    public <T> RequestBuilder eq(GetLong<T> getter, Long def) {
        String field = Getters.field(getter);
        return this.doAdd(KeyMatcher.eq(field, RequestGetter.longOptional(request, field, def)));
    }

    public <T> RequestBuilder eq(GetLong<T> getter) {
        return eq(getter, null);
    }

    /**
     * 等于
     * 
     * @param key
     * @param def
     * @return
     */
    public <T> RequestBuilder eq(GetInteger<T> getter, Integer def) {
        String field = Getters.field(getter);
        return this.doAdd(KeyMatcher.eq(field, RequestGetter.integerOptional(request, field, def)));
    }

    public <T> RequestBuilder eq(GetInteger<T> getter) {
        return eq(getter, null);
    }

    /**
     * 等于
     * 
     * @param key
     * @param def
     * @return
     */
    public <T> RequestBuilder eq(GetBoolean<T> getter, Boolean def) {
        String field = Getters.field(getter);
        return this.doAdd(KeyMatcher.eq(field, RequestGetter.booleanOptional(request, field, def)));
    }

    public <T> RequestBuilder eq(GetBoolean<T> getter) {
        return eq(getter, null);
    }

    /**
     * 不等于
     * 
     * @param key
     * @param def
     * @return
     */
    public <T> RequestBuilder neq(GetString<T> getter, String def) {
        String field = Getters.field(getter);
        return this.doAdd(KeyMatcher.neq(field, RequestGetter.stringOptional(request, field + NEQ, def)));
    }

    public <T> RequestBuilder neq(GetString<T> getter) {
        return neq(getter, null);
    }

    /**
     * 不等于
     * 
     * @param key
     * @param def
     * @return
     */
    public <T> RequestBuilder neq(GetLong<T> getter, Long def) {
        String field = Getters.field(getter);
        return this.doAdd(KeyMatcher.neq(field, RequestGetter.longOptional(request, field + NEQ, def)));
    }

    public <T> RequestBuilder neq(GetLong<T> getter) {
        return neq(getter, null);
    }

    /**
     * 不等于
     * 
     * @param key
     * @param def
     * @return
     */
    public <T> RequestBuilder neq(GetInteger<T> getter, Integer def) {
        String field = Getters.field(getter);
        return this.doAdd(KeyMatcher.neq(field, RequestGetter.integerOptional(request, field + NEQ, def)));
    }

    public <T> RequestBuilder neq(GetInteger<T> getter) {
        return neq(getter, null);
    }

    /**
     * 不等于
     * 
     * @param key
     * @param def
     * @return
     */
    public <T> RequestBuilder neq(GetBoolean<T> getter, Boolean def) {
        String field = Getters.field(getter);
        return this.doAdd(KeyMatcher.neq(field, RequestGetter.booleanOptional(request, field + NEQ, def)));
    }

    public <T> RequestBuilder neq(GetBoolean<T> getter) {
        return neq(getter, null);
    }

    /**
     * 大于
     * 
     * @param key
     * @param def
     * @return
     */
    public <T> RequestBuilder gt(GetLong<T> getter, Long def) {
        String field = Getters.field(getter);
        return this.doAdd(KeyMatcher.gt(field, RequestGetter.longOptional(request, field + GT, def)));
    }

    public <T> RequestBuilder gt(GetLong<T> getter) {
        return gt(getter, null);
    }

    /**
     * 大于
     * 
     * @param key
     * @param def
     * @return
     */
    public <T> RequestBuilder gt(GetInteger<T> getter, Integer def) {
        String field = Getters.field(getter);
        return this.doAdd(KeyMatcher.gt(field, RequestGetter.integerOptional(request, field + GT, def)));
    }

    public <T> RequestBuilder gt(GetInteger<T> getter) {
        return gt(getter, null);
    }

    /**
     * 大于等于
     * 
     * @param key
     * @param def
     * @return
     */
    public <T> RequestBuilder egt(GetLong<T> getter, Long def) {
        String field = Getters.field(getter);
        return this.doAdd(KeyMatcher.egt(field, RequestGetter.longOptional(request, field + EGT, def)));
    }

    public <T> RequestBuilder egt(GetLong<T> getter) {
        return egt(getter, null);
    }

    /**
     * 大于等于
     * 
     * @param key
     * @param def
     * @return
     */
    public <T> RequestBuilder egt(GetInteger<T> getter, Integer def) {
        String field = Getters.field(getter);
        return this.doAdd(KeyMatcher.egt(field, RequestGetter.integerOptional(request, field + EGT, def)));
    }

    public <T> RequestBuilder egt(GetInteger<T> getter) {
        return egt(getter, null);
    }

    /**
     * 小于
     * 
     * @param key
     * @param def
     * @return
     */
    public <T> RequestBuilder lt(GetLong<T> getter, Long def) {
        String field = Getters.field(getter);
        return this.doAdd(KeyMatcher.lt(field, RequestGetter.longOptional(request, field + LT, def)));
    }

    public <T> RequestBuilder lt(GetLong<T> getter) {
        return lt(getter, null);
    }

    /**
     * 小于
     * 
     * @param key
     * @param def
     * @return
     */
    public <T> RequestBuilder lt(GetInteger<T> getter, Integer def) {
        String field = Getters.field(getter);
        return this.doAdd(KeyMatcher.lt(field, RequestGetter.integerOptional(request, field + LT, def)));
    }

    public <T> RequestBuilder lt(GetInteger<T> getter) {
        return lt(getter, null);
    }

    /**
     * 小于等于
     * 
     * @param key
     * @param def
     * @return
     */
    public <T> RequestBuilder elt(GetLong<T> getter, Long def) {
        String field = Getters.field(getter);
        return this.doAdd(KeyMatcher.elt(field, RequestGetter.longOptional(request, field + ELT, def)));
    }

    public <T> RequestBuilder elt(GetLong<T> getter) {
        return elt(getter, null);
    }

    /**
     * 小于等于
     * 
     * @param key
     * @param def
     * @return
     */
    public <T> RequestBuilder elt(GetInteger<T> getter, Integer def) {
        String field = Getters.field(getter);
        return this.doAdd(KeyMatcher.elt(field, RequestGetter.integerOptional(request, field + ELT, def)));
    }

    public <T> RequestBuilder elt(GetInteger<T> getter) {
        return elt(getter, null);
    }

    public <T> RequestBuilder like(GetString<T> getter, String def) {
        String field = Getters.field(getter);
        return this.doAdd(KeyMatcher.like(field, RequestGetter.stringOptional(request, field + LK, def)));
    }

    public <T> RequestBuilder ins(GetString<T> getter) {
        String field = Getters.field(getter);
        String value = RequestGetter.stringOptional(request, field + IN);
        return this.doAdd(KeyMatcher.strings(field, RequestGetter.strings(value)));
    }

    public <T> RequestBuilder inl(GetLong<T> getter) {
        String field = Getters.field(getter);
        String value = RequestGetter.stringOptional(request, field + IN);
        return this.doAdd(KeyMatcher.longs(field, RequestGetter.longs(value)));
    }

    public <T> RequestBuilder ini(GetInteger<T> getter) {
        String field = Getters.field(getter);
        String value = RequestGetter.stringOptional(request, field + IN);
        return this.doAdd(KeyMatcher.integers(field, RequestGetter.integers(value)));
    }

    public <T> RequestBuilder desc(ClassGetter<T, ?> getter) {
        String field = Getters.field(getter);
        this.order = QueryOrder.descOf(field);
        return this;
    }

    public <T> RequestBuilder asc(ClassGetter<T, ?> getter) {
        String field = Getters.field(getter);
        this.order = QueryOrder.descOf(field);
        return this;
    }
}
