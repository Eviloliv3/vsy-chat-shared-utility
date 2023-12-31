package de.vsy.shared_utility.async_value_acquisition;

import de.vsy.shared_utility.logging.ThreadContextRunnable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Fetches values, using a generic ValueFetcher, until an expected value is fetched or a timeout is
 * reached, if run as scheduled task. Fetches once per run.
 *
 * @param <T> type of value that will be fetched
 */
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

    /**
     * Returns last fetched value.
     *
     * @return fetched value of generic type
     */
    public T getFetchedValue() {
        this.lock.readLock().lock();

        try {
            return this.currentValue;
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override
    protected void runWithContext() {
        final var now = Instant.now();

        if (now.isBefore(this.terminationTime) && !(this.expectedValue.equals(this.currentValue))) {
            this.lock.writeLock().lock();

            try {
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
            LOGGER.trace("CountDownLatch counted down. Expected value read or timeout" +
                    " reached.");
            this.latch.countDown();
        }
    }
}
