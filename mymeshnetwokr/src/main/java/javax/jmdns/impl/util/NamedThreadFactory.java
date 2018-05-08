// Copyright 2003-2012 Arthur van Hoff, Rick Blair
// Licensed under Apache License version 2.0
// Original license LGPL

package javax.jmdns.impl.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


public class NamedThreadFactory implements ThreadFactory {
    private final ThreadFactory _delegate;
    private final String _namePrefix;

    /**
     * Constructs the thread factory.
     *
     * @param namePrefix a prefix to append to thread names (will be separated from the default thread name by a space.)
     */
    public NamedThreadFactory(String namePrefix) {
        this._namePrefix = namePrefix;
        _delegate = Executors.defaultThreadFactory();
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = _delegate.newThread(runnable);
        thread.setName(_namePrefix + ' ' + thread.getName());
        return thread;
    }
}
