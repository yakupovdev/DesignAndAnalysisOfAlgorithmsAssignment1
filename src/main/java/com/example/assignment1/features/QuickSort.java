package com.example.assignment1.features;

import com.example.assignment1.metrics.Metrics;

import java.util.Random;

public class QuickSort {
    private static final Random rand = new Random();

    public static void sort(int[] arr, Metrics m) {
        quickSort(arr, 0, arr.length - 1, m);
    }

    private static void quickSort(int[] arr, int left, int right, Metrics m) {
        m.enter();
        while (left < right) {
            int pivotIndex = partition(arr, left, right, m);
            if (pivotIndex - left < right - pivotIndex) {
                quickSort(arr, left, pivotIndex - 1, m);
                left = pivotIndex + 1;
            } else {
                quickSort(arr, pivotIndex + 1, right, m);
                right = pivotIndex - 1;
            }
        }
        m.exit();
    }

    private static int partition(int[] arr, int left, int right, Metrics m) {
        int pivotIndex = left + rand.nextInt(right - left + 1);
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int store = left;
        for (int i = left; i < right; i++) {
            m.comparisons++;
            if (arr[i] < pivot) swap(arr, i, store++);
        }
        swap(arr, store, right);
        return store;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

