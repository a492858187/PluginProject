package org.tq.pluginproject.mapping;

import java.util.HashMap;
import java.util.Map;

public class RouterMapping_123 {
    public static Map<String, String> get() {
        Map<String, String> mapping = new HashMap<>();
        mapping.put("router://xxx", "org.xxx.xxx.AActivity");
        mapping.put("router://xxxA", "org.xxx.xxx.BActivityA");
        mapping.put("router://xxxB", "org.xxx.xxx.CActivity");
        mapping.put("router://xxxC", "org.xxx.xxx.DActivity");
        return mapping;
    }
}
