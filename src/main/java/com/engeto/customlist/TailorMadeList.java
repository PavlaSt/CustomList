package com.engeto.customlist;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class TailorMadeList<E> implements CustomList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    public TailorMadeList() {
        size = 0;
    }

    @Override
    public void addFirst(E element) {
        Node<E> temp = new Node<>(element, first, null);
        if (first != null) {
            first.previous = temp;
        }
        first = temp;

        if (last == null) {
            last = temp;
        }
        size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> temp = new Node<>(element, null, last);
        if (last != null) {
            last.next = temp;
        }
        last = temp;
        if (first == null) {
            first = temp;
        }
        size++;
    }

    @Override
    public E get(int i) {
        Node<E> currant = getNode(i);
        return currant.value;
    }
    public Node<E> getNode(int i) {
        Node<E> curr;
        if ((i >= size) || (i < - size)) {
            throw new IndexOutOfBoundsException("empty");
        }
        if (i < 0) {
            curr = last;
            for (int j = size - 1; j >= size + i; i--) {
                curr = curr.previous;
            }
        } else {
            curr = first;
            for (int j = 0; j < i; j++) {
                curr = curr.next;
            }
        }
        return curr;
    }


    /**
     * Retrieves the fist element of the list.
     *
     * @return The first element.
     * @throws NoSuchElementException In case the list is empty.
     */
    @Override
    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        return first.value;
    }


    @Override
    public E getLast() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        return last.value;
    }


    @Override
    public E remove(int i) {

        Node<E> curr = getNode(i);
        if (curr == null)
            throw new NoSuchElementException();
        if (curr.previous == null) {
            removeFirst();
            return curr.value;
        }
        if (curr.next == null) {
            removeLast();
            return curr.value;
        }
        curr.previous.next = curr.next;
        curr.next.previous = curr.previous;
        size--;

        return curr.value;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Creates a copy of thi list, with the elements in reverse order.
     *
     * @return Reversed list with the elements in reverse order.
     */
    @Override
    public CustomList<E> reverse() {
        TailorMadeList<E> reversed = new TailorMadeList<>();
        TailorMadeListIterator<E> iterator = new TailorMadeListIterator<>(first);
        for (E element : this) {
            reversed.addFirst(element);
        }
        return reversed;
    }


    @Override
    public Iterator<E> iterator() {
        return new TailorMadeListIterator<>(first);
    }

    public void removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        first = first.next;
        first.previous = null;
        size--;
    }

    public void removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        last = last.previous;
        last.next = null;
        size--;
    }



    static class Node<E> {

        public final E value;
        public Node<E> next;
        public Node<E> previous;

        Node(E element, Node<E> next, Node<E> prev) {
            this.value = element;
            this.next = next;
            this.previous = prev;
        }


        @Override
        public String toString() {
            return String.format("Node: %s", value);
        }

        @Override
        public boolean equals(Object obj) {
            if (getClass() != obj.getClass()) {
                return false;
            }
            Node<E> node = (Node<E>) obj;
            return node.previous == previous && node.next == next && node.value == value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, next, previous, getClass());
        }

        Node<E> getNext() {
            return next;
        }

        Node<E> getPrev() {
            return previous;
        }
    }


}
