package com.engeto.customlist;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CustomListTest {
    private CustomList<Object> list;
    private Object testElement;
    private Object[] testElements;

    @BeforeEach
    void createList() {
        list = new TailorMadeList<>();
        testElement = new Object();
        testElements = new Object[]{1, 2, 3, 4, 5};
    }

    @Test
    void emptyListSize() {
        assertThat(list.size())
                .as("The size of an empty list must be 0.")
                .isEqualTo(0);
    }

    @Test
    void emptyListGet() {
        assertThatThrownBy(() -> list.get(0))
                .as("Getting an element from an empty list should throw an exception.")
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void emptyListLast() {
        assertThatThrownBy(() -> list.getLast())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void emptyListFirst() {
        assertThatThrownBy(() -> list.getFirst())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void emptyListRemove() {
        assertThatThrownBy(() -> list.remove(0))
                .as("Removing an element from an empty list should throw an exception.")
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessageContaining("empty");
    }

    @Test
    void emptyListAddFirst() {
        list.addFirst(testElement);
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void emptyListAddLast() {
        list.addLast(testElement);
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void emptyListForLoop() {
        assertThatCode(() -> {
            for (Object element : list) /* empty on purpose */;
        }).doesNotThrowAnyException();
    }

    @Test
    void emptyListIterator() {
        Iterator<Object> listIterator = list.iterator();
        assertThat(listIterator.hasNext())
                .as("Iterator of an empty list should not iterate over any elements.")
                .isFalse();
    }

    @Test
    void emptyListReverse() {
        assertThatCode(list::reverse)
                .as("It must be possible to reverse an empty list.")
                .doesNotThrowAnyException();

        CustomList<Object> reversedList = list.reverse();
        assertThat(reversedList)
                .isNotSameAs(list)
                .isNotNull();
    }

    @Test
    void onlyElementGet() {
        list.addLast(testElement);
        assertThat(list.get(0))
                .as("It must be possible to retrieve an element after it is added.")
                .isSameAs(testElement);
    }

    @Test
    void onlyElementAddedLastGetFirstGetLastEquality() {
        list.addLast(testElement);
        assertThat(list.getFirst())
                .as("After adding the first element to the list, it should act as the first and last element.")
                .isSameAs(list.getLast())
                .isSameAs(list.get(0))
                .isSameAs(testElement);
    }

    @Test
    void onlyElementAddedFirstGetFirstGetLastEquality() {
        list.addFirst(testElement);
        assertThat(list.getLast())
                .as("After adding the first element to the list, it should act as the first and last element.")
                .isSameAs(list.getFirst())
                .isSameAs(list.get(0))
                .isSameAs(testElement);
    }

    @Test
    void listPreservesOrder() {
        for(Object element : testElements) {
            list.addLast(element);
        }

        for(int i = 0; i < testElements.length; i++) {
            assertThat(list.get(i))
                    .as("The list must preserve the order of the elements.")
                    .isSameAs(testElements[i]);
        }
    }

    @Test
    void reversingList() {
        for(Object element : testElements) {
            list.addLast(element);
        }

        CustomList<Object> reversedList = list.reverse();
        for(int i = 0; i < testElements.length; i++) {
            assertThat(reversedList.get(i))
                    .as("Reversed list must contain the elements in reverse order.")
                    .isSameAs(testElements[testElements.length - i - 1]);
        }
        assertThat(reversedList.getFirst())
                .isSameAs(list.getLast());
        assertThat(reversedList.getLast())
                .isSameAs(list.getFirst());
    }

    @Test
    void reversingCreatesNewList() {
        for(Object element : testElements) {
            list.addLast(element);
        }

        CustomList<Object> reversedList = list.reverse();
        assertThat(reversedList)
                .isNotSameAs(list);
    }

    @Test
    void addFirst() {
        for(Object element : testElements) {
            list.addLast(element);
        }

        Object newFirstElement = 10;
        list.addFirst(newFirstElement);
        assertThat(list.getFirst())
                .isSameAs(list.get(0))
                .isNotSameAs(list.getLast())
                .isSameAs(newFirstElement);
    }

    @Test
    void addLast() {
        for(Object element : testElements) {
            list.addLast(element);
        }

        Object newLastElement = 10;
        list.addLast(newLastElement);
        assertThat(list.getLast())
                .isSameAs(list.get(list.size() - 1))
                .isNotSameAs(list.getFirst())
                .isSameAs(newLastElement);
    }

    @Test
    void remove() {
        for(Object element : testElements) {
            list.addLast(element);
        }

        Object removedElement = testElements[0];
        list.remove(0);
        assertThat(list.getFirst())
                .isNotSameAs(removedElement)
                .isSameAs(testElements[1]);
    }

    @Test
    void iterate() {
        for(Object element : testElements) {
            list.addLast(element);
        }

        int i = 0;
        for(Object element : list) {
            assertThat(element)
                    .as("For-each loop must iterate over the list elements in order.")
                    .isSameAs(testElements[i++]);
        }
    }

    @Test
    void duplicateElements() {
        list.addFirst(testElement);
        list.addFirst(testElement);

        assertThat(list.size())
                .as("It must be possible to add the same element to the list multiple times.")
                .isEqualTo(2);

        assertThat(list.get(0))
                .isSameAs(list.getFirst())
                .isSameAs(testElement);
        assertThat(list.get(1))
                .isSameAs(list.getLast())
                .isSameAs(testElement);
    }

    @Test
    void getOutOfBounds() {
        list.addFirst(testElement);
        assertThatThrownBy(() -> list.get(50))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void removeOutOfBounds() {
        list.addFirst(testElement);
        assertThatThrownBy(() -> list.remove(50))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void bigList() {
        final int SIZE = 100000;
        for (int i = 0; i < SIZE; i++) {
            list.addLast(new Object());
        }

        assertThat(list.size()).isEqualTo(SIZE);
        int realSize = 0;
        for(Object element : list) {
            realSize++;
        }

        assertThat(realSize).isEqualTo(SIZE);
    }
}