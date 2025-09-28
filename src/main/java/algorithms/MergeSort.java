package algorithms;

import util.Metrics;

public class MergeSort {
    private static final int CUTOFF = 16;

    public static void sort(int[] arr, Metrics m) {
        int[] buffer = new int[arr.length];
        sort(arr, buffer, 0, arr.length, m, 1);
    }

    private static void sort(int[] arr, int[] buf, int lo, int hi, Metrics m, int depth) {
        m.maxDepth = Math.max(m.maxDepth, depth);

        int n = hi - lo;
        if (n <= CUTOFF) {
            insertionSort(arr, lo, hi, m);
            return;
        }

        int mid = lo + n / 2;
        sort(arr, buf, lo, mid, m, depth + 1);
        sort(arr, buf, mid, hi, m, depth + 1);

        merge(arr, buf, lo, mid, hi, m);
    }

    private static void merge(int[] arr, int[] buf, int lo, int mid, int hi, Metrics m) {
        int i = lo, j = mid, k = lo;

        while (i < mid && j < hi) {
            m.comparisons++;
            if (arr[i] <= arr[j]) buf[k++] = arr[i++];
            else buf[k++] = arr[j++];
        }
        while (i < mid) buf[k++] = arr[i++];
        while (j < hi) buf[k++] = arr[j++];

        for (int t = lo; t < hi; t++) arr[t] = buf[t];
    }

    private static void insertionSort(int[] arr, int lo, int hi, Metrics m) {
        for (int i = lo + 1; i < hi; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= lo && arr[j] > key) {
                m.comparisons++;
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}