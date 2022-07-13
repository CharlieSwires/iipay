package com.iipay.sample;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DateSorterTest {
    DateSorterImpl dsi;

    LocalDate[] ld = {
            LocalDate.of(19,07,01), 
            LocalDate.of(19,01,02), 
            LocalDate.of(19,01,01), 
            LocalDate.of(19,05,03)};
    LocalDate[] ldResult = {
            LocalDate.of(19,01,01),
            LocalDate.of(19,01,02),
            LocalDate.of(19,07,01),
            LocalDate.of(19,05,03)};
    @BeforeEach
    void setUp() throws Exception {
        dsi = new DateSorterImpl();
    }

    @Test
    void test() {
        Set<LocalDate> unsortedSet = new HashSet<LocalDate>();
        for(int i = 0; i < ld.length; i++) {
            unsortedSet.add(ld[i]);
        }
        SortedSet<LocalDate> sortedSet = (SortedSet<LocalDate>) new TreeSet<LocalDate>();
        for(int i = 0; i < ldResult.length; i++) {
            sortedSet.add(ldResult[i]);
        }
        SortedSet<LocalDate> result = dsi.sortDates(unsortedSet);
        Assertions.assertEquals(result, sortedSet);
    }
    @Test
    void test2() {
        try {
            SortedSet<LocalDate> result = dsi.sortDates(null);
        } catch(IllegalArgumentException e) {
            Assertions.assertEquals(true, true);
        }
    }
    @Test
    void test3() {
        try {
            Set<LocalDate> unsortedSet = new HashSet<LocalDate>();
            SortedSet<LocalDate> result = dsi.sortDates(unsortedSet);
        } catch(IllegalArgumentException e) {
            Assertions.assertEquals(true, true);
        }
    }

}
