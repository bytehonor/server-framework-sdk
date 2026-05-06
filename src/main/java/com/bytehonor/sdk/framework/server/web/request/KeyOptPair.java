package com.bytehonor.sdk.framework.server.web.request;

import com.bytehonor.sdk.framework.lang.constant.SqlOperator;
import com.bytehonor.sdk.framework.lang.string.StringKit;

/**
 * 查询参数中的“字段+操作符”组合模型。
 * 
 * @author lijianqiang
 */
public class KeyOptPair {

    /**
     * 字段与操作符的分隔符。
     */
    public static final String SPL = "__";

    /**
     * 默认操作符：等于。
     */
    public static final String EQ = SqlOperator.EQ.getKey();

    private String key;

    private String opt;

    public KeyOptPair() {
        this.key = "";
        this.opt = EQ;
    }

    /**
     * 组装字段与操作符字符串。
     * 
     * @param key 字段名
     * @param opt 操作符
     * @return key__opt 格式字符串
     */
    public static String make(String key, String opt) {
        return new StringBuilder().append(key).append(SPL).append(opt).toString();
    }

    /**
     * 解析字段与操作符组合字符串。
     * 
     * @param raw 原始字符串
     * @return 解析后的对象，空值时返回默认对象
     */
    public static KeyOptPair parse(String raw) {
        KeyOptPair model = new KeyOptPair();
        if (StringKit.isEmpty(raw)) {
            return model;
        }
        int diff = 2;
        int at = raw.indexOf(SPL);
        if (at > 1) {
            int length = raw.length();
            model.setKey(raw.substring(0, at));
            model.setOpt(raw.substring(at + diff, length));
        } else {
            model.setKey(raw);
        }
        return model;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

}
