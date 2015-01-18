import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 18th December 2014
 * unit test for Solver
 * @author Stuart Shannon
 */
public class SolverTest {
    
    @Test(expected=NullPointerException.class)
    public void throwsNullPointerExceptionIfNullBoardPassedToConstructor() {
        @SuppressWarnings("unused")
        Solver solver = new Solver(null);
    }
    
    @Test
    public void isSolvableReturnsTrueIfInitialBoardIsSolvable() {
        int[][] tiles = new int [][] {{ 0, 1, 3 },
                                      { 4, 2, 5 },
                                      { 7, 8, 6 }};
        
        Board solvableBoard = new Board(tiles);
        Solver solver = new Solver(solvableBoard);
        
        assertTrue(solver.isSolvable());
    }
    
    @Test
    public void isSolvableReturnsFalseIfInitialBoardIsNotSolvable() {
        int[][] tiles = new int [][] {{ 1, 2, 3 },
                                      { 4, 5, 6 },
                                      { 8, 7, 0 }};
        
        Board unSolvableBoard = new Board(tiles);
        Solver solver = new Solver(unSolvableBoard);
        
        assertFalse(solver.isSolvable());
    }
    
    @Test
    public void movesShouldReturnFour() {
        int[][] tiles = new int [][] {{ 0, 1, 3 },
                                      { 4, 2, 5 },
                                      { 7, 8, 6 }};

        Board solvableBoard = new Board(tiles);
        Solver solver = new Solver(solvableBoard);
        
        int expected = 4;
        int actual = solver.moves();
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void movesShouldReturnMinusOneIfUnsolvable() {
        int[][] tiles = new int [][] {{ 1, 2, 3 },
                                      { 4, 5, 6 },
                                      { 8, 7, 0 }};

        Board unSolvableBoard = new Board(tiles);
        Solver solver = new Solver(unSolvableBoard);
        
        int expected = -1;
        int actual = solver.moves();
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void solutionShouldReturnNullIfUnsolvable() {
        int[][] tiles = new int [][] {{ 1, 2, 3 },
                                      { 4, 5, 6 },
                                      { 8, 7, 0 }};

        Board unSolvableBoard = new Board(tiles);
        Solver solver = new Solver(unSolvableBoard);
        
        Iterable<Board> expected = null;
        Iterable<Board> actual = solver.solution();
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void solutionShouldReturnSequenceOfBoardsInAShortestSolutionIfSolvable() {
        int[][] tiles = new int [][] {{ 0, 1, 3 },
                                      { 4, 2, 5 },
                                      { 7, 8, 6 }};

        Board solvableBoard = new Board(tiles);
        Solver solver = new Solver(solvableBoard);
        
        int[][] tiles_move_1 = new int [][] {{ 1, 0, 3 },
                                             { 4, 2, 5 },
                                             { 7, 8, 6 }};
        
        int[][] tiles_move_2 = new int [][] {{ 1, 2, 3 },
                                             { 4, 0, 5 },
                                             { 7, 8, 6 }};
        
        int[][] tiles_move_3 = new int [][] {{ 1, 2, 3 },
                                             { 4, 5, 0 },
                                             { 7, 8, 6 }};
        
        int[][] tiles_move_4 = new int [][] {{ 1, 2, 3 },
                                             { 4, 5, 6 },
                                             { 7, 8, 0 }};

        Board board_move_1 = new Board(tiles_move_1);
        Board board_move_2 = new Board(tiles_move_2);
        Board board_move_3 = new Board(tiles_move_3);
        Board board_move_4 = new Board(tiles_move_4);
        
        Stack<Board> expectedItr = new Stack<Board>();
        expectedItr.push(board_move_4);
        expectedItr.push(board_move_3);
        expectedItr.push(board_move_2);
        expectedItr.push(board_move_1);
        expectedItr.push(solvableBoard);
        
        Iterable<Board> actualItr = solver.solution();
        
        for (Board actual : actualItr) {
            assertEquals(expectedItr.pop(), actual);
        }
        
    }

}
