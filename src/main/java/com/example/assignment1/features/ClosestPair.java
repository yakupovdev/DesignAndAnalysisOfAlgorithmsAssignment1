package com.example.assignment1.features;

import com.example.assignment1.metrics.Metrics;
import java.util.*;

public class ClosestPair {
    public static class Point {
        public double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }

    public static double closestPair(Point[] points, Metrics m) {
        Point[] sortedByX = points.clone();
        Arrays.sort(sortedByX, Comparator.comparingDouble(p -> p.x));
        Point[] buffer = new Point[points.length];
        m.allocations++;
        return closest(sortedByX, buffer, 0, points.length - 1, m);
    }

    private static double closest(Point[] pts, Point[] buf, int left, int right, Metrics m) {
        m.enter();
        if (right - left <= 3) {
            double res = bruteForce(pts, left, right, m);
            m.exit();
            return res;
        }

        int mid = (left + right) >>> 1;
        double midX = pts[mid].x;

        double d1 = closest(pts, buf, left, mid, m);
        double d2 = closest(pts, buf, mid + 1, right, m);
        double d = Math.min(d1, d2);

        mergeByY(pts, buf, left, mid, right);

        List<Point> strip = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (Math.abs(pts[i].x - midX) < d) strip.add(pts[i]);
        }

        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < d; j++) {
                m.comparisons++;
                d = Math.min(d, dist(strip.get(i), strip.get(j)));
            }
        }
        m.exit();
        return d;
    }

    private static void mergeByY(Point[] pts, Point[] buf, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (pts[i].y <= pts[j].y) buf[k++] = pts[i++];
            else buf[k++] = pts[j++];
        }
        while (i <= mid) buf[k++] = pts[i++];
        while (j <= right) buf[k++] = pts[j++];
        System.arraycopy(buf, left, pts, left, right - left + 1);
    }

    private static double bruteForce(Point[] pts, int left, int right, Metrics m) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                m.comparisons++;
                min = Math.min(min, dist(pts[i], pts[j]));
            }
        }
        Arrays.sort(pts, left, right + 1, Comparator.comparingDouble(p -> p.y));
        return min;
    }

    private static double dist(Point a, Point b) {
        return Math.hypot(a.x - b.x, a.y - b.y);
    }
}

