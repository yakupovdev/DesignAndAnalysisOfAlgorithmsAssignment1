package com.example.assignment1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.assignment1.metrics.Metrics;
import com.example.assignment1.features.*;

public class DeterministicSelectTest {
    @Test
    void testSelectMedian() {
        int[] arr = {9, 1, 7, 3, 5, 8, 2, 6, 4};
        Metrics m = new Metrics();
        int result = DeterministicSelect.select(arr, 5, m); // медиана
        assertEquals(5, result);
        assertTrue(m.comparisons > 0);
    }
}