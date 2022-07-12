package com.iipay.sample;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class DateSorterImpl implements DateSorter{
    static Map<Integer,Boolean> containsRMap = new HashMap<>();
    // Ahh to reminisce
    static Object[][] months = { 
            {"January",     1, true},
            {"February",    2, true},
            {"March",       3, true},
            {"April",       4, true},
            {"May",         5, false},
            {"June",        6, false},
            {"July",        7, false},
            {"August",      8, false},
            {"September",   9, true},
            {"October",     10,true},
            {"November",    11,true},
            {"December",    12,true}
            
    };
    static final int MONTH = 0;
    static final int MONTH_ORDINAL = 1;
    static final int CONTAINS_R = 2;
    
    public DateSorterImpl() {
        for(int i = 0; i < months.length; i++) {
            containsRMap.put(
                    (Integer)months[i][MONTH_ORDINAL],
                    (Boolean)months[i][CONTAINS_R]);
        }
    }
    
    /**
     * 1. Split into two lists one with R one without
     * 2. contains R sort ascending
     * 3. contains no R sort descending
     * 4. glue together into a SortedSet
     * 5. return result
     */
    @Override
    public SortedSet<LocalDate> sortDates(Set<LocalDate> unsortedDates) {
        List<LocalDate> unsortedContainsR = new ArrayList<LocalDate>();
        List<LocalDate> unsortedNotContainsR = new ArrayList<LocalDate>();
        for (LocalDate date : unsortedDates) {
            if (containsRMap.get(date.getMonthValue())){
                unsortedContainsR.add(date);
            } else {
                unsortedNotContainsR.add(date);
            }
        }
        unsortedContainsR.sort(new Comparator<LocalDate>() {

            @Override
            public int compare(LocalDate o1, LocalDate o2) {
                return o1.isAfter(o2)? 1 : -1;
            }
            
        });
        unsortedNotContainsR.sort(new Comparator<LocalDate>() {

            @Override
            public int compare(LocalDate o1, LocalDate o2) {
                return o1.isBefore(o2)? 1 : -1;
            }
            
        });

        SortedSet<LocalDate> sorted = new TreeSet<>();
        for (LocalDate date : unsortedContainsR) {
            sorted.add(date);
        }
        for (LocalDate date : unsortedNotContainsR) {
            sorted.add(date);
        }
        return sorted;
    }

}
