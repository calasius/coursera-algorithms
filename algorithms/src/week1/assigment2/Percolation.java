// package week1.assigment2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int n;
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufTop;
    private int top;
    private int bottom;
    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n must be greater than 0");
        this.n = n;
        this.grid = new boolean[n][n];
        this.uf = new WeightedQuickUnionUF(n * n + 2);
        this.ufTop = new WeightedQuickUnionUF(n * n + 1);
        this.top = 0;
        this.bottom = n * n + 1;
        this.openSites = 0;
    }

    private int flattenIndex(int row, int col) {
        return (row - 1) * n + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > n)
            throw new IllegalArgumentException("row must be between 1 and n");
        if (col < 1 || col > n)
            throw new IllegalArgumentException("col must be between 1 and n");
        if (isOpen(row, col))
            return;

        openSites++;
        grid[row - 1][col - 1] = true;

        int index = flattenIndex(row, col);
        if (row == 1) {
            uf.union(top, index);
            ufTop.union(top, index);
        }
        if (row == n)
            uf.union(bottom, index);
        if (row > 1 && isOpen(row - 1, col)) {
            uf.union(index, index - n);
            ufTop.union(index, index - n);
        }
        if (row < n && isOpen(row + 1, col)) {
            uf.union(index, index + n);
            ufTop.union(index, index + n);
        }
        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(index, index - 1);
            ufTop.union(index, index - 1);
        }
        if (col < n && isOpen(row, col + 1)) {
            uf.union(index, index + 1);
            ufTop.union(index, index + 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n)
            throw new IllegalArgumentException("row must be between 1 and n");
        if (col < 1 || col > n)
            throw new IllegalArgumentException("col must be between 1 and n");
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > n)
            throw new IllegalArgumentException("row must be between 1 and n");
        if (col < 1 || col > n)
            throw new IllegalArgumentException("col must be between 1 and n");
        int index = flattenIndex(row, col);
        return ufTop.find(top) == ufTop.find(index);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(top) == uf.find(bottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        System.out.println(p.percolates());
        p.open(1, 1);
        p.open(1, 2);
        p.open(1, 3);
        System.out.println(p.isFull(2, 2));
        System.out.println(p.percolates());
    }
}
