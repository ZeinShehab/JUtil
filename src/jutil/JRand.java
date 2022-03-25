package jutil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class JRand {
    private static final Random rand = new Random();   

    private JRand() {}

    public static int randInt(int a, int b) {
        return rand.nextInt(b-a+1) + a;
    }

    public static double uniform(double a, double b) {
        return (b-a) * random() + a;
    }

    public static double random() {
        return rand.nextDouble();
    }

    @SafeVarargs
    public static<T> T choice(T ... a) {
        return a[randInt(0, a.length-1)];
    }

    public static<T> T choice(List<T> a) {
        return a.get(randInt(0, a.size()-1));
    }

    public static<T> List<T> sample(T[] a, int n) {
        List<T> res = new ArrayList<>(n); 
        boolean[] choices = new boolean[a.length];

        for (int i = 0; i < n; i++) {
            int index = randInt(0, a.length-1); 
            if (choices[index]) i--;
            else {
                choices[index] = true;
                res.add(a[index]);
            }
        }
        return res;
    }

    // TODO: sample an iterable && lists with O(n) get
    public static<T> List<T> sample(List<T> a, int n) {
        List<T> res = new ArrayList<>(n);
        boolean[] choices = new boolean[a.size()];

        for (int i = 0; i < n; i++) {
            int index = randInt(0, a.size()-1); 
            if (choices[index]) i--;
            else {
                choices[index] = true;
                res.add(a.get(index));
            }
        }
        return res;
    }
}