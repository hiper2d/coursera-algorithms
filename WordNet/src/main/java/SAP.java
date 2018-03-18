import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

public class SAP {

    private final Digraph digraph;

    public SAP(Digraph g) {
        if (g == null) {
            throw new IllegalArgumentException("Null argument");
        }
        this.digraph = g.reverse().reverse();
    }

    public int length(int v, int w) {
        validateVertexRange(v);
        validateVertexRange(w);

        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(digraph, w);
        return findMinLength(bfsv, bfsw);
    }

    public int ancestor(int v, int w) {
        validateVertexRange(v);
        validateVertexRange(w);

        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(digraph, w);
        return findMinAncestor(bfsv, bfsw);
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        validateVerteses(v);
        validateVerteses(w);

        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(digraph, w);
        return findMinLength(bfsv, bfsw);
    }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        validateVerteses(v);
        validateVerteses(w);

        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(digraph, w);
        return findMinAncestor(bfsv, bfsw);
    }

    private int findMinLength(BreadthFirstDirectedPaths bfsv, BreadthFirstDirectedPaths bfsw) {
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < digraph.V(); i++) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                int length = bfsv.distTo(i) + bfsw.distTo(i);
                if (minDist > length) {
                    minDist = length;
                }
            }
        }
        if (minDist == Integer.MAX_VALUE) {
            return -1;
        }
        return minDist;
    }

    private int findMinAncestor(BreadthFirstDirectedPaths bfsv, BreadthFirstDirectedPaths bfsw) {
        int minAncestor = -1;
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < digraph.V(); i++) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                int length = bfsv.distTo(i) + bfsw.distTo(i);
                if (minDist > length) {
                    minDist = length;
                    minAncestor = i;
                }
            }
        }
        if (minDist == Integer.MAX_VALUE) {
            return -1;
        }
        return minAncestor;
    }

    private void validateVerteses(Iterable<Integer> v) {
        if (v == null) {
            throw new IllegalArgumentException("Null argument");
        }
        for (int i : v) {
            if (i < 0 || i > digraph.V() - 1) {
                validateVertexRange(i);
            }
        }
    }

    private void validateVertexRange(int v) {
        if (v < 0 || v > digraph.V() - 1) {
            throw new IllegalArgumentException("Vertex is out of range");
        }
    }
}
