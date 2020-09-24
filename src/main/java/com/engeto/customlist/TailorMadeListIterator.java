package com.engeto.customlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

class TailorMadeListIterator<E> implements Iterator<E> {

    private TailorMadeList.Node<E> current;

    public TailorMadeListIterator(TailorMadeList.Node<E> first) {
        current = first;
    }

    @Override
    public boolean hasNext() {

        return current != null;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public E next() {
        TailorMadeList.Node<E> temporary = current;
        current = current.getNext();
        return temporary.value;
    }
    public TailorMadeList.Node<E> getCurrent() {

        return current;
    }

}
