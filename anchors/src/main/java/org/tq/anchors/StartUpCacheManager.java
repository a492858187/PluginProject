package org.tq.anchors;

import java.util.concurrent.ConcurrentHashMap;

public class StartUpCacheManager {
    private ConcurrentHashMap<Class<? extends StartUp>, Result> mInitializedComponents = new ConcurrentHashMap<>();
    private static volatile StartUpCacheManager mInstance;

    private StartUpCacheManager() {

    }

    public static StartUpCacheManager getInstance() {
        if (mInstance == null) {
            synchronized (StartUpCacheManager.class) {
                if (mInstance == null) {
                    mInstance = new StartUpCacheManager();
                }
            }
        }
        return mInstance;
    }

    public void saveInitializedComponents(Class<? extends StartUp> zClass, Result result) {
        mInitializedComponents.put(zClass, result);
    }

    public boolean hadInitialized(Class<? extends StartUp> zClass) {
        return mInitializedComponents.contains(zClass);
    }

    public <T> Result<T> obtainInitializedResult(Class<? extends StartUp<T>> zClass) {
        return mInitializedComponents.get(zClass);
    }

    public void remove(Class<? extends StartUp> zClass) {
        mInitializedComponents.remove(zClass);
    }

    public void clear() {
        mInitializedComponents.clear();
    }
}
