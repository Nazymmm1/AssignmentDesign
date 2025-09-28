# Assignment 1: Divide and Conquer Algorithms

## Learning Goals
- Implement classic divide-and-conquer algorithms with safe recursion patterns.
- Analyze running-time recurrences using Master Theorem (3 cases) and Akra–Bazzi intuition.
- Collect metrics (time, recursion depth, comparisons/allocations) and validate with measurements.
- Communicate results via a report and clean Git workflow.



## Implemented Algorithms

### 1. MergeSort
- Techniques: Linear merge, reusable buffer, cutoff to insertion sort for small subarrays.
- Recurrence:  
  T(n) = 2T(n/2) + Θ(n)  
  Master Theorem, Case 2 → Θ(n log n)
- Notes: Buffer reuse reduces allocations; insertion sort improves performance on small n.

### 2. QuickSort
- Techniques: Randomized pivot, recurse on smaller partition, iterative handling of larger one.
- Recurrence:  
  Expected: T(n) = T(n/2) + T(n/2) + Θ(n) = Θ(n log n)  
  Worst case: T(n) = T(n-1) + Θ(n) = Θ(n²)
- Notes: Random pivot prevents adversarial cases; smaller-first recursion bounds stack depth ≈ O(log n).

### 3. Deterministic Select (Median-of-Medians)
- Techniques: Group elements in 5s, take medians, recursively choose pivot, in-place partition.
- Recurrence:  
  T(n) = T(n/5) + T(7n/10) + Θ(n)  
  Using Akra–Bazzi → Θ(n)
- Notes: Always guarantees linear time; recurses only into one partition (smaller side preferred).

### 4. Closest Pair of Points (2D)
- Techniques: Divide by x-coordinate, recursive solve, merge step checks strip in y-order (7–8 neighbor scan).
- Recurrence:  
  T(n) = 2T(n/2) + Θ(n)  
  Master Theorem, Case 2 → Θ(n log n)
- Notes: Sorting by x and reusing y-order arrays controls allocations and improves cache behavior.



## Metrics and Measurements

We collected:
- Time vs n
- Recursion depth vs n
- Comparisons/allocations

Example plots (to be inserted):
- MergeSort time vs n
- QuickSort recursion depth vs n
- Select runtime linear trend
- Closest Pair vs brute-force



## Summary of Results

- MergeSort: Matches Θ(n log n), cutoff improved small cases.
- QuickSort: Average Θ(n log n), recursion depth bounded by ~2 log₂(n).
- Select: Measurements align with Θ(n), but constants are higher than randomized quickselect.
- Closest Pair: Matches Θ(n log n); brute-force only feasible for small n.

Conclusion: Experimental results aligned well with theoretical predictions, with some constant-factor effects observed due to cache locality and memory allocation overhead.



## GitHub Workflow

Branches:
- main: stable releases (tagged v0.1, v1.0)
- feature/mergesort, feature/quicksort, feature/select, feature/closest, feature/metrics

Commit storyline:
- init: initial project setup with algorithms and metrics
- feat(metrics): counters, depth tracker, CSV writer
- feat(mergesort): baseline + buffer reuse + cutoff
- feat(quicksort): randomized pivot, smaller-first recursion
- feat(select): deterministic select (MoM5)
- feat(closest): divide-and-conquer closest pair
- docs(report): recurrence analysis, plots
- release: v1.0