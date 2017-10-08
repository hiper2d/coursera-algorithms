import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONSTANT = 1.96;
    private final double[] results;
    private final int t;

    public PercolationStats(int n, int trials) {
        Percolation p;
        results = new double[trials];
        t = trials;

        for (int time = 0; time < trials; time++) {
            p = new Percolation(n);
            int[] randoms = StdRandom.permutation(n * n);
            int randomIndex = 0;

            while (!p.percolates()) {
                int r = randoms[randomIndex++];
                int row = r / n + 1;
                int col = r % n + 1;
                p.open(row, col);
            }

            results[time] = (double) randomIndex / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return mean() - CONSTANT * stddev() / Math.sqrt(t);
    }

    public double confidenceHi() {
        return mean() + CONSTANT * stddev() / Math.sqrt(t);
    }

    public static void main(String[] args) {
        if (args.length >= 2) {
            int n = Integer.parseInt(args[0]);
            int t = Integer.parseInt(args[1]);
            new PercolationStats(n, t);
        }
    }
}
