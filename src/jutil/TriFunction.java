package jutil;

/**
 * Represents a function that accepts 3 arguments and produces a result.
 */
public interface TriFunction<T, U, E, R> {
    /**
     * Applies the function with the given parameters and returns a result
     * 
     * @param arg0 first parameter
     * @param arg1 second parameter
     * @param arg2 third parameter
     * @return the result of the function
     */
    R apply(T arg0, U arg1, E arg2);
}