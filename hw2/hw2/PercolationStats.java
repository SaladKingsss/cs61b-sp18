package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {


    private int T;
    private double[] ansofSites;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.T = T;
        this.ansofSites = new double[N];
        for (int i = 0; i < T; i++) {
            Percolation site = pf.make(N);
            while (!site.percolates()) {
                int x, y;
                do {
                    x = StdRandom.uniform(N);
                    y = StdRandom.uniform(N);
                } while (site.isOpen(x, y));
                site.open(x, y);
            }
            this.ansofSites[i] = (double) site.numberOfOpenSites() / (double) (N * N);
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

//    public static void main(String[] args) {
//        PercolationStats ps = new PercolationStats(1600, 10, new PercolationFactory());
//        System.out.println(ps.mean());
//        System.out.println(ps.stddev());
//    }
}
