package algorithms;

import util.Metrics;
import java.util.Random;

public class QuickSort {
    private static final int CUTOFF = 16;
    private static final Random rand = new Random();

    public static void sort(int[] arr, Metrics m){
        if(arr.length <= 1 || arr==null){
            return;
        }
        quickSort(arr,0, arr.length-1, m, 1 );
    }

    private static void quickSort(int[] arr, int low, int high, Metrics m, int depth) {
        m.maxDepth = Math.max(m.maxDepth, depth);

        while (low < high) {
            if (high - low + 1 <= CUTOFF) {
                insertionSort(arr, low, high, m);
                return;
            }

            int pivotIndex = partition(arr, low, high, m);

            int leftSize = pivotIndex - low;
            int rightSize = high - pivotIndex;


            if (leftSize < rightSize) {
                quickSort(arr, low, pivotIndex - 1, m, depth + 1);
                low = pivotIndex + 1; // iterate on the larger side
            } else {
                quickSort(arr, pivotIndex + 1, high, m, depth + 1);
                high = pivotIndex - 1;
            }
        }
    }


    private static int partition(int[] arr, int low, int high, Metrics m) {
        int pivotIndex = low + rand.nextInt(high - low + 1);
        swap(arr, pivotIndex, high, m);

        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            m.comparisons++;
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j, m);
            }
        }
        swap(arr, i + 1, high, m);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j, Metrics m){
        if(i != j){
            int temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
            m.swaps++;
        }
    }
    private static void insertionSort(int[] arr, int low, int high, Metrics m) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= low) {
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


}