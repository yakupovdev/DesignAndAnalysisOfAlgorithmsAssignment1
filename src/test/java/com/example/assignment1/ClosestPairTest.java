package com.example.assignment1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.assignment1.metrics.Metrics;
import com.example.assignment1.features.*;

public class ClosestPairTest {
    @Test
    void testClosestPair() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(2, 3),
                new ClosestPair.Point(12, 30),
                new ClosestPair.Point(40, 50),
                new ClosestPair.Point(5, 1),
                new ClosestPair.Point(12, 10),
                new ClosestPair.Point(3, 4)
        };
        Metrics m = new Metrics();
        double dist = ClosestPair.closestPair(pts, m);
        assertEquals(Math.hypot(2-3, 3-4), dist, 1e-9); // точка (2,3) и (3,4)
        assertTrue(m.comparisons > 0);
    }
}