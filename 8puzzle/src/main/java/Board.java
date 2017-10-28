import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int[][] blocks;
    private final int n;
    private int hamming;
    private int manhattan;

    public Board(final int[][] initial) {
        n = initial.length;
        blocks = copy(initial);
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
                    } else if (count == 1) {
                        exch(copyBlocks, iTemp, jTemp, i, j);
                        count++;
                    }
                }
            }
        }
        return new Board(copyBlocks);
    }

    public boolean equals(Object y) {
        if (y == null || !y.getClass().equals(this.getClass())) {
            return false;
        }
        Board other = (Board) y;
        if (blocks.length != other.blocks.length) {
            return false;
        }
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
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
            copy = copy(blocks);
            exch(copy, i0, j0, i0 + 1, j0);
            neighbors.add(new Board(copy));
        }
        if (i0 != 0) {
            copy = copy(blocks);
            exch(copy, i0, j0, i0 - 1, j0);
            neighbors.add(new Board(copy));
        }
        if (j0 != n - 1) {
            copy = copy(blocks);
            exch(copy, i0, j0, i0, j0 + 1);
            neighbors.add(new Board(copy));
        }
        if (j0 != 0) {
            copy = copy(blocks);
            exch(copy, i0, j0, i0, j0 - 1);
            neighbors.add(new Board(copy));
        }
        return neighbors;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder().append(n).append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(String.format("%2d ", blocks[i][j]));
            }
            sb.append("\n");
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

    private int[][] copy(int[][] source) {
        int[][] copyBlocks = new int[n][n];
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[i].length; j++) {
                copyBlocks[i][j] = source[i][j];
            }
        }
        return copyBlocks;
    }

    private void exch(int[][] source, int i0, int j0, int i1, int j1) {
        int temp = source[i0][j0];
        source[i0][j0] = source[i1][j1];
        source[i1][j1] = temp;
    }
}
