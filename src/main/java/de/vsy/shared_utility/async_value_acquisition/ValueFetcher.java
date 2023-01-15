package de.vsy.shared_utility.async_value_acquisition;

@FunctionalInterface
public interface ValueFetcher<T> {

    /**
     * Gets value of specific type.
     *
     * @return value of specific type
     */
    T getValue();
}
