package com.example.assignment1.features;

import com.example.assignment1.metrics.Metrics;

public class DeterministicSelect {

    public static int select(int[] arr, int k, Metrics m) {
        return select(arr, 0, arr.length - 1, k, m);
    }

    private static int select(int[] arr, int left, int right, int k, Metrics m) {
        m.enter();
        while (true) {
            if (left == right) {
                m.exit();
                return arr[left];
            }

            int pivotIndex = medianOfMedians(arr, left, right, m);
            pivotIndex = partition(arr, left, right, pivotIndex, m);

            int rank = pivotIndex - left + 1;
            if (k == rank) {
                m.exit();
                return arr[pivotIndex];
            } else if (k < rank) {
                right = pivotIndex - 1;
            } else {
                k -= rank;
                left = pivotIndex + 1;
            }
        }
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex, Metrics m) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            m.comparisons++;
            if (arr[i] < pivotValue) swap(arr, storeIndex++, i);
        }
        swap(arr, right, storeIndex);
        return storeIndex;
    }

    private static int medianOfMedians(int[] arr, int left, int right, Metrics m) {
        int n = right - left + 1;
        if (n < 5) {
            insertionSort(arr, left, right, m);
            return left + n / 2;
        }

        int numMedians = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            insertionSort(arr, i, subRight, m);
            int median = i + (subRight - i) / 2;
            swap(arr, left + numMedians, median);
            numMedians++;
        }
        return medianOfMedians(arr, left, left + numMedians - 1, m);
    }

    private static void insertionSort(int[] arr, int left, int right, Metrics m) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                m.comparisons++;
                if (arr[j] <= key) break;
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
