import edu.princeton.cs.algs4.ResizingArrayQueue;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {

    private int numberOfSegments;
    private final ResizingArrayQueue<Point[]> segments;

    public FastCollinearPoints(final Point[] points) {
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
        findSegments(points);
    }

    public int numberOfSegments() {
        return numberOfSegments;
    }

    public LineSegment[] segments() {
        LineSegment[] segmentsArray = new LineSegment[numberOfSegments];
        int i = 0;
        for (Point[] seg : segments) {
            segmentsArray[i++] = new LineSegment(seg[0], seg[1]);
        }
        return segmentsArray;
    }

    private void findSegments(final Point[] points) {
        if (points.length < 4) {
            return;
        }

        Point[] pointsCopy = copyPoints(points);
        Comparator<Point> slopeComparator;

        for (Point point : points) {
            slopeComparator = point.slopeOrder();
            Arrays.sort(pointsCopy, slopeComparator);

            int start = 1;
            int end = 1;
            double previousSlope = point.slopeTo(pointsCopy[1]);

            for (int i = 2; i < pointsCopy.length; i++) {
                double slope = point.slopeTo(pointsCopy[i]);
                if (Double.compare(previousSlope, slope) == 0) {
                    end++;
                } else {
                    if (end - start >= 2) {
                        addToQueue(point, pointsCopy[start], pointsCopy[end]);
                    }
                    start = i;
                    end = start;
                    previousSlope = slope;
                }
                if (i == pointsCopy.length - 1 && end - start >= 2) {
                    addToQueue(point, pointsCopy[start], pointsCopy[end]);
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

    private void addToQueue(Point origin, Point start, Point end) {
        if (start.compareTo(end) > 0) {
            Point temp = start;
            start = end;
            end = temp;
        }

        if (origin.compareTo(start) < 0) {
            start = origin;
        } else if (origin.compareTo(end) > 0) {
            end = origin;
        }

        for (Point[] segmentPoint : segments) {
            if (start.slopeOrder().compare(end, segmentPoint[1]) == 0 && end.slopeOrder().compare(start, segmentPoint[0]) == 0) {
                if (segmentPoint[0].compareTo(start) > 0) {
                    segmentPoint[0] = start;
                }
                if (segmentPoint[1].compareTo(end) < 0) {
                    segmentPoint[1] = end;
                }
                return;
            }
        }

        segments.enqueue(new Point[] {start, end});
        numberOfSegments++;
    }
}
