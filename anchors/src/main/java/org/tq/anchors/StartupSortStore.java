package org.tq.anchors;

import java.util.List;
import java.util.Map;

public class StartupSortStore {

    private List<StartUp<?>> result;
    private Map<Class<? extends StartUp>,StartUp<?>> startupMap;
    private Map<Class<? extends StartUp>, List<Class<? extends StartUp>>> startupChildrenMap;

    public StartupSortStore(List<StartUp<?>> result, Map<Class<? extends StartUp>,StartUp<?>> startupMap,
                            Map<Class<? extends StartUp>, List<Class<? extends StartUp>>> startupChildrenMap) {
        this.result = result;
        this.startupMap = startupMap;
        this.startupChildrenMap = startupChildrenMap;
    }

    public List<StartUp<?>> getResult() {
        return result;
    }

    public Map<Class<? extends StartUp>, StartUp<?>> getStartupMap() {
        return startupMap;
    }

    public Map<Class<? extends StartUp>, List<Class<? extends StartUp>>> getStartupChildrenMap() {
        return startupChildrenMap;
    }
}
