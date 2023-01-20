package org.tq.pluginproject.startup;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import org.tq.anchors.AndroidStartUp;
import org.tq.anchors.StartUp;

import java.util.ArrayList;
import java.util.List;

public class Task2 extends AndroidStartUp<Void> {

    static List<Class<? extends StartUp<?>>> depends;

    static {
        depends = new ArrayList<>();
        depends.add(Task1.class);
    }

    @Override
    public Void create(Context context) {
        Log.d("AndroidStartUp", "Task2:学习Socket");
        SystemClock.sleep(800);
        Log.d("AndroidStartUp", "Task2:掌握Socket");
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
