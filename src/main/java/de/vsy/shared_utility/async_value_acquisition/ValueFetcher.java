package de.vsy.shared_utility.async_value_acquisition;

@FunctionalInterface
public interface ValueFetcher<T> {

    /**
     * Gets Value of generic type.
     *
     * @return value of type T
     */
    T getValue();
}
