package jutil;

import java.lang.reflect.Array;
import java.util.Objects;

public class JArrays {
    private JArrays() {};

    public static <T> T[] getAll(T[] array, Class<T> arrayClass, int ... indices) {
        Objects.requireNonNull(array);
        Objects.requireNonNull(arrayClass);

        @SuppressWarnings("unchecked")
        T[] items = (T[]) (Array.newInstance(arrayClass, indices.length));

        for (int i = 0; i < indices.length; i++) {
            items[i] = array[indices[i]];
        }
        return items;
    }
}
