package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {

    private final int BLANK = 0;
    private final int[][] tiles;
    private final int N;
    private int estimatedDistanceToGoal = -1;


    /* Constructs a board from an N-by-N array of tiles
     * where tileAt(i,j) = tile at row i, column j */
    public Board(int[][] tiles) {

        //?
        this.N = tiles.length;
        this.tiles = new int[N][N];

        for (int i = 0; i <= N - 1; i++) {
            for (int j = 0; j <= N - 1; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }

    }

    //Returns value of tile at row i, column j (or 0 if blank)
    public int tileAt(int i, int j) {
        if (i < 0 || i > N - 1 || j < 0 || j > N - 1) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    //Returns the board size N
    public int size() {
        return N;
    }


    private int xyToD(int x, int y) {
        return x * N + y + 1;
    }

    private int getManhattan(int i, int j) {
        int x = (this.tileAt(i, j) - 1) / N;
        int y = (this.tileAt(i, j) - 1) % N;
        return (Math.abs(x - i) + Math.abs(y - j));
    }


    //  Hamming estimate described below
    public int hamming() {
        int ans = 0;
        for (int i = 0; i <= N - 1; i++) {
            for (int j = 0; j <= N - 1; j++) {
                if (this.tileAt(i, j) == BLANK) {
                    continue;
                }
                if (this.tileAt(i, j) != xyToD(i, j)) {
                    ans += 1;
                }
            }
        }
        return ans;
    }

    //  Manhattan estimate described below
    public int manhattan() {
        int ans = 0;
        for (int i = 0; i <= N - 1; i++) {
            for (int j = 0; j <= N - 1; j++) {
                if (this.tileAt(i, j) == BLANK) {
                    continue;
                }
                ans += getManhattan(i, j);
            }
        }
        return ans;
    }

    /**
     * Estimated distance to goal.
     * This method should simply return the results of manhattan()
     * when submitted to autograder.
     */
    @Override
    public int estimatedDistanceToGoal() {
        if (estimatedDistanceToGoal < 0) {
            estimatedDistanceToGoal = manhattan();
        }
        return estimatedDistanceToGoal;
    }

    //Returns true if this board's tile values are the same position as y's
    @Override
    public boolean equals(Object y) {

        if (y == this) {
            return true;
        }
        if (y == null) {
            return false;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }

        Board other = (Board) y;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i <= N - 1; i++) {
            for (int j = 0; j <= N - 1; j++) {
                if (this.tileAt(i, j) != other.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Returns the string representation of the board.
     * Uncomment this method.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int n = size();
        s.append(N + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    /**
     * Returns the neighbors of the current board
     *
     * @author http://joshh.ug/neighbors.html
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

}
