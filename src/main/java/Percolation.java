import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int n;
    private final int top;
    private final int bottom;

    private final WeightedQuickUnionUF uf;
    private int openCount = 0;

    public Percolation(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        top =  n * n;
        bottom = n * n + 1;
        uf = new WeightedQuickUnionUF(n * n + 2);
    }

    public void open(int row, int col) {
        checkParameters(row, col);
        int i = convertRowColToIndex(row, col);

        int topNeighborIndex = -1;
        int bottomNeighborIndex = -1;
        int rightNeighborIndex = -1;
        int leftNeighborIndex = -1;

        if (row == 1) {
            uf.union(i, top);
            bottomNeighborIndex = convertRowColToIndex(row + 1, col);
        } else if (row == n) {
            uf.union(i, bottom);
            topNeighborIndex = convertRowColToIndex(row - 1, col);
        } else {
            topNeighborIndex = convertRowColToIndex(row - 1, col);
            bottomNeighborIndex = convertRowColToIndex(row + 1, col);
        }

        if (col == 1) {
            rightNeighborIndex = convertRowColToIndex(row, col + 1);
        } else if (col == n) {
            leftNeighborIndex = convertRowColToIndex(row, col - 1);
        } else {
            leftNeighborIndex = convertRowColToIndex(row, col - 1);
            rightNeighborIndex = convertRowColToIndex(row, col + 1);
        }

        connectNeighbor(i, topNeighborIndex);
        connectNeighbor(i, bottomNeighborIndex);
        connectNeighbor(i, leftNeighborIndex);
        connectNeighbor(i, rightNeighborIndex);
    }

    public boolean isOpen(int row, int col) {
        checkParameters(row, col);
        int i = convertRowColToIndex(row, col);
        return isOpen(i);
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
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException();
        }
    }

    private int convertRowColToIndex(int row, int col) {
        return --col + n * --row;
    }

    private void connectNeighbor(int p, int q) {
        if (q != -1 && isOpen(q)) {
            uf.union(p, q);
            openCount++;
        }
    }

    private boolean isOpen(int p) {
        return uf.find(p) == p;
    }
}
