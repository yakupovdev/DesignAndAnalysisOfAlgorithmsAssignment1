package com.example.assignment1.metrics;

public class Metrics {
    public long comparisons = 0;
    public long allocations = 0;
    public int maxDepth = 0;
    public int currentDepth = 0;

    public void enter() {
        currentDepth++;
        maxDepth = Math.max(maxDepth, currentDepth);
    }

    public void exit() {
        currentDepth--;
    }

    public void reset() {
        comparisons = 0;
        allocations = 0;
        maxDepth = 0;
        currentDepth = 0;
    }

    @Override
    public String toString() {
        return comparisons + "," + allocations + "," + maxDepth;
    }
}
