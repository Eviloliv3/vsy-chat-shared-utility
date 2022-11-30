package de.vsy.shared_utility.async_value_acquisition;

import de.vsy.shared_utility.logging.ThreadContextRunnable;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TimeBasedValueFetcher<T> extends ThreadContextRunnable {

  private static final Logger LOGGER = LogManager.getLogger();
  private final T expectedValue;
  private final Instant terminationTime;
  private final CountDownLatch latch;
  private final ReadWriteLock lock;
  private final ValueFetcher<T> fetcher;
  private T currentValue;

  public TimeBasedValueFetcher(ValueFetcher<T> fetcher, T expectedValue, Instant terminationTime,
      CountDownLatch latch) {
    this.fetcher = fetcher;
    this.expectedValue = expectedValue;
    this.latch = latch;
    this.terminationTime = terminationTime;
    this.currentValue = null;
    this.lock = new ReentrantReadWriteLock();
  }

  public T getFetchedValue() {
    try {
      this.lock.readLock().lock();
      return this.currentValue;
    } finally {
      this.lock.readLock().unlock();
    }
  }

  @Override
  protected void runWithContext() {
    final var now = Instant.now();

    if (now.isBefore(this.terminationTime)) {
      try {
        this.lock.writeLock().lock();
        this.currentValue = this.fetcher.getValue();

        if (this.currentValue.equals(this.expectedValue)) {
          LOGGER.trace("CountDownLatch counted down. Expected value read: {}/{}",
              this.currentValue,
              this.expectedValue);
          this.latch.countDown();
        }
      } finally {
        this.lock.writeLock().unlock();
      }
    } else {
      LOGGER.trace("CountDownLatch counted down. ValueFetcher timeout reached.");
      this.latch.countDown();
    }
  }
}
