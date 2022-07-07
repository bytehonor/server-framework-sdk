package com.bytehonor.sdk.server.spring.start;

import java.util.ArrayList;
import java.util.List;

public class SpringServerListenerFactory {

    private static final List<SpringServerListener> LISTENERS = new ArrayList<SpringServerListener>();

    public static void register(SpringServerListener listener) {
        if (listener == null) {
            return;
        }
    }

    public static List<SpringServerListener> list() {
        return LISTENERS;
    }
}
