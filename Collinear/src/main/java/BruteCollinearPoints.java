import edu.princeton.cs.algs4.ResizingArrayQueue;

import java.util.Arrays;

public class BruteCollinearPoints {

    private int numberOfSegments;
    private final ResizingArrayQueue<LineSegment> segments;

    public BruteCollinearPoints(final Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Argument is null");
        }

        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException("Argument contain null point");
            }
        }

        Point[] pointsCopy = copyPoints(points);
        Arrays.sort(pointsCopy);
        for (int i = 0; i < pointsCopy.length - 1; i++) {
            if (pointsCopy[i].compareTo(pointsCopy[i + 1]) == 0) {
                throw new IllegalArgumentException("Argument contains points duplicates");
            }
        }

        segments = new ResizingArrayQueue<>();
        findSegments(pointsCopy);
    }

    public int numberOfSegments() {
        return numberOfSegments;
    }

    public LineSegment[] segments() {
        LineSegment[] segmentsArray = new LineSegment[numberOfSegments];
        int i = 0;
        for (LineSegment seg : segments) {
            segmentsArray[i++] = seg;
        }
        return segmentsArray;
    }

    private void findSegments(final Point[] points) {
        if (points.length < 4) {
            return;
        }

        for (int p1 = 0; p1 < points.length - 3; p1++) {
            for (int p2 = p1 + 1; p2 < points.length - 2; p2++) {
                for (int p3 = p2 + 1; p3 < points.length - 1; p3++) {
                    if (points[p1].slopeOrder().compare(points[p2], points[p3]) == 0) {
                        for (int p4 = p3 + 1; p4 < points.length; p4++) {
                            if (points[p2].slopeOrder().compare(points[p3], points[p4]) == 0) {
                                segments.enqueue(new LineSegment(points[p1], points[p4]));
                                numberOfSegments++;
                            }
                        }
                    }
                }
            }
        }
    }

    private Point[] copyPoints(Point[] points) {
        Point[] pointsCopy = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            pointsCopy[i] = points[i];
        }
        return pointsCopy;
    }
}
