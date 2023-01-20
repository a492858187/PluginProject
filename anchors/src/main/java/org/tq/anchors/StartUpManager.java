package org.tq.anchors;

import android.content.Context;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class StartUpManager {

    private Context context;
    private List<StartUp<?>> startUpList;
    private StartupSortStore startupSortStore;
    private AtomicInteger needAwaitCount;
    public CountDownLatch awaitCountDownLatch;

    public StartUpManager(Context context, AtomicInteger needAwaitCount, List<StartUp<?>> startUpList) {
        this.needAwaitCount = needAwaitCount;
        this.context = context;
        this.startUpList = startUpList;
    }

    public StartUpManager start() {
        if(Looper.myLooper() != Looper.getMainLooper()) {
            throw new RuntimeException("请在主线程调用！");
        }
        awaitCountDownLatch = new CountDownLatch(needAwaitCount.get());
        startupSortStore = TopologySort.sort(startUpList);
        for(StartUp<?> startUp : startupSortStore.getResult()) {
            StartupRunnable startupRunnable = new StartupRunnable(context, startUp, this);
            if(startUp.callCreateOnMainThread()){
                startupRunnable.run();
            }else{
                startUp.executor().execute(startupRunnable);
            }
//            Object result = startUp.create(context);
//            StartUpCacheManager.getInstance().saveInitializedComponents(startUp.getClass(), new Result(result));
        }
        return this;
    }

    public void await() {
        try{
            awaitCountDownLatch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void notifyChildren(StartUp<?> startUp){
        if(!startUp.callCreateOnMainThread() && startUp.waitOnMainThread()){
            awaitCountDownLatch.countDown();
        }

        if(startupSortStore.getStartupChildrenMap().containsKey(startUp.getClass())) {
            List<Class<? extends StartUp>> childStartupCls = startupSortStore.getStartupChildrenMap().get(startUp.getClass());
            for(Class<? extends StartUp> cls : childStartupCls) {
                StartUp<?> childStartup = startupSortStore.getStartupMap().get(cls);
                childStartup.toNotify();
            }
        }
    }

    public static class Builder {
        private List<StartUp<?>> startUpList = new ArrayList<>();
        private AtomicInteger mNeedAwaitCount = new AtomicInteger();

        public Builder addStartup(StartUp<?> startUp) {
            startUpList.add(startUp);
            return this;
        }

        public Builder addAllStartup(List<StartUp<?>> startUps) {
            startUpList.addAll(startUps);
            return this;
        }

        public StartUpManager build(Context context) {
            for(StartUp<?> startUp:startUpList){
                if (startUp.waitOnMainThread() && !startUp.callCreateOnMainThread()) {
                    mNeedAwaitCount.incrementAndGet();
                }
            }
            return new StartUpManager(context, mNeedAwaitCount, startUpList);
        }
    }
}
