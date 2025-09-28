package algorithms;

import util.Metrics;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

public class ClosestPair {
    public static class Point {
        public final double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
        public String toString() { return "(" + x + "," + y + ")"; }
    }

    public static double closestPair(Point[] points, Metrics m) {
        if (points == null || points.length < 2)
            throw new IllegalArgumentException("Need at least 2 points");


        Point[] ptsSortedX = Arrays.copyOf(points, points.length);
        Arrays.sort(ptsSortedX, Comparator.comparingDouble(p -> p.x));

        // Also prepare array sorted by y
        Point[] ptsSortedY = Arrays.copyOf(ptsSortedX, ptsSortedX.length);
        Arrays.sort(ptsSortedY, Comparator.comparingDouble(p -> p.y));

        return closestPairRecursive(ptsSortedX, ptsSortedY, m, 1);
    }

    private static double closestPairRecursive(Point[] ptsX, Point[] ptsY, Metrics m, int depth) {
        m.maxDepth = Math.max(m.maxDepth, depth);

        int n = ptsX.length;

        if (n <= 3) {
            return bruteForce(ptsX, m);
        }

        int mid = n / 2;
        Point midPoint = ptsX[mid];

        Point[] leftX = Arrays.copyOfRange(ptsX, 0, mid);
        Point[] rightX = Arrays.copyOfRange(ptsX, mid, n);
        m.allocations += 2;


        List<Point> leftList = new ArrayList<>();
        List<Point> rightList = new ArrayList<>();
        for (Point p : ptsY) {
            m.comparisons++;
            if (p.x < midPoint.x || (p.x == midPoint.x && leftList.size() < leftX.length)) {
                leftList.add(p);
            } else {
                rightList.add(p);
            }
        }

        Point[] leftY = leftList.toArray(new Point[0]);
        Point[] rightY = rightList.toArray(new Point[0]);
        m.allocations += 2;


        double dl = closestPairRecursive(leftX, leftY, m, depth + 1);
        double dr = closestPairRecursive(rightX, rightY, m, depth + 1);
        double d = Math.min(dl, dr);

        List<Point> stripList = new ArrayList<>();
        for (Point p : ptsY) {
            m.comparisons++;
            if (Math.abs(p.x - midPoint.x) < d) {
                stripList.add(p);
            }
        }
        Point[] strip = stripList.toArray(new Point[0]);
        m.allocations++;

        // Check strip points
        return Math.min(d, stripClosest(strip, d, m));
    }


    public static double bruteForce(Point[] pts, Metrics m) {
        double minDist = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                m.comparisons++;
                minDist = Math.min(minDist, distance(pts[i], pts[j]));
            }
        }
        return minDist;
    }


    private static double stripClosest(Point[] strip, double d, Metrics m) {
        double minDist = d;
        int n = strip.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n && (strip[j].y - strip[i].y) < minDist; j++) {
                m.comparisons++;
                minDist = Math.min(minDist, distance(strip[i], strip[j]));
            }
        }
        return minDist;
    }

    private static double distance(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}