package lab11.graphs;

/**
 * @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;
    private int[] pathTo;
    private boolean cycleFound = false;
    private Maze maze;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = maze.xyTo1D(1, 1);
        pathTo = new int[maze.V()];
        pathTo[s] = s;
        edgeTo[s] = s;
    }

    // Helper methods go here

    /*
     * For every visited vertex v,
     * if there is an adjacent w such that w is already visited
     * and w is not parent of v, then there is a cycle in graph.
     */
    private void dfs(int v) {
        marked[v] = true;

        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                pathTo[w] = v;
                dfs(w);
            } else if (w != pathTo[v]) {
                edgeTo[w] = v;

                int cur = v;
                do {
                    edgeTo[cur] = pathTo[cur];
                    cur = pathTo[cur];
                } while (cur != w);

                cycleFound = true;
            }
            if (cycleFound) {
                announce();
                return;
            }
        }
    }

    @Override
    public void solve() {
        dfs(s);
    }
}

