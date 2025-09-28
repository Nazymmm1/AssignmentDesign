package util;

public class Metrics {
    public long comparisons = 0;
    public long swaps = 0;
    public long allocations = 0;
    public int maxDepth = 0;
    private int currentDepth = 0;

    public void incComparisons() { comparisons++; }
    public void incSwaps() { swaps++; }
    public void incAllocations() { allocations++; }

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) maxDepth = currentDepth;
    }
    public void exitRecursion() { currentDepth--; }

    public void reset() {
        comparisons = swaps = allocations = 0;
        maxDepth = currentDepth = 0;
    }
}