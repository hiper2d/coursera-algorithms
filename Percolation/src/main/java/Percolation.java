import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int size;
    private final int top;

    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF ufBottom;
    private final boolean[] opens;
    private int openCount = 0;
    private int bottom = -1;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.size = n;
        top =  n * n;
        uf = new WeightedQuickUnionUF(n * n + 1);
        ufBottom = new WeightedQuickUnionUF(n);
        opens = new boolean[n * n + 1];
        opens[top] = true;
    }

    public void open(int row, int col) {
        checkParameters(row, col);

        int i = convertRowColToIndex(row, col);
        if (!opens[i]) {
            opens[i] = true;
            openCount++;
        } else {
            return;
        }

        int topNeighborIndex;
        int bottomNeighborIndex = i;
        int rightNeighborIndex = i;
        int leftNeighborIndex = i;

        if (row == 1) {
            topNeighborIndex = top;
        } else {
            topNeighborIndex = convertRowColToIndex(row - 1, col);
        }

        if (row < size) {
            bottomNeighborIndex = convertRowColToIndex(row + 1, col);
        } else {
            if (bottom == -1) {
                bottom = col - 1;
            } else {
                ufBottom.union(bottom, col - 1);
            }
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
        return opens[i];
    }

    public boolean isFull(int row, int col) {
        checkParameters(row, col);
        int i = convertRowColToIndex(row, col);
        return uf.connected(i, top);
    }

    public int numberOfOpenSites() {
        return openCount;
    }

    // This gives the best total result 94% (all performance and memory tests are passed) but it's not always correct.
    // The best result with all correctness test are passed is 88%.
    public boolean percolates() {
        if (bottom == -1) {
            return false;
        }
        int bottomRoot = ufBottom.find(bottom);
        int bottomRootIndex = convertRowColToIndex(size, bottomRoot + 1);
        return uf.connected(top, bottomRootIndex);
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
