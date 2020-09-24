package com.engeto.customlist;

/**
 * A custom simplified list.
 */

public interface CustomList<E> extends Iterable<E> {
    /**
     * Adds an element to the beginning of the list.
     * @param element The element to be added.
     */
    void addFirst(E element);

    /**
     * Adds an element to the end of the list.
     * @param element The element to be added.
     */
    void addLast(E element);

    /**
     * Gets the element at the specified position in the list.
     * @param i The position from which to retrieve the element. 0-based index. If a negative number is given, the position is relative from the end of the list.
     * @return The element at the given position.
     * @throws IndexOutOfBoundsException in case a position past the end/beginning of the list is given.
     */
    E get(int i);

    /**
     * Retrieves the fist element of the list.
     * @return The first element.
     * @throws java.util.NoSuchElementException In case the list is empty.
     */
    E getFirst();

    /**
     * Retrieves the last element of the list.
     * @return The last element.
     * @throws java.util.NoSuchElementException In case the list is empty.
     */
    E getLast();

    /**
     * Removes the element at the specified position.
     * @param i The position from which to delete the element. 0-based index. If a negative number is given, the position is relative from the end of the list.
     * @throws IndexOutOfBoundsException in case a position past the end/beginning of the list is given.
     * @return The removed element.
     */
    E remove(int i);

    /**
     * Computes the size of the list.
     * @return The number of elements in the list.
     */
    int size();

    /**
     * Creates a copy of thi list, with the elements in reverse order.
     * @return Reversed list with the elements in reverse order.
     */
    CustomList<E> reverse();
}
