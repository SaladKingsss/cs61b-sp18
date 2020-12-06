package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private Percolation site;
    private int T;
    private double[] ansofSites;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        this.site = pf.make(N);
        this.T = T;
        this.ansofSites = new double[N];
        for (int i = 1; i <= T; i++) {
            while (!this.site.percolates()) {
                int x, y;
                do {
                    x = StdRandom.uniform(N);
                    y = StdRandom.uniform(N);
                } while (this.site.isOpen(x, y));
                this.site.open(x, y);
            }
            this.ansofSites[i] = (double) this.site.numberOfOpenSites() / (double) (N * N);
        }


    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.ansofSites);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.ansofSites);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(this.T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(this.T);
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(1600, 10, new PercolationFactory());
        System.out.println(ps.mean());
        System.out.println(ps.stddev());
    }
}
