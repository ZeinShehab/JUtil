package jutil;

import static java.lang.Math.log;

public final class JMath {
    private JMath() {
    }

    private static final double LN_2 = log(2);

    /**
     * Returns the floor of base 2 logarithm of {@code x}
     * 
     * @param x postive value
     * @return floor of base 2 log of x
     */
    public static int log2(int x) {
        if (x <= 0)
            throw new IllegalArgumentException();
        return 31 - Integer.numberOfLeadingZeros(x);
    }

    /**
     * Returns the ceil of base 2 logarithm of {@code x}
     * 
     * @param x postive value
     * @return ceil of base 2 log of x
     */
    public static int log2Ceil(int x) {
        if (x <= 0)
            throw new IllegalArgumentException();
        return 32 - Integer.numberOfLeadingZeros(x);
    }

    /**
     * Returns the floor of base 2 logarithm of {@code x}
     * 
     * @param x postive value
     * @return floor of base 2 log of x
     */
    public static int log2(long x) {
        if (x <= 0)
            throw new IllegalArgumentException();
        return 63 - Long.numberOfLeadingZeros(x);
    }

    /**
     * Returns the ceil of base 2 logarithm of {@code x}
     * 
     * @param x postive value
     * @return ceil of base 2 log of x
     */
    public static int log2Ceil(long x) {
        if (x <= 0)
            throw new IllegalArgumentException();
        return 64 - Long.numberOfLeadingZeros(x);
    }

    /**
     * Returns the base 2 logarithm of {@code x}
     * 
     * @param x postive value
     * @return base 2 log of x
     */
    public static double log2(double x) {
        if (x <= 0)
            throw new IllegalArgumentException();
        return log(x) / LN_2;
    }

    /**
     * Returns true if {@code x} is equal to {@code 2^k}
     * where {@code k} is a finite integer.
     * 
     * @param x non-zero finite integer value
     * @return true if x is power of two and false otherwise
     */
    public static boolean isPowerOfTwo(long x) {
        return x != 0 && ((x & (x - 1)) == 0);
    }

    /**
     * Returns true if {@code x} is an integer value stored in a double.
     * 
     * @param x a value
     * @return true if x is an integer
     */
    public static boolean isInt(double x) {
        return (x % 1 == 0);
    }

    /**
     * Returns {@code base^power % mod}
     * 
     * @param base  the base
     * @param power the exponent
     * @param mod   the modulo
     * @return base^power % mod
     */
    public static double modpow(long base, long power, double mod) {
        base %= mod;
        if (base == 0)
            return 0;

        double x = 1;
        while (power > 0) {
            if (power % 2 == 1) {
                x = (x * base) % mod;
            }
            power >>= 1;
            base = (long) ((base * base) % mod);
        }
        return x;
    }

    /**
     * Returns {@code base^power}
     * 
     * @param base  the base
     * @param power the exponent
     * @return base^power
     */
    public static double pow(long base, long power) {
        double x = 1;
        while (power > 0) {
            if (power % 2 == 1) {
                x *= base;
            }
            power >>= 1;
            x *= x;
        }
        return x;
    }

    /**
     * Checks if {@code n} is prime or not
     * 
     * @param n a value
     * @return true if {@code n} is prime and false otherwise
     */
    public static boolean isPrime(long n) {
        if (n < 2)
            return false;
        if (n == 2 || n == 3)
            return true;
        if (n % 2 == 0 || n % 3 == 0)
            return false;

        long sqrtN = (long) Math.sqrt(n) + 1;
        for (long i = 6L; i <= sqrtN; i += 6) {
            if (n % (i - 1) == 0 || n % (i + 1) == 0)
                return false;
        }
        return true;
    }

    /**
     * Calculates the number of bits used to represent {@code l}
     * <p>
     * Computes {@code ceil(log2(l))}
     * 
     * @param l a value
     * @return number of bits used to represent l
     */
    public static int bitLength(long l) {
        return log2Ceil(l);
    }

    /**
     * Returns the minimum value of the elements {@code a}
     * 
     * @param <T> the item type
     * @param a   the elements
     * @return the minimum of {@code a}
     */
    @SafeVarargs
    public static <T extends Comparable<T>> T max(T... a) {
        if (a == null || a.length == 0)
            return null;

        T max = a[0];
        for (T n : a) {
            if (n.compareTo(max) > 0)
                max = n;
        }
        return max;
    }

    /**
     * Returns the maximum value of the elements {@code a}
     * 
     * @param <T> the item type
     * @param a   the elements
     * @return the maximum of {@code a}
     */
    @SafeVarargs
    public static <T extends Comparable<T>> T min(T... a) {
        if (a == null || a.length == 0)
            return null;

        T min = a[0];
        for (T n : a) {
            if (n.compareTo(min) < 0)
                min = n;
        }
        return min;
    }
}
