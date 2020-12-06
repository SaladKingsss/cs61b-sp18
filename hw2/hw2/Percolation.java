package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {


    private WeightedQuickUnionUF site;
    private int N;
    private boolean[][] openflag;
    private int numofOpen;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N < 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        site = new WeightedQuickUnionUF(N * N + 2); //N*N at top, N*N+1 at bottom.
        this.openflag = new boolean[N][N];
        numofOpen = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.openflag[i][j] = false;
            }
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        this.error(row, col);
        if (this.openflag[row][col]) {
            return;
        }
        this.numofOpen++;
        this.openflag[row][col] = true;

        if (row == 0) {
            this.site.union(N * N, calNumOfPosition(row, col));
        }
        //fix backwash problem.
        if (row == N - 1) {
            this.site.union(calNumOfPosition(row, col), N * N + 1);
        }

        //每次要到写工具函数的时候，我就会觉得这是很难的一件事！！！！！！！
        this.unionOpenNeighbor(row, col, row, col + 1);
        this.unionOpenNeighbor(row, col, row, col - 1);
        this.unionOpenNeighbor(row, col, row + 1, col);
        this.unionOpenNeighbor(row, col, row - 1, col);

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        this.error(row, col);
        return this.openflag[row][col];
    }

    // is the site (row, col) full? (watered or not)
    public boolean isFull(int row, int col) {
        this.error(row, col);
        return this.site.connected(N * N, calNumOfPosition(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return this.numofOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return this.site.connected(N * N, N * N + 1);
    }

    private void unionOpenNeighbor(int row, int col, int newRow, int newCol) {
        if (newRow >= N || newRow < 0 || newCol >= N || newCol < 0) {
            return;
        }
        if (isOpen(newRow, newCol)) {
            this.site.union(calNumOfPosition(row, col), calNumOfPosition(newRow, newCol));
        }
    }

    private void error(int row, int col) {
        if (row >= N || row < 0 || col >= N || col < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    private int calNumOfPosition(int p, int q) {
        return p * this.N + q;
    }

    // use for unit testing (not required)
    public static void main(String[] args) {

    }
}
