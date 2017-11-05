import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

public class KdTree {

    private Node root;
    private int size;

    public KdTree() {
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        validateArgument(p);
        RectHV zone = new RectHV(0.0, 0.0, 1.0, 1.0);
        root = insert(root, p, 0, zone);
    }

    private Node insert(Node currentRoot, Point2D p, int level, RectHV currentZone) {
        boolean isEvenLevel = level % 2 == 0;
        if (currentRoot == null) {
            size++;
            return new Node(p, currentZone, isEvenLevel);
        }

        if (currentRoot.value.equals(p)) {
            currentRoot.value = p;
            return currentRoot;
        }

        int compare;
        RectHV zone;
        if (isEvenLevel) {
            compare = Double.compare(p.x(), currentRoot.value.x());
            switch (compare) {
                case -1:
                    zone = new RectHV(currentZone.xmin(), currentZone.ymin(), currentRoot.value.x(), currentZone.ymax());
                    currentRoot.left = insert(currentRoot.left, p, level + 1, zone);
                    break;
                default:
                    zone = new RectHV(currentRoot.value.x(), currentZone.ymin(), currentZone.xmax(), currentZone.ymax());
                    currentRoot.right = insert(currentRoot.right, p, level + 1, zone);
            }
        } else {
            compare = Double.compare(p.y(), currentRoot.value.y());
            switch (compare) {
                case -1:
                    zone = new RectHV(currentZone.xmin(), currentZone.ymin(), currentZone.xmax(), currentRoot.value.y());
                    currentRoot.left = insert(currentRoot.left, p, level + 1, zone);
                    break;
                default:
                    zone = new RectHV(currentZone.xmin(), currentRoot.value.y(), currentZone.xmax(), currentZone.ymax());
                    currentRoot.right = insert(currentRoot.right, p, level + 1, zone);
            }
        }
        return currentRoot;
    }

    public boolean contains(Point2D p) {
        validateArgument(p);
        Node current = root;
        while (true) {
            if (current == null) {
                return false;
            }
            if (current.value.equals(p)) {
                return true;
            }
            if (current.left != null && current.left.rect.contains(p)) {
                current = current.left;
            } else if (current.right != null && current.right.rect.contains(p)) {
                current = current.right;
            } else {
                return false;
            }
        }
    }

    public void draw() {
        draw(root);
    }

    private void draw(Node currentRoot) {
        if (currentRoot == null) {
            return;
        }
        draw(currentRoot.left);
        if (currentRoot.isEvenLevel) {
            StdDraw.setPenRadius();
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(currentRoot.value.x(), currentRoot.rect.ymin(), currentRoot.value.x(), currentRoot.rect.ymax());
        } else {
            StdDraw.setPenRadius();
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(currentRoot.rect.xmin(), currentRoot.value.y(), currentRoot.rect.xmax(), currentRoot.value.y());
        }
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        currentRoot.value.draw();
        draw(currentRoot.right);
    }

    public Iterable<Point2D> range(RectHV rect) {
        validateArgument(rect);
        List<Point2D> points = new ArrayList<>();
        range(root, rect, points);
        return points;
    }

    private void range(Node current, RectHV rect, List<Point2D> points) {
        if (current == null) {
            return;
        }
        if (current.rect.intersects(rect)) {
            range(current.left, rect, points);
            range(current.right, rect, points);
        }
        if (rect.contains(current.value)) {
            points.add(current.value);
        }
    }

    public Point2D nearest(Point2D p) {
        validateArgument(p);
        if (size == 0) {
            return null;
        }
        return nearest(root, root.value, p);
    }

    private Point2D nearest(Node current, Point2D best, Point2D p) {
        if (current == null) {
            return best;
        }

        if (!current.rect.contains(p)) {
            double axisDist;
            if (current.isEvenLevel) {
                axisDist = new Point2D(current.value.x(), p.y()).distanceSquaredTo(p);
                if (axisDist > best.distanceSquaredTo(p)) {
                    return best;
                }
            } else {
                axisDist = new Point2D(p.x(), current.value.y()).distanceSquaredTo(p);
                if (axisDist > best.distanceSquaredTo(p)) {
                    return best;
                }
            }
        }

        double currentDistSquare = current.value.distanceSquaredTo(p);
        Point2D newBest = currentDistSquare < best.distanceSquaredTo(p) ? current.value : best;

        if (current.left != null) {
            newBest = nearest(current.left, newBest, p);
        }
        if (current.right != null) {
            newBest = nearest(current.right, newBest, p);
        }
        return newBest;
    }

    private class Node {
        Point2D value;
        Node left;
        Node right;
        RectHV rect;
        boolean isEvenLevel;

        Node(Point2D p, RectHV zone, boolean lev) {
            value = p;
            rect = zone;
            isEvenLevel = lev;
        }
    }

    private void validateArgument(Object p) {
        if (p == null) {
            throw new IllegalArgumentException("Argument is null");
        }
    }
}
