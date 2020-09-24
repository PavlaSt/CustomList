package com.engeto.customlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TailorMadeListTest {

    @Test
    void addFirst() {
        TailorMadeList<String> testList = new TailorMadeList<>();

        testList.addFirst("ab");
        testList.addFirst("cd");
        testList.addFirst("ef");

        assertEquals("ef", testList.get(0));
        assertEquals("cd", testList.get(1));
        assertEquals("ab", testList.get(2));


    }
}