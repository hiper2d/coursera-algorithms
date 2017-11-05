import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class PointSET {

    private final NavigableSet<Point2D> points;

    public PointSET() {
        points = new TreeSet<>();
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
        validateArgument(p);
        points.add(p);
    }

    public boolean contains(Point2D p) {
        validateArgument(p);
        return points.contains(p);
    }

    public void draw() {
        for (Point2D point: points) {
            point.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        validateArgument(rect);
        List<Point2D> result = new ArrayList<>();
        for (Point2D point: points) {
            if (rect.contains(point)) {
                result.add(point);
            }
        }
        return result;
    }

    public Point2D nearest(Point2D p) {
        validateArgument(p);
        if (size() == 0) {
            return null;
        }
        Point2D best = points.first();

        for (Point2D point: points) {
            if (best.distanceSquaredTo(p) > point.distanceSquaredTo(p)) {
                best = point;
            }
        }
        return best;
    }

    private void validateArgument(Object p) {
        if (p == null) {
            throw new IllegalArgumentException("Argument is null");
        }
    }
}
