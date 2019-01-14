package com.bytehonor.sdk.server.spring.constant;

public class HttpConstants {
	
	public static final String COUNT_KEY = "$count";
	
	public static final String LIMIT_KEY = "$limit";
	
	public static final Integer LIMIT_DEF = 20;

    public static final Integer LIMIT_MAX = 200;
    
    public static final Integer LIMIT_MAX_TOP = 5000;

    public static final String OFFSET_KEY = "$offset";

    public static final int OFFSET_DEFAULT = 0;

    public static final String ORDER_KEY = "order";

    public static final String ORDER_DEFAULT = "id";
    
    public static final String SORT_KEY = "sort";
    
    public static final String SORT_ASC = "asc";
    
    public static final String SORT_DESC = "desc";

    public static final String PAGE_KEY = "page";

}
