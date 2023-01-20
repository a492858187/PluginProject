package org.tq.pluginproject.startup;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import org.tq.anchors.AndroidStartUp;
import org.tq.anchors.StartUp;

import java.util.ArrayList;
import java.util.List;

public class Task5 extends AndroidStartUp<Void> {

    static List<Class<? extends StartUp<?>>> depends;

    static {
        depends = new ArrayList<>();
        depends.add(Task3.class);
        depends.add(Task4.class);
    }

    @Override
    public Void create(Context context) {
        Log.d("AndroidStartUp", "Task5:学习Okhttp");
        SystemClock.sleep(500);
        Log.d("AndroidStartUp", "Task5:掌握Okhttp");
        return null;
    }

    @Override
    public List<Class<? extends StartUp<?>>> dependencies() {
        return depends;
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
