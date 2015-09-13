import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

/**
 * 8-Puzzle Solver object
 * <p>
 * 20th November 2014
 * <p>
 * 
 * Enrichment:
 * <p>
 * 1. TODO(SS) -  Exploit the fact that the difference in Manhattan distance 
 * between a board and a neighbor is either +1, -1, or 0, based on the 
 * direction that the block moves.
 * <br/> 
 * 2. TODO(SS) - Use only one PQ to run the A* algorithm on the initial board 
 * and its twin.
 * <br/>
 * 3. TODO(SS) - When two search nodes have the same Manhattan priority, you can 
 * break ties however you want, e.g., by comparing either the Hamming or 
 * Manhattan distances of the two boards.
 * <br/>
 * 4. TODO(SS) - Optimize the order in which neighbors() returns the neighbors 
 * of a board.
 * <br/>
 * 5. TODO(SS) - Use a parity argument to determine whether a puzzle is 
 * unsolvable (instead of two synchronous A* searches). However, this will 
 * either break the API or will require a fragile dependence on the toString() 
 * method.
 * 
 * @author Stuart Shannon
 */
public class Solver {
    
    private SearchNode solution;
    private SearchNode twinSolution;
    
    private class SearchNode implements Comparable<SearchNode> {

        private final Board board;
        private final SearchNode prev;
        private final int numMoves;
        
        public SearchNode(Board board, SearchNode prev) {
            super();
            this.board = board;
            this.prev = prev;
            this.numMoves = (prev == null) ? 0 : prev.numMoves + 1;
        }

        @Override
        public String toString() {
            return "priority=" + board.manhattan() + numMoves + "\n" 
                   + "moves=" + numMoves + "\n"
                   + "manhatten=" + board.manhattan() + "\n"
                   + board;
        }

        @Override
        public int compareTo(SearchNode other) {
            return (this.board.manhattan() + this.numMoves) - 
                   (other.board.manhattan() + other.numMoves);
        }
        
    }
    
    /**
     *  Find a solution to the initial board (using the A* algorithm).
     *  Either solution or twinSolution will end up containing the goal board.
     */
    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException("Attempt to initialize Solver "
                    + "with a null board");
        }
        
        MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>();
        minPQ.insert(new SearchNode(initial, null));
        
        MinPQ<SearchNode> twinMinPQ = new MinPQ<SearchNode>();
        twinMinPQ.insert(new SearchNode(initial.twin(), null));
        
        solve(minPQ, twinMinPQ);
    }
    
    /**
     * Populates minPQ and deletes min until goal is found in either solution 
     * or twin 
     */
    private void solve(MinPQ<SearchNode> minPQ, MinPQ<SearchNode> twinMinPQ) {
        solution = minPQ.delMin();
        twinSolution = twinMinPQ.delMin();
        
        while (!solution.board.isGoal() && !twinSolution.board.isGoal()) {
            
            for (Board board : solution.board.neighbors()) {
                if(solution.prev==null || !board.equals(solution.prev.board)) {
                    minPQ.insert(new SearchNode(board, solution));
                }
            }
            
            solution = minPQ.delMin();
            
            for (Board board : twinSolution.board.neighbors()) {
                if(twinSolution.prev==null || 
                   !board.equals(twinSolution.prev.board)) {
                    twinMinPQ.insert(new SearchNode(board, twinSolution));
                }
            }
            
            twinSolution = twinMinPQ.delMin();
        }
    }
    
    /**
     *  Is the initial board solvable?
     */
    public boolean isSolvable() {
        return solution.board.isGoal();
    }
    
    /**
     *  Min number of moves to solve initial board; -1 if unsolvable
     */
    public int moves() {
        if(isSolvable()) {
            return solution.numMoves;
        }
        else {
            return -1;
        }
    }
    
    /**
     *  Sequence of boards in a shortest solution; null if unsolvable
     */
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;

        Stack<Board> stack = new Stack<Board>();
        for(SearchNode solutionStep = solution ; solutionStep != null ; 
                solutionStep = solutionStep.prev) {
            stack.push(solutionStep.board);
        }

        return stack;
    }
    
    /**
     *  solve a slider puzzle.
     */
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}