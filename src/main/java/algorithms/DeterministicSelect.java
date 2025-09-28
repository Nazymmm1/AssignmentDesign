package algorithms;

import util.Metrics;

public class DeterministicSelect {
    private static final int GROUP_SIZE = 5;

    public static int select(int[] arr, int k, Metrics m) {
        if (arr == null || arr.length == 0 || k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("Invalid input");
        }
        return select(arr, 0, arr.length - 1, k, m, 1);
    }

    private static int select(int[] arr, int left, int right, int k, Metrics m, int depth) {
        m.maxDepth = Math.max(m.maxDepth, depth);

        while (true) {
            if (left == right) return arr[left];

            int pivotIndex = medianOfMedians(arr, left, right, m, depth + 1);
            pivotIndex = partition(arr, left, right, pivotIndex, m);

            if (k == pivotIndex) {
                return arr[k];
            } else if (k < pivotIndex) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }
    }


    private static int partition(int[] arr, int left, int right, int pivotIndex, Metrics m) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right, m);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            m.comparisons++;
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i, m);
                storeIndex++;
            }
        }
        swap(arr, right, storeIndex, m);
        return storeIndex;
    }


    private static int medianOfMedians(int[] arr, int left, int right, Metrics m, int depth) {
        m.maxDepth = Math.max(m.maxDepth, depth);

        int n = right - left + 1;
        if (n <= GROUP_SIZE) {
            insertionSort(arr, left, right, m);
            return left + n / 2;
        }

        int numMedians = 0;
        for (int i = left; i <= right; i += GROUP_SIZE) {
            int subRight = Math.min(i + GROUP_SIZE - 1, right);
            insertionSort(arr, i, subRight, m);
            int medianIndex = i + (subRight - i) / 2;
            swap(arr, left + numMedians, medianIndex, m);
            numMedians++;
        }


        return medianOfMedians(arr, left, left + numMedians - 1, m, depth + 1);
    }

    private static void insertionSort(int[] arr, int left, int right, Metrics m) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                m.comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    m.swaps++;
                    j--;
                } else break;
            }
            arr[j + 1] = key;
        }
    }

    private static void swap(int[] arr, int i, int j, Metrics m) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            m.swaps++;
        }
    }

}