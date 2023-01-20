package org.tq.anchors;

import android.content.Context;

import java.util.List;

public interface StartUp <T> extends Dispatcher{

    T create(Context context);

    List<Class<? extends StartUp<?>>> dependencies();

    int getDependenciesCount();

}
