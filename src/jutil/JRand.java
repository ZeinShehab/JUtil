package jutil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class JRand {
    private static final Random rand = new Random();

    private JRand() {
    }

    /**
     * Generates a random integer between {@code a} and {@code b}
     * both inclusive
     * 
     * @param a a value
     * @param b a value
     * @return random number between a and b (inclusive)
     */
    public static int randInt(int a, int b) {
        return rand.nextInt(b - a + 1) + a;
    }

    /**
     * Generates a uniform random number between a and b both inclusive
     * 
     * @param a a value
     * @param b a value
     * @return uniform random number between a and b (inclusive)
     */
    public static double uniform(double a, double b) {
        return (b - a) * random() + a;
    }

    /**
     * Generates a uniform random number between {@code 0} (inclusive) and {@code 1}
     * (exclusive)
     * 
     * @return uniform random number between 0 and 1
     */
    public static double random() {
        return rand.nextDouble();
    }

    /**
     * Returns a random item from {@code a}
     * 
     * @param <T> the item type
     * @param a   the items
     * @return random item from a
     */
    @SafeVarargs
    public static <T> T choice(T... a) {
        return a[randInt(0, a.length - 1)];
    }

    /**
     * Returns a random item from {@code a}
     * 
     * @param <T> the item type
     * @param a   list of items
     * @return random item from a
     */
    public static <T> T choice(List<T> a) {
        return a.get(randInt(0, a.size() - 1));
    }

    /**
     * Returns a random sample of size {@code n} of items from {@code a}
     * 
     * @param <T> the item type
     * @param a   the items
     * @param n   sample size
     * @return random sample of items from a
     */
    public static <T> List<T> sample(T[] a, int n) {
        List<T> res = new ArrayList<>(n);
        boolean[] choices = new boolean[a.length];

        for (int i = 0; i < n; i++) {
            int index = randInt(0, a.length - 1);
            if (choices[index])
                i--;
            else {
                choices[index] = true;
                res.add(a[index]);
            }
        }
        return res;
    }

    /**
     * Returns a random sample of size {@code n} of items from {@code a}
     * 
     * @param <T> the item type
     * @param a   list of items
     * @param n   the sample size
     * @return random sample of items from a
     */
    public static <T> List<T> sample(List<T> a, int n) {
        List<T> res = new ArrayList<>(n);
        boolean[] choices = new boolean[a.size()];

        for (int i = 0; i < n; i++) {
            int index = randInt(0, a.size() - 1);
            if (choices[index])
                i--;
            else {
                choices[index] = true;
                res.add(a.get(index));
            }
        }
        return res;
    }
}