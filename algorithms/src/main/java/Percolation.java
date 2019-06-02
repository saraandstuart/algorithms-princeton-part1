import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

/**
 * This class simulates the percolation problem.
 * <p>
 * Run it from command line by typing java Percolation N, where N is the 
 * size of grid.
 * <p>
 * grid[boolean][boolean] - false means blocked, true means open
 * <p>
 * <ul>
 * <li>index i means row, i.e. usually y from standard geometry</li>
 * <li>index j means column, i.e. usually x from standard geometry</li>
 * <li>So open(i, j) == open(row, column) == open(y, x)</li>
 * </il>
 * 
 * @author Stuart Shannon
 */

public class Percolation {

    private int gridSize;
    private boolean[][] grid;
    private int numberOfOpenSites;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private WeightedQuickUnionUF noBackWashWeightedQuickUnionUF;
    private int virtualTop;
    private int virtualBottom;

    /**
     * Create N-by-N grid, with all sites blocked
     * @param N - grid size
     */
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("N was less than zero");

        gridSize = N;
        grid = new boolean[N+1][N+1];
        weightedQuickUnionUF = new WeightedQuickUnionUF(N*N + 2);
        noBackWashWeightedQuickUnionUF = new WeightedQuickUnionUF(N*N + 2);

        // assign integer values to virtual top and virtual bottom sites
        virtualTop = N*N;
        virtualBottom = N*N + 1;
    }

    /**
     * Open site (row i, column j) if it is not already
     * @param i - row index/x coordinate
     * @param j - column index/y coordinate
     */
    public void open(int i, int j) {
        validateIndices(i, j);

        grid[i][j] = true; // mark site as open
        numberOfOpenSites++;
        
        linkToVirtualTopIfOnTopRow(i, j);
        linkToVirtualBottomIfOnBottomRow(i, j);
        linkToNeighboursIfOpen(i, j);
    }

    /**
     * Validates indices i and j
     * @param i row index/x coordinate
     * @param j column index/y coordinate
     */
    private void validateIndices(int i, int j) {
        if (i <= 0 || i > gridSize) {
            throw new IndexOutOfBoundsException("row index i out of bounds "
                    + "(1 < i <  " + gridSize + "), i was " + i);
        }
        if (j <= 0 || j > gridSize) {
            throw new IndexOutOfBoundsException("column index j out of bounds "
                    + "(1 < j < " + gridSize + "), j was " + j);
        }
    }
    
	private void linkToVirtualTopIfOnTopRow(int i, int j) {
		if (i == 1) {
        	weightedQuickUnionUF.union(virtualTop, ijTo1D(i, j));
        	noBackWashWeightedQuickUnionUF.union(virtualTop, ijTo1D(i, j));
        }
	}
	
	private void linkToVirtualBottomIfOnBottomRow(int i, int j) {
		if (i == gridSize) {
			weightedQuickUnionUF.union(virtualBottom, ijTo1D(i, j));
		}
	}
	
	private void linkToNeighboursIfOpen(int i, int j) {
		linkToNeighbourIfOpenLeft(i, j);
        linkToNeighbourIfOpenRight(i, j);
        linkToNeighBourIfOpenAbove(i, j);
        linkToNeighbourIfOpenBelow(i, j);
	}
    
    private void linkToNeighbourIfOpenLeft(int i, int j) {
        if (j != 1 && isOpen(i, j-1)) {
            weightedQuickUnionUF.union(ijTo1D(i, j), ijTo1D(i, j-1));
            noBackWashWeightedQuickUnionUF.union(ijTo1D(i, j), ijTo1D(i, j-1));
        }
    }

    private void linkToNeighbourIfOpenRight(int i, int j) {
        if (j != gridSize && isOpen(i, j+1)) {
            weightedQuickUnionUF.union(ijTo1D(i, j), ijTo1D(i, j+1));
            noBackWashWeightedQuickUnionUF.union(ijTo1D(i, j), ijTo1D(i, j+1));
        }
    }

    private void linkToNeighBourIfOpenAbove(int i, int j) {
        if (i != 1 && isOpen(i-1, j)) {
            weightedQuickUnionUF.union(ijTo1D(i, j), ijTo1D(i-1, j));
            noBackWashWeightedQuickUnionUF.union(ijTo1D(i, j), ijTo1D(i-1, j));
        }
    }

    private void linkToNeighbourIfOpenBelow(int i, int j) {
        if (i != gridSize && isOpen(i+1, j)) {
            weightedQuickUnionUF.union(ijTo1D(i, j), ijTo1D(i+1, j));
            noBackWashWeightedQuickUnionUF.union(ijTo1D(i, j), ijTo1D(i+1, j));
        }
    }
    
    /**
     * Maps 2D coordinates of grid to 1D union find index.
     * @param i row index/y coordinate, min value is 1
     * @param j column index/x coordinate, min value is 1
     * @return union find index
     */
    private int ijTo1D(int i, int j) {
        return (i-1)*gridSize + (j-1);
    }

    /**
     * Is site (row i, column j) open?
     * @param i - row index/y coordinate
     * @param j - column index/x coordinate
     */
    public boolean isOpen(int i, int j) {
        validateIndices(i, j);
        return grid[i][j];
    }

    /**
     * Is site (row i, column j) full?
     * @param i - row index/y coordinate
     * @param j - column index/x coordinate
     */
    public boolean isFull(int i, int j) {
        return isOpen(i, j) 
                && noBackWashWeightedQuickUnionUF.connected(virtualTop, ijTo1D(i, j));
    }

    /**
     * number of open sites
     * @return
     */
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /**
     * Does the system percolate?
     */
    public boolean percolates() {
        return weightedQuickUnionUF.connected(virtualTop, virtualBottom);
    }

    // test client, optional
    public static void main(String[] args) {
        Percolation p = new Percolation(7);

        StdOut.println(">>> ijTo1D tests");
        // 1. 1,1 => 0
        StdOut.print("1. expected=0, ");
        StdOut.println("actual=" + p.ijTo1D(1, 1));

        // 2. 5,4 => 31
        StdOut.print("2. expected=31, ");
        StdOut.println("actual=" + p.ijTo1D(5, 4));

        // 3. 7,5 => 46
        StdOut.print("3. expected=46, ");
        StdOut.println("actual=" + p.ijTo1D(7, 5));
        StdOut.println("<<< ijTo1D tests");
        
        // test open and constructor
        StdOut.println(">>> open and constructor tests");
        p.open(1, 1);
        p.open(1, 2);
        StdOut.print("1. Is (1,1) and 1,2) connected, expected=true, ");
        StdOut.println("actual=" 
        		+ p.weightedQuickUnionUF.connected(p.ijTo1D(1, 1), p.ijTo1D(1, 2)));
        StdOut.println("<<< open and constructor tests");


    }
}