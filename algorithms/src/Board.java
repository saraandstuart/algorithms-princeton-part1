import java.util.Arrays;

import edu.princeton.cs.algs4.Stack;

/**
 * 8-Puzzle Board object
 * 
 * 20th November 2014
 * 
 * Enrichment:
 * <p>
 * 1. TODO(SS) - Try with char[] 1d array instead of a 2d array.
 * 
 * @author Stuart Shannon
 * 
 */
public class Board {
    private int[][] tiles;
    private final int N;
    private int hamming = -1;
    private int manhattan = -1;

    /**
     * construct a board from an N-by-N array of blocks 
     * (where blocks[i][j] = block in row i, column j)
     */
    public Board(int[][] blocks) {
        N = blocks.length;
        tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = blocks[i][j];
            }
        }
    }

    /**
     *  board dimension N
     */
    public int dimension() {
        return N;
    }

    /**
     *  number of blocks out of place
     */
    public int hamming() {
        if (hamming != -1) {
            return hamming;
        }

        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int value = tiles[i][j];
                if (!isBlankTile(value) && !isCorrectTileValue(i, j, value) ) {
                    result++;
                }
            }
        }
        hamming = result;
        return hamming;
    }

    /**
     *  sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
        if (manhattan != -1) {
            return manhattan;
        }

        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int value = tiles[i][j];
                if (!isBlankTile(value) && !isCorrectTileValue(i, j, value) ) {
                    result += Math.abs((i + 1) - correctRowFor(value));
                    result += Math.abs((j + 1) - correctColFor(value));
                }
            }
        }

        manhattan = result;

        return manhattan;
    }

    private boolean isBlankTile(int value) {
        return value == 0;
    }

    private boolean isCorrectTileValue(int i, int j, int value) {
        return value == i * N + (j + 1);
    }

    private int correctRowFor(int value) {
        int correctRowValue = value / N;

        if (value % N != 0) {
            correctRowValue++;
        }

        return correctRowValue;
    }

    private int correctColFor(int value) {
        int correctColValue = value % N;

        if (value % N == 0) {
            correctColValue = N;
        }

        return correctColValue;
    }

    /**
     *  is this board the goal board?
     */
    public boolean isGoal() {
        return manhattan() == 0;
    }

    /**
     *  A board that is obtained by exchanging two adjacent 
     *  blocks in the same row
     */
    public Board twin() {
        int[][] twin = clone2DArray(tiles);

        int row = StdRandom.uniform(0, N);

        while (twin[row][0] == 0 || twin[row][1] == 0) {
            row = StdRandom.uniform(0, N);
        }

        int tmp = twin[row][0];
        twin[row][0] = twin[row][1];
        twin[row][1] = tmp;

        return new Board(twin);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null) return false;
        if (!(y instanceof Board)) return false;

        Board other = (Board) y;

        if (N != other.N) return false;
        if (!Arrays.deepEquals(tiles, other.tiles)) return false;

        return true;
    }

    /**
     *  all neighboring boards
     */
    public Iterable<Board> neighbors() {
        Stack<Board> stack = new Stack<Board>();

        // find col and row with zero value
        int row = -1;
        int col = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        // swap left
        if (col > 0) {
            stack.push(new Board(swap(tiles, row, col, row, col - 1)));
        }
        //swap right
        if (col < N - 1) {
            stack.push(new Board(swap(tiles, row, col, row, col + 1)));
        }
        //swap up
        if (row > 0) {
            stack.push(new Board(swap(tiles, row, col, row - 1, col)));
        }
        //swap down
        if (row < N - 1) {
            stack.push(new Board(swap(tiles, row, col, row + 1, col)));
        }

        return stack;
    }

    private int[][] clone2DArray(int[][] original) {
        int len = original.length;
        int[][] clone = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++)
                clone[i][j] = original[i][j];
        }
        return clone;
    }

    private int[][] swap(int[][] array, 
                         int fromRow, 
                         int fromCol, 
                         int toRow, 
                         int toCol) {
        
        int[][] clone = clone2DArray(array);
        int tmp = clone[toRow][toCol];
        clone[toRow][toCol] = clone[fromRow][fromCol];
        clone[fromRow][fromCol] = tmp;
        return clone;
    }


    /**
     *  string representation of this board (in the output format specified 
     *  below)
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    /**
     *  unit tests (not graded)
     */
    public static void main(String[] args) {

        int size = 3;
        for (int i = 0; i < size * size; i++) {
            System.out.println(i + "[" + i / size + "][" + i % size + "]");
        }

    }
}