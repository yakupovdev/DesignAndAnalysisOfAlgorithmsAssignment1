package com.example.assignment1;

import com.example.assignment1.features.*;
import com.example.assignment1.metrics.Metrics;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;


public class Main {
    public static void main(String[] args) throws Exception {
        PrintWriter csv = new PrintWriter(new FileWriter("metrics.csv"));
        csv.println("Algorithm,Time(ms),Comparisons,Allocations,MaxDepth");

        //MergeSort
        int[] arr1 = randomArray(10000);
        Metrics m1 = new Metrics();
        long t1 = System.currentTimeMillis();
        MergeSort.sort(arr1, m1);
        long t2 = System.currentTimeMillis();
        csv.println("MergeSort," + (t2 - t1) + "," + m1);

        //QuickSort
        int[] arr2 = randomArray(10000);
        Metrics m2 = new Metrics();
        long t3 = System.currentTimeMillis();
        QuickSort.sort(arr2, m2);
        long t4 = System.currentTimeMillis();
        csv.println("QuickSort," + (t4 - t3) + "," + m2);

        //Deterministic Select
        int[] arr3 = randomArray(10000);
        Metrics m3 = new Metrics();
        long t5 = System.currentTimeMillis();
        int kth = DeterministicSelect.select(arr3, arr3.length / 2, m3);
        long t6 = System.currentTimeMillis();
        csv.println("DeterministicSelect," + (t6 - t5) + "," + m3);

        //Closest Pair
        ClosestPair.Point[] pts = randomPoints(10000);
        Metrics m4 = new Metrics();
        long t7 = System.currentTimeMillis();
        double dist = ClosestPair.closestPair(pts, m4);
        long t8 = System.currentTimeMillis();
        csv.println("ClosestPair," + (t8 - t7) + "," + m4);

        csv.close();
        System.out.println("Метрики записаны в metrics.csv");
    }

    private static int[] randomArray(int n) {
        Random r = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = r.nextInt(n * 10);
        return arr;
    }

    private static ClosestPair.Point[] randomPoints(int n) {
        Random r = new Random();
        ClosestPair.Point[] pts = new ClosestPair.Point[n];
        for (int i = 0; i < n; i++) {
            pts[i] = new ClosestPair.Point(r.nextDouble() * 1000, r.nextDouble() * 1000);
        }
        return pts;
    }
}
