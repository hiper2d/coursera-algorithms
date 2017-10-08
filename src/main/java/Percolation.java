import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int size;
    private final int top;
    private final int bottom;

    private final WeightedQuickUnionUF uf;
    private final boolean[] opens;
    private int openCount = 0;

    public Percolation(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }

        this.size = n;
        top =  n * n;
        bottom = n * n + 1;
        uf = new WeightedQuickUnionUF(n * n + 2);
        opens = new boolean[n * n + 2];
        opens[top] = true;
        opens[bottom] = true;
    }

    public void open(int row, int col) {
        checkParameters(row, col);

        int i = convertRowColToIndex(row, col);
        if (!opens[i]) {
            opens[i] = true;
            openCount++;
        }

        int topNeighborIndex;
        int bottomNeighborIndex;
        int rightNeighborIndex = i;
        int leftNeighborIndex = i;

        if (row == 1) {
            topNeighborIndex = top;
        } else {
            topNeighborIndex = convertRowColToIndex(row - 1, col);
        }
        if (row == size) {
            bottomNeighborIndex = bottom;
        } else {
            bottomNeighborIndex = convertRowColToIndex(row + 1, col);
        }

        if (col == 1) {
            rightNeighborIndex = convertRowColToIndex(row, col + 1);
        } else if (col == size) {
            leftNeighborIndex = convertRowColToIndex(row, col - 1);
        } else {
            leftNeighborIndex = convertRowColToIndex(row, col - 1);
            rightNeighborIndex = convertRowColToIndex(row, col + 1);
        }

        connectNeighbor(topNeighborIndex, i);
        connectNeighbor(bottomNeighborIndex, i);
        connectNeighbor(leftNeighborIndex, i);
        connectNeighbor(rightNeighborIndex, i);
    }

    public boolean isOpen(int row, int col) {
        checkParameters(row, col);
        int i = convertRowColToIndex(row, col);
        return uf.connected(i, top);
    }

    public boolean isFull(int row, int col) {
        checkParameters(row, col);
        int i = convertRowColToIndex(row, col);
        return uf.connected(i, top);
    }

    public int numberOfOpenSites() {
        return openCount;
    }

    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    private void checkParameters(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException();
        }
    }

    private int convertRowColToIndex(int row, int col) {
        return (col - 1) + size * (row - 1);
    }

    private void connectNeighbor(int root, int newGuy) {
        if (root == newGuy) {
            return;
        }
        if (opens[root]) {
            uf.union(root, newGuy);
        }
    }
}
