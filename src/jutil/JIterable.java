package jutil;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class JIterable {
    private JIterable() {
    }

    /**
     * Returns an empty iterable of no elements
     * 
     * @param <E> type of items
     * @return empty Iterable
     */
    public static <E> Iterable<E> emptyIterable() {
        return () -> JIterator.emptyIterator();
    }

    /**
     * Returns the number of items contained in the Iterable. Null safe.
     * 
     * @param iterable the iterable. can be null
     * @return number of items in the Iterable
     */
    public static int size(Iterable<?> iterable) {
        if (iterable == null)
            return 0;

        if (iterable instanceof Collection<?>)
            return ((Collection<?>) iterable).size();

        return JIterator.size(emptyIteratorIfNull(iterable));
    }

    /**
     * Returns a list of the items in the Iterable. Null safe.
     * 
     * @param <T>      the item type
     * @param iterable the iterable. can be null.
     * @return a list of items in the Iterable
     */
    public static <T> List<T> toList(Iterable<? extends T> iterable) {
        return JIterator.toList(emptyIteratorIfNull(iterable));
    }

    /**
     * Returns the item at {@code index} in the Iterable.
     * 
     * @param <T>      the item type
     * @param iterable the iterable
     * @param index    the index to get
     * @return item at {@code index} in the iterable
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public static <T> T get(Iterable<T> iterable, int index) {
        if (iterable instanceof List<?>) {
            return ((List<T>) iterable).get(index);
        }
        return JIterator.get(emptyIteratorIfNull(iterable), index);
    }

    // TODO: getAll multiple indices

    /**
     * Returns the first item in the iterable.
     * 
     * @param <T>      the item type
     * @param iterable the iterable
     * @return the first item
     * @throws IndexOutOfBoundsException if there is no first item
     */
    public static <T> T first(Iterable<T> iterable) {
        return get(iterable, 0);
    }

    /**
     * Checks if the iterable is empty
     * <p>
     * A {@code null} Iterable returns true
     * 
     * @param iterable the iterable to check
     * @return true if {@code iterable} is null or empty and false otherwise
     */
    public static boolean isEmpty(Iterable<?> iterable) {
        if (iterable instanceof Collection<?>) {
            return ((Collection<?>) iterable).isEmpty();
        }
        return JIterator.isEmpty(emptyIteratorIfNull(iterable));
    }

    /**
     * Returns a empty Iterable if {@code iterable} is null
     * and {@code iterable} otherwise. Null safe.
     * 
     * @param <E>      the item type
     * @param iterable the iterable. cam be null.
     * @return an empty iterable if {@code iterable} is null
     */
    public static <E> Iterable<E> emptyIfNull(Iterable<E> iterable) {
        return iterable == null ? JIterable.<E>emptyIterable() : iterable;
    }

    /**
     * Returns an empty Iterator if {@code iterable} is null
     * and {@code iterable.iterator()} otherwise. Null safe.
     * 
     * @param <E>      the item type
     * @param iterable the iterable. can be null
     * @return an empty Iterator if {@code iterable} is null
     */
    public static <E> Iterator<E> emptyIteratorIfNull(Iterable<E> iterable) {
        return iterable == null ? JIterator.<E>emptyIterator() : iterable.iterator();
    }

    /**
     * Returns a string representation of the iterable.
     * <p>
     * The string represenation consists of the items of the iterable
     * enclosed between square brackets {@code []} and seperated by a comma and
     * space
     * {@code ", "}.
     * 
     * @param <T>      the item type
     * @param iterable the iterable
     * @return string representation of {@code iterable}
     */
    public static <T> String toString(Iterable<T> iterable) {
        return JIterator.toString(emptyIteratorIfNull(iterable));
    }
}
