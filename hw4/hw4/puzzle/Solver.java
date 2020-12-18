package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayDeque;

public class Solver {

    private SearchNode head;
    private SearchNode tail;
    private int numOfEnqueue;

    private ArrayDeque<WorldState> ans = new ArrayDeque<>();

    private class SearchNode implements Comparable<SearchNode> {

        //a WorldState.
        private WorldState state;
        //the number of moves made to reach this world state from the initial state.
        private int numOfMoves;
        //a reference to the previous search node.
        private SearchNode prevSearchNode;

        public SearchNode(WorldState state, int numOfMoves, SearchNode prevSearchNode) {
            this.state = state;
            this.numOfMoves = numOfMoves;
            this.prevSearchNode = prevSearchNode;
        }


        @Override
        public int compareTo(SearchNode other) {
            return (this.numOfMoves + this.state.estimatedDistanceToGoal())
                    - (other.numOfMoves + other.state.estimatedDistanceToGoal());
        }

    }


    //bugs still.
    private int getNumOfEnqueue() {
        return numOfEnqueue;
    }

    /**
     * Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     * <p>
     * <p>
     * Remove the search node with minimum priority. Let’s call this node X.
     * If it is the goal node, then we’re done.
     * Otherwise, for each neighbor of X’s world state, create a new search node
     * that obeys the description above and insert it into the priority queue.
     */
    public Solver(WorldState initial) {

        MinPQ<SearchNode> pq = new MinPQ<>();
        this.head = new SearchNode(initial, 0, null);
        pq.insert(this.head);
        numOfEnqueue += 1;

        while (!pq.isEmpty()) {

            SearchNode currentNode = pq.delMin();

            // tail gets from here.
            if (currentNode.state.isGoal()) {
                this.tail = currentNode;
                break;
            }

            for (WorldState neighbor : currentNode.state.neighbors()) {

                // A critical optimization checks that no enqueued WorldState is its own
                if (currentNode.prevSearchNode != null
                        && neighbor.equals(currentNode.prevSearchNode.state)) {
                    continue;
                }

                SearchNode newSearchNode = new SearchNode(neighbor,
                        currentNode.numOfMoves + 1, currentNode);
                pq.insert(newSearchNode);
                numOfEnqueue += 1;
            }
        }

    }

    /**
     * Returns the minimum number of moves to solve the puzzle
     * starting at the initial WorldState.
     * bugs still!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    public int moves() {
        return ans.size() - 1;
    }

    /**
     * Returns a sequence of WorldStates from the initial WorldState to the solution.
     */

    /*
    public Iterable<WorldState> solution() {

        SearchNode temp = this.tail;

        if (temp == null) {
            ans.push(temp.state);
        }

        while (!temp.equals(this.head)) {
            ans.push(temp.state);
            temp = temp.prevSearchNode; //bugs here?
            if (temp == null) {
                ans.push(temp.state);
                break;
            }
        }
        return ans;
    }
    */
    public Iterable<WorldState> solution() {

        while (!tail.equals(head)) {
            ans.push(tail.state);
            tail = tail.prevSearchNode;
        }
        ans.push(tail.state);
        return ans;
    }

}
