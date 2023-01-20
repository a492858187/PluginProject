package org.tq.anchors;

import android.os.Process;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

public abstract class AndroidStartUp<T> implements StartUp<T> {

    private CountDownLatch countDownLatch = new CountDownLatch(getDependenciesCount());

    @Override
    public List<Class<? extends StartUp<?>>> dependencies() {
        return null;
    }

    @Override
    public int getDependenciesCount() {
        List<Class<? extends StartUp<?>>> dependencies = dependencies();
        return dependencies == null ? 0: dependencies.size();
    }

    @Override
    public Executor executor() {
        return ExecutorManager.getIoExecutor();
    }

    @Override
    public void toWait() {
        try{
            countDownLatch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void toNotify() {
        countDownLatch.countDown();
    }

    @Override
    public int getThreadPriority() {
        return Process.THREAD_PRIORITY_DEFAULT;
    }
}
