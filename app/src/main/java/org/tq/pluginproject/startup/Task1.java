package org.tq.pluginproject.startup;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import org.tq.anchors.AndroidStartUp;

public class Task1 extends AndroidStartUp<Void> {

    @Override
    public Void create(Context context) {
        Log.d("AndroidStartUp", "Task1:学习Java基础");
        SystemClock.sleep(3000);
        Log.d("AndroidStartUp", "Task1:掌握Java基础");
        return null;
    }

    @Override
    public int getDependenciesCount() {
        return super.getDependenciesCount();
    }

    @Override
    public boolean callCreateOnMainThread() {
        return false;
    }

    @Override
    public boolean waitOnMainThread() {
        return false;
    }
}
