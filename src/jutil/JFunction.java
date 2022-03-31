package jutil;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Utility class for functions
 */
public final class JFunction {
    private JFunction() {
    }

    /**
     * Creates a partial function of {@code biFunction}
     * <p>
     * The returned function is applied with {@code arg0} as the default first
     * parameter.
     * 
     * @param <T>        type of the first parameter
     * @param <U>        type of the second parameter
     * @param <R>        return type
     * @param biFunction the function to get partial for
     * @param arg0       the first argument to set as default
     * @return partial of {@code biFunction} applied with {@code arg0} as the first
     *         argument
     */
    public static <T, U, R> Function<U, R> partial(BiFunction<T, U, R> biFunction, T arg0) {
        return (arg1) -> biFunction.apply(arg0, arg1);
    }

    /**
     * Creates a partial function of {@code triFunction}
     * <p>
     * The returned function is applied with {@code arg0} and {@code arg1}
     * as the default first two parameters.
     * 
     * @param <T>         type of the first parameter
     * @param <U>         type of the second parameter
     * @param <E>         type of the third parameter
     * @param <R>         return type
     * @param triFunction the function to create partial for
     * @param arg0        the first argument to set as default
     * @param arg1        the second argument to set as default
     * @return partial of {@code triFunction} applied with {@code arg0} and
     *         {@code arg1}
     *         as the first two arguments
     */
    public static <T, U, E, R> Function<E, R> partial(TriFunction<T, U, E, R> triFunction, T arg0, U arg1) {
        return (arg2) -> triFunction.apply(arg0, arg1, arg2);
    }

    /**
     * Creates a partial function of {@code triFunction}
     * <p>
     * The returned function is applied with {@code arg0} as the default first
     * parameter.
     * 
     * @param <T>         type of the first parameter
     * @param <U>         type of the second parameter
     * @param <E>         type of the third parameter
     * @param <R>         the return type
     * @param triFunction the function to create partial for
     * @param arg0        the first argument to set as default
     * @return partial of {@code triFunction} applied with {@code arg0} as the first
     *         argument
     */
    public static <T, U, E, R> BiFunction<U, E, R> partial(TriFunction<T, U, E, R> triFunction, T arg0) {
        return (arg1, arg2) -> triFunction.apply(arg0, arg1, arg2);
    }
}