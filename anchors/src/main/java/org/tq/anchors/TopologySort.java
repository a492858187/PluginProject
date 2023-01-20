package org.tq.anchors;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopologySort {


    public static StartupSortStore sort(List<? extends StartUp<?>> startupList) {
        Map<Class<? extends StartUp>, Integer> inDegreeMap = new HashMap<>();
        Deque<Class<? extends StartUp>> zeroDeque = new ArrayDeque<>();

        Map<Class<? extends StartUp>, StartUp<?>> startupMap = new HashMap<>();
        Map<Class<? extends StartUp>, List<Class<? extends StartUp>>> startupChildrenMap = new HashMap<>();

        for (StartUp<?> startup : startupList) {
            startupMap.put(startup.getClass(), startup);
            int dependenciesCount = startup.getDependenciesCount();
            inDegreeMap.put(startup.getClass(), dependenciesCount);
            if(dependenciesCount == 0){
                zeroDeque.offer(startup.getClass());
            }else{
                for(Class<? extends StartUp<?>> parent : startup.dependencies()){
                    List<Class<? extends StartUp>> children = startupChildrenMap.get(parent);
                    if(children == null) {
                        children = new ArrayList<>();
                        startupChildrenMap.put(parent, children);
                    }
                    children.add(startup.getClass());
                }
            }
        }

        List<StartUp<?>> main = new ArrayList<>();
        List<StartUp<?>> threads = new ArrayList<>();
        List<StartUp<?>> result = new ArrayList<>();
        while(!zeroDeque.isEmpty()) {
            Class<? extends StartUp> cls = zeroDeque.poll();
            StartUp<?> startUp = startupMap.get(cls);

            if(startUp.callCreateOnMainThread()){
                main.add(startUp);
            }else{
                threads.add(startUp);
            }

            if(startupChildrenMap.containsKey(cls)) {
                List<Class<? extends StartUp>> childStartUp = startupChildrenMap.get(cls);
                for(Class<? extends StartUp> childCls : childStartUp) {
                    Integer num = inDegreeMap.get(childCls);
                    inDegreeMap.put(childCls, num - 1);
                    if(num - 1 == 0) {
                        zeroDeque.offer(childCls);
                    }
                }
            }
        }
        result.addAll(threads);
        result.addAll(main);
        return new StartupSortStore(result, startupMap, startupChildrenMap);

    }

}
