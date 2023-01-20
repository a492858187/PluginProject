package org.tq.anchors;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StartupInitializer {

    public static String META_VALUE = "android.startup";

    public static List<StartUp<?>> discoverAndInitialize(Context context, String providerName)
            throws Exception {
        Map<Class<? extends StartUp>, StartUp<?>> startups = new HashMap<>();
        ComponentName provider = new ComponentName(context, providerName);
        ProviderInfo providerInfo = context.getPackageManager().getProviderInfo(provider, PackageManager.GET_META_DATA);
        for(String key:providerInfo.metaData.keySet()){
            String value = providerInfo.metaData.getString(key);
            if(TextUtils.equals(META_VALUE, value)) {
                Class<?> clazz = Class.forName(key);
                if(StartUp.class.isAssignableFrom(clazz)) {
                    doInitialize((StartUp<?>) clazz.newInstance(), startups);
                }
            }
        }

        List<StartUp<?>> result = new ArrayList<>(startups.values());
        return result;
    }

    public static void doInitialize(StartUp<?> startup, Map<Class<? extends StartUp>, StartUp<?>> startups)
            throws IllegalAccessException, InstantiationException {
        startups.put(startup.getClass(), startup);
        if(startup.getDependenciesCount() != 0) {
            for(Class<? extends StartUp> dependency: startup.dependencies()) {
                doInitialize(dependency.newInstance(), startups);
            }
        }
    }
}
