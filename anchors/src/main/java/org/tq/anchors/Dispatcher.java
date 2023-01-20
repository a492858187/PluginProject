package org.tq.anchors;

import java.util.concurrent.Executor;

public interface Dispatcher {

    boolean callCreateOnMainThread();

    boolean waitOnMainThread();

    void toWait();

    void toNotify();

    Executor executor();

    int getThreadPriority();

}
