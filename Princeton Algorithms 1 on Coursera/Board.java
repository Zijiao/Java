/*
 * @Zijiao Chen
 * 
 * Solution to hw4 for Princeton Algo on Coursera
 * http://coursera.cs.princeton.edu/algs4/assignments/8puzzle.html
 * 
 *  Two methods not finished
 * 
 */

import java.util.Arrays;
import java.util.ArrayList;

public class Board {
    private int dim; // dim = N while the board is N by N
    private int[][] board;
    private int[][] goal;
    
    public Board(int[][] blocks) {
        // construct a board from an N-by-N array of blocks
        // (where blocks[i][j] = block in row i, column j)
        if (blocks.length < 2) {throw new IllegalArgumentException("N must be at least 2!");}
        
        int goalTemp = 1; // used to construct the goal board
        this.dim = blocks.length;
        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
              this.board[i][j] = blocks[i][j]; //update the starting state
              this.goal[i][j] = goalTemp;
              goalTemp++;
            }
        }
        goal[this.dim - 1][this.dim - 1] = 0; // change the last position of goal to 0
    }
    
    public int dimension() {                
       // board dimension N
        return this.dim;
    }
    
    public int hamming() {                  
       // number of blocks out of place
        int hamm = 0;
        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                if (this.board[i][j] != 0 && this.board[i][j] != goal[i][j]) {hamm++;}
            }
        }
        return hamm;
    }
    
    public int manhattan() {                
       // sum of Manhattan distances between blocks and goal
        int manhatt = 0;
        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                if (this.board[i][j] != 0 && this.board[i][j] != this.goal[i][j]) {
                    // manhatt += row difference + col difference
                    manhatt += Math.abs((this.board[i][j] + 1)/ this.dim - i) + Math.abs((this.board[i][j] + 1)% this.dim - j); 
                }
            }
         }
        return manhatt;
    }
    
    public boolean isGoal() {               
       // is this board the goal board?
        return Arrays.equals(this.board, this.goal);
    }
    
    public Board twin() {                   
      // a boadr that is obtained by exchanging two adjacent blocks in the same row
        int[][] tempBlocks = new int[this.dim][this.dim];
        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
              tempBlocks[i][j] = this.board[i][j]; // just copy
            } 
        }
        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim - 1; j++) {
                if (tempBlocks[i][j] != 0 && tempBlocks[i][j + 1] != 0) {
                    int tempInt = tempBlocks[i][j];
                    tempBlocks[i][j] = tempBlocks[i][j + 1];
                    tempBlocks[i][j + 1] = tempInt;
                    break;
                }
            }
        }
        
        
        Board twinBoard = new Board(tempBlocks);
        return twinBoard;
    }
    
    public boolean equals(Object y) {       
      // does this board equal y?
        if (y == this) {return true;}
        if (y == null) {return false;}
        if (this.getClass() != y.getClass()) {return false;}
        
        return Arrays.equals(this.board, ((Board) y).board) && this.dim == ((Board) y).dim;
    }
    
    public Iterable<Board> neighbors() {    
        // all neighboring boards
    }    
    
    
    public String toString() {              
      // string representation of this board (in the output format specified below)
    }
    

    public static void main(String[] args) {
      // unit tests (not graded)
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        StdOut.print(initial.toString());
        StdOut.print(initial.twin().toString());
        StdOut.println(initial.hamming());
        StdOut.println(initial.manhattan());
        StdOut.println(initial.dimension());
        StdOut.println(initial.isGoal());
        
        for (Board b : initial.neighbors()) {
            StdOut.println(b.toString());
            for (Board d : b.neighbors()) {
                StdOut.println("===========");
                StdOut.println(d.toString());
                StdOut.println("===========");
            }
        }        
    }
}