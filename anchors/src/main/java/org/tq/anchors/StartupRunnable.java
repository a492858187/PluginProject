package org.tq.anchors;

import android.content.Context;
import android.os.Process;

public class StartupRunnable implements Runnable{

    private Context context;
    private StartUp<?> startUp;
    private StartUpManager startUpManager;

    public StartupRunnable(Context context, StartUp<?> startUp, StartUpManager startUpManager) {
        this.context = context;
        this.startUp = startUp;
        this.startUpManager = startUpManager;
    }

    @Override
    public void run() {
        Process.setThreadPriority(startUp.getThreadPriority());
        startUp.toWait();
        Object result = startUp.create(context);
        StartUpCacheManager.getInstance().saveInitializedComponents(startUp.getClass(), new Result(result));
        startUpManager.notifyChildren(startUp);
    }

}
