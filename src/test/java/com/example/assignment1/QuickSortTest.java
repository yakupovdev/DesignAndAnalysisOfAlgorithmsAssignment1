package com.example.assignment1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.assignment1.metrics.Metrics;
import com.example.assignment1.features.*;

public class QuickSortTest {
    @Test
    void testQuickSort() {
        int[] arr = {5, 3, 8, 4, 2, 7, 1, 9};
        Metrics m = new Metrics();
        QuickSort.sort(arr, m);
        assertArrayEquals(new int[]{1,2,3,4,5,7,8,9}, arr);
        assertTrue(m.comparisons > 0);
    }
}
