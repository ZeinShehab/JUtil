package jutil;

import java.lang.reflect.Array;
import java.util.Objects;

public final class JArrays {
    private JArrays() {};

    /**
     * Returns an array of items of at the given {@code indices}
     * 
     * @param <T> the item type
     * @param array the array to get from
     * @param arrayClass class of the array
     * @param indices the indices to get
     * @return items in {@code array} at given {@code indices}
     */
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
