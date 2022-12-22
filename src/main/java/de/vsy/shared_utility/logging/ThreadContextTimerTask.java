package de.vsy.shared_utility.logging;

import java.util.Map;
import java.util.TimerTask;
import org.apache.logging.log4j.ThreadContext;

/**
 * Used to manually pass Log4J ThreadContext from parent thread to TimerTask.
 */
public abstract class ThreadContextTimerTask extends TimerTask {

  private final Map<String, String> contextMap;

  public ThreadContextTimerTask() {
    contextMap = ThreadContext.getContext();
  }

  @Override
  public void run() {
    if (contextMap != null) {
      ThreadContext.putAll(contextMap);
    }
    try {
      runWithContext();
    } finally {
      ThreadContext.clearAll();
    }
  }

  protected abstract void runWithContext();
}
