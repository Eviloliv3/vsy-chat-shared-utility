package de.vsy.shared_utility.logging;

import org.apache.logging.log4j.ThreadContext;

import java.util.Map;

public abstract
class ThreadContextRunnable implements Runnable {

    private final Map<String, String> contextMap;

    public
    ThreadContextRunnable () {
        contextMap = ThreadContext.getContext();
    }

    @Override
    public
    void run () {
        if (contextMap != null) {
            ThreadContext.putAll(contextMap);
        }
        try {
            runWithContext();
        } finally {
            ThreadContext.clearAll();
        }
    }

    protected abstract
    void runWithContext ();
}
