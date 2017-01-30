/*
 * @Zijiao Chen
 * 
 * Solution to hw 4 from Princeton algo 1 on Coursera
 * http://coursera.cs.princeton.edu/algs4/assignments/8puzzle.html
 * 
 * to be finished
 */

public class Solver {
    
    private class SearchNode implements Comparable<SearchNode>{
        private int moves;
        private SearchNode prevNode;
        private Board current;
        private priority;
        
        public SearchNode(Board board, int moves) {
            this.moves = moves;
            prevNode = null;
            current = board;
            priority = this.moves + this.current.manhattan();
        }
        
        @Override
        public int compareTo(SearchNode that) {
            if (this.priority > that.priority) {return 1;}
            else if (this.priority < that.priority) {return -1;}
            else if (this.priority == that. priority) {return 0;}
        }
    }
    
    MinPQ<SearchNode> origPQ;
    MinPQ<SearchNode> twinPQ;
    boolean solvable;
    int solutionMoves; // min number of moves to solve initial board; -1 if unsolvable
    
    public Solver(Board initial) { 
        // find a solution to the initial board (using the A* algorithm)
        MinPQ pq = new MinPQ();
    }
    
    public boolean isSolvable() {           
        // is the initial board solvable?
        return solvable;
    }
    
    public int moves() {
        // min number of moves to solve initial board; -1 if unsolvable
        return solutionMoves;
    }
    
    public Iterable<Board> solution() {
        // sequence of boards in a shortest solution; null if unsolvable
    }
    
    public static void main(String[] args) {
    // solve a slider puzzle
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