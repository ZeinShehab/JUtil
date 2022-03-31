package jutil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public final class JIterator {
    private JIterator() {
    }

    /**
     * Returns an empty iterator over no elements
     * 
     * @param <E> type of items
     * @return empty Iterator
     */
    public static <E> Iterator<E> emptyIterator() {
        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                throw new IllegalStateException("This iterator has no elements");
            }
        };
    }

    /**
     * Returns the number of items the iterator has. Null safe.
     * 
     * @param iterator an iterator. can be null
     * @return number of items
     */
    public static int size(Iterator<?> iterator) {
        if (iterator == null)
            return 0;

        int size = 0;
        while (iterator.hasNext()) {
            iterator.next();
            size++;
        }
        return size;
    }

    /**
     * Returns a list of the items the iterator contains.
     * <p>
     * The iterator is traversed and the values are stored in an ArrayList.
     * 
     * @param <T>           the item type
     * @param iterator      the iterator. can't be null
     * @param estimatedSize starting size of the ArrayList
     * @return list of the iterator items
     * @throws NullPointerException     if {@code iter} is null
     * @throws IllegalArgumentException if {@code estimatedSize} is less than 1
     */
    public static <T> List<T> toList(Iterator<? extends T> iterator, int estimatedSize) {
        Objects.requireNonNull(iterator);

        if (estimatedSize < 1)
            throw new IllegalArgumentException("Estimated size must be greater than 0");

        List<T> list = new ArrayList<>(estimatedSize);
        iterator.forEachRemaining(list::add);
        return list;
    }

    /**
     * Returns a list of the items the iterator contains.
     * <p>
     * The iterator is traversed and the values are stored in an ArrayList.
     * 
     * @param <T>      the item type
     * @param iterator the iterator. can't be null
     * @return list of the iterator items
     * @throws NullPointerException if {@code iter} is null
     */
    public static <T> List<T> toList(Iterator<? extends T> iterator) {
        return toList(iterator, 10);
    }

    /**
     * Returns an array of the items in the iterator.
     * <p>
     * As the iterator is traversed an ArrayList of the values is created
     * and then it is converted to an array.
     * 
     * @param iterator the iterator to convert. can't be null.
     * @return array of the items in the iterator
     * @throws NullPointerException if {@code iterator} is null
     */
    public static Object[] toArray(Iterator<?> iterator) {
        Objects.requireNonNull(iterator);
        List<?> list = toList(iterator);
        return list.toArray();
    }

    /**
     * Returns an array of the items in the iterator.
     * <p>
     * As the iterator is traversed an ArrayList of the values is created
     * and then it is converted to an array.
     * 
     * @param <T>        the item type
     * @param iterator   the iterator to convert. can't be null.
     * @param arrayClass the class of the array to create. can't be null.
     * @return array of the items in the iterator.
     * @throws NullPointerException if {@code iterator} is null
     * @throws ArrayStoreExcetpion  if {@code arrayClass} is invalid
     */
    public static <T> T[] toArray(Iterator<? extends T> iterator, Class<T> arrayClass) {
        Objects.requireNonNull(iterator);
        Objects.requireNonNull(arrayClass);

        List<T> list = toList(iterator);
        @SuppressWarnings("unchecked")
        T[] array = (T[]) (Array.newInstance(arrayClass, list.size()));
        return list.toArray(array);
    }

    /**
     * Returns the element at {@code index} in the Iterator.
     * <p>
     * The Iterator is moved to {@code index} as a side effect.
     * 
     * @param <T>      the item type
     * @param iterator the iterator
     * @param index    the index
     * @return item at the specified index
     * @throws NullPointerException      if the iterator is null
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public static <T> T get(Iterator<T> iterator, int index) {
        Objects.requireNonNull(iterator);

        int i = index;
        while (iterator.hasNext()) {
            i--;

            if (i == -1) {
                return iterator.next();
            }
            iterator.next();
        }
        throw new IndexOutOfBoundsException("Item does not exist: " + index);
    }

    /**
     * Returns the first item in the iterator.
     * <p>
     * The Iterator is moved to {@code 0} as a side effect.
     * 
     * @param <T>      the item type
     * @param iterator the iterator
     * @return the first item
     * @throws IndexOutOfBoundsException if there is no first item
     */
    public static <T> T first(Iterator<T> iterator) {
        return get(iterator, 0);
    }

    /**
     * Checks if the Iterator is empty .
     * <p>
     * A {@code null} Iterator returns true.
     * 
     * @param iterator the iterator to check
     * @return true if Iterator is empty or null and false otherwise
     */
    public static boolean isEmpty(Iterator<?> iterator) {
        return iterator == null || !iterator.hasNext();
    }

    /**
     * Returns a single use wrapper Iterable of the iterator. Null safe.
     * 
     * @param <T>      the item type
     * @param iterator the iterator to wrap. can be null.
     * @return single use wrapper Iterable of {@code iterator}
     */
    public static <T> Iterable<T> asIterable(Iterator<T> iterator) {
        if (iterator == null) {
            return JIterable.<T>emptyIterable();
        }
        return new Iterable<T>() {

            @Override
            public Iterator<T> iterator() {
                return iterator;
            }

        };
    }

    /**
     * Returns a string representation of the iterator.
     * <p>
     * The string represenation consists of the items of the iterator
     * enclosed between square brackets {@code []} and seperated by a comma and
     * space
     * {@code ", "}.
     * 
     * @param <T>      the item type
     * @param iterator the iterator
     * @return string representation of {@code iterator}
     */
    public static <T> String toString(Iterator<T> iterator) {
        if (isEmpty(iterator))
            return "[]";

        StringBuilder sb = new StringBuilder("[");

        while (iterator.hasNext()) {
            T elem = iterator.next();
            sb.append(elem);
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
