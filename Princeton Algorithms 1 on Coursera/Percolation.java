/*
* @author Zijiao Chen
* 
* class Percolation 
* 
* homework 1 for Princeton's Algorithms 1 on Coursera
* 
* for description see http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
* 
*/

public class Percolation {

    // n = N, total amount of sites = n*n
    private int n; 
    
    // grid[i][j] = 0 means blocked, grid[i][j] = 1 means open
    private byte[][] grid; 
    
    // the union-find structure has n*n + 2 elements (2 for head an tail)
    // uf[0] is head, uf[n*n+1] is tail
    // percolate when connected(head, tail)
    private QuickFindUF uf; 
    // or private WeightedQuickUnionUF uf;
    
    
    public Percolation(int N) {              
   // create N-by-N grid, with all sites blocked
       if (N <= 0) {
          throw new IllegalArgumentException("N must be larger than 0!");     
        }       
        n = N;
        
        grid = new byte[N][N];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 0; // all sites blocked
            }
        }
        
        // initialize the union-find structure 
        // union every site in first row to the head, union every site in the last row to the tail
        uf = new QuickFindUF(N * N + 2);
        // or uf = WeightedQuickUnionUF(N*N+2);
        for (int i = 1; i <= N; i++) {
            uf.union(0, i);
        }
        for (int i = N * (N - 1) + 1; i <= N * N; i++) {
            uf.union(N * N + 1, i);
        }
    }
    
    public void open(int i, int j) {         
   // open site (row i, column j) if it is not already
        if (i > n || j > n || i < 1 || j < 1) {
            throw new IndexOutOfBoundsException("i and j must be less than N!");
        }
        if (!isOpen(i, j)) {
            grid[i-1][j-1] = 1;
            
            if (i == 1 && j == 1) {
                // upper-left corner
                if (isOpen(i+1, j)) { uf.union(gridTouf(i, j), gridTouf(i+1, j)); }
                if (isOpen(i, j+1)) { uf.union(gridTouf(i, j), gridTouf(i, j+1)); }
            }
            else if (i == 1 && j == n) {
                // upper-right corner
                if (isOpen(i, j-1)) { uf.union(gridTouf(i, j), gridTouf(i, j-1)); }
                if (isOpen(i+1, j)) { uf.union(gridTouf(i, j), gridTouf(i+1, j)); }
            }
            else if (i == n && j == 1) {
                // bottom-left corner
                if (isOpen(i-1, j)) { uf.union(gridTouf(i, j), gridTouf(i-1, j)); }
                if (isOpen(i, j+1)) { uf.union(gridTouf(i, j), gridTouf(i, j+1)); }
            }
            else if (i == n && j == n) {
                // bottom-right corner
                if (isOpen(i, j-1)) { uf.union(gridTouf(i, j), gridTouf(i, j-1)); }
                if (isOpen(i-1, j)) { uf.union(gridTouf(i, j), gridTouf(i-1, j)); }
            }
            else if (i == 1) {
                // upper row
                if (isOpen(i, j-1)) { uf.union(gridTouf(i, j), gridTouf(i, j-1)); }
                if (isOpen(i, j+1)) { uf.union(gridTouf(i, j), gridTouf(i, j+1)); }
                if (isOpen(i+1, j)) { uf.union(gridTouf(i, j), gridTouf(i+1, j)); }
            }
            else if (i == n) {
                // bottom row
                if (isOpen(i, j-1)) { uf.union(gridTouf(i, j), gridTouf(i, j-1)); }
                if (isOpen(i, j+1)) { uf.union(gridTouf(i, j), gridTouf(i, j+1)); }
                if (isOpen(i-1, j)) { uf.union(gridTouf(i, j), gridTouf(i-1, j)); }
            }
            else if (j == 1) {
                // left colomn
                if (isOpen(i-1, j)) { uf.union(gridTouf(i, j), gridTouf(i-1, j)); }
                if (isOpen(i+1, j)) { uf.union(gridTouf(i, j), gridTouf(i+1, j)); }
                if (isOpen(i, j+1)) { uf.union(gridTouf(i, j), gridTouf(i, j+1)); }
            }
            else if (j == n) {
                // right colomn
                if (isOpen(i-1, j)) { uf.union(gridTouf(i, j), gridTouf(i-1, j)); }
                if (isOpen(i+1, j)) { uf.union(gridTouf(i, j), gridTouf(i+1, j)); }
                if (isOpen(i, j-1)) { uf.union(gridTouf(i, j), gridTouf(i, j-1)); }
            }
            else {
                // all other sites
                if (isOpen(i-1, j)) { uf.union(gridTouf(i, j), gridTouf(i-1, j)); }
                if (isOpen(i+1, j)) { uf.union(gridTouf(i, j), gridTouf(i+1, j)); }
                if (isOpen(i, j-1)) { uf.union(gridTouf(i, j), gridTouf(i, j-1)); }
                if (isOpen(i, j+1)) { uf.union(gridTouf(i, j), gridTouf(i, j+1)); }
            }
        }
    
    }
    public boolean isOpen(int i, int j) {     
   // is site (row i, column j) open?
        if (i > n || j > n || i < 1 || j < 1) {
            throw new IndexOutOfBoundsException("i and j must be less than N!");
        }
        return (grid[i-1][j-1] == 1);
    }
    public boolean isFull(int i, int j) {     
   // is site (row i, column j) full?
        if (i > n || j > n || i < 1 || j < 1) {
           throw new IndexOutOfBoundsException("i and j must be less than N!");
        }
        return uf.connected(0, gridTouf(i, j));
    }
    public boolean percolates() {             
   // does the system percolate?
        return uf.connected(0, n*n+1);
    }
    
    private int gridTouf(int i, int j) {
       return (i-1)*n + j;
    }
    
    public static void main(String[] args) {  
   // test client, optional
        
    }
}