import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int[][] blocks;
    private final int n;
    private int hamming;
    private int manhattan;

    public Board(int[][] blocks) {
        this.blocks = blocks;
        n = blocks.length;
        calculateHammingAndManhattan();
    }

    public int dimension() {
        return n;
    }

    public int hamming() {
        return hamming;
    }

    public int manhattan() {
        return manhattan;
    }

    public boolean isGoal() {
        return hamming == 0 && manhattan == 0;
    }

    public Board twin() {
        int count = 0, iTemp = 0, jTemp = 0;
        int[][] copyBlocks = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copyBlocks[i][j] = blocks[i][j];

                if (copyBlocks[i][j] != 0) {
                    if (count == 0) {
                        iTemp = i;
                        jTemp = j;
                        count++;
                    } else if (count++ == 1) {
                        exch(copyBlocks, iTemp, jTemp, i, j);
                    }
                }
            }
        }
        return new Board(copyBlocks);
    }

    public boolean equals(Object y) {
        if (!(y instanceof Board)) {
            return false;
        }
        Board other = (Board) y;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] != other.blocks[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        int i0 = 0;
        int j0 = 0;
        for (int k = 0; k < n*n; k++) {
            i0 = k / n;
            j0 = k % n;
            if (blocks[i0][j0] == 0) {
                break;
            }
        }

        List<Board> neighbors = new ArrayList<>();
        int[][] copy;
        if (i0 != n - 1) {
            copy = copy();
            exch(copy, i0, j0, i0 + 1, j0);
            neighbors.add(new Board(copy));
        }
        if (i0 != 0) {
            copy = copy();
            exch(copy, i0, j0, i0 - 1, j0);
            neighbors.add(new Board(copy));
        }
        if (j0 != n - 1) {
            copy = copy();
            exch(copy, i0, j0, i0, j0 + 1);
            neighbors.add(new Board(copy));
        }
        if (j0 != 0) {
            copy = copy();
            exch(copy, i0, j0, i0, j0 - 1);
            neighbors.add(new Board(copy));
        }
        return neighbors;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder().append(n);
        for (int i = 0; i < n; i++) {
            sb.append("\n");
            for (int j = 0; j < n; j++) {
                sb.append(String.format("%2d", blocks[i][j]));
            }
        }
        return sb.toString();
    }

    private void calculateHammingAndManhattan() {
        int k = 1;
        hamming = 0;
        manhattan = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int val = blocks[i][j];
                if (val != 0) {
                    if (val != k) {
                        hamming++;
                        int wrongI = (val - 1) / n;
                        int wrongJ = (val - 1) % n;
                        manhattan += Math.abs(i - wrongI) + Math.abs(j - wrongJ);
                    }
                }
                k++;
            }
        }
    }

    private int[][] copy() {
        int[][] copyBlocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copyBlocks[i][j] = blocks[i][j];
            }
        }
        return copyBlocks;
    }

    private void exch(int[][] blocks, int i0, int j0, int i1, int j1) {
        int temp = blocks[i0][j0];
        blocks[i0][j0] = blocks[i1][j1];
        blocks[i1][j1] = temp;
    }
}
