import algorithms.ClosestPair;
import algorithms.DeterministicSelect;
import algorithms.MergeSort;
import algorithms.QuickSort;
import util.Metrics;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //merge sort
        int[] arr = {5, 2, 9, 1, 5, 6};
        Metrics m = new Metrics();

        System.out.println("Merge sort");
        System.out.println("Before: " + Arrays.toString(arr));
        MergeSort.sort(arr, m);
        System.out.println("Sorted: " + Arrays.toString(arr));
        System.out.println("Comparisons: " + m.comparisons);
        System.out.println("Max depth: " + m.maxDepth);

        //quick sort
        int[] arr1 = {9, 3, 7, 1, 8, 2, 5};
        Metrics m1 = new Metrics();
        System.out.println("Quick sort");
        System.out.println("Before: " + Arrays.toString(arr1));
        QuickSort.sort(arr1, m1);
        System.out.println("After:  " + Arrays.toString(arr1));
        System.out.println("Comparisons: " + m1.comparisons);
        System.out.println("Swaps: " + m1.swaps);
        System.out.println("Max recursion depth: " + m1.maxDepth);

        //deterministic select
        int[] arr2 = {9, 3, 7, 10, 8, 2, 5};
        int k = 3;

        Metrics m2 = new Metrics();
        int result = DeterministicSelect.select(arr2, k, m2);
        System.out.println("Deterministic select demonstration");
        System.out.println(k+1 + "-th smallest = " + result);
        System.out.println("Comparisons: " + m2.comparisons);
        System.out.println("Swaps: " + m2.swaps);
        System.out.println("Max depth: " + m2.maxDepth);

        int[] copy = Arrays.copyOf(arr2, arr2.length);
        Arrays.sort(copy);
        System.out.println("Check (sorted): " + copy[k]);

        ClosestPair.Point[] points = {
                new ClosestPair.Point(2, 3), new ClosestPair.Point(12, 30), new ClosestPair.Point(40, 50),
                new ClosestPair.Point(5, 1), new ClosestPair.Point(12, 10), new ClosestPair.Point(3, 4)
        };

        //Closest pair
        Metrics m3 = new Metrics();
        double result3 = ClosestPair.closestPair(points, m3);
        System.out.println("Closest pair distance = " + result3);
        System.out.println("Comparisons: " + m3.comparisons);
        System.out.println("Allocations: " + m3.allocations);
        System.out.println("Max recursion depth: " + m3.maxDepth);


        double brute = ClosestPair.bruteForce(points, m3);
        System.out.println("Brute force check = " + brute);
    }
}