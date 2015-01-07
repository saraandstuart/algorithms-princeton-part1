import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 28th November 2014
 * unit test for Board
 * @author Stuart Shannon
 */
public class BoardTest {

    @Test
    public void hamming() {
        int[][] tiles = new int [][] {{ 8, 1, 3 },
                                      { 4, 0, 2 },
                                      { 7, 6, 5 }};
        Board board = new Board(tiles);
        
        assertEquals(5, board.hamming());
    }
    
    @Test
    public void manhatten() {
        int[][] tiles = new int [][] {{ 8, 1, 3 },
                                      { 4, 0, 2 },
                                      { 7, 6, 5 }};
        Board board = new Board(tiles);
        
        assertEquals(10, board.manhattan());
    }
    
    @Test
    public void twin() {
        int[][] tiles = new int [][] {{ 8, 1, 3 },
                                      { 4, 0, 2 },
                                      { 7, 6, 5 }};
        
        int[][] twinArray1 = new int [][] {{ 1, 8, 3 },
                                           { 4, 0, 2 },
                                           { 7, 6, 5 }};
        
        int[][] twinArray2 = new int [][] {{ 8, 3, 1 },
                                           { 4, 0, 2 },
                                           { 7, 6, 5 }};
        
        int[][] twinArray3 = new int [][] {{ 8, 1, 3 },
                                           { 4, 0, 2 },
                                           { 6, 7, 5 }};
        
        int[][] twinArray4 = new int [][] {{ 8, 1, 3 },
                                           { 4, 0, 2 },
                                           { 7, 5, 6 }};
        
        Board board = new Board(tiles);
        Board twin1 = new Board(twinArray1);
        Board twin2 = new Board(twinArray2);
        Board twin3 = new Board(twinArray3);
        Board twin4 = new Board(twinArray4);
        
        List<Board> possibleTwins = new ArrayList<Board>();
        possibleTwins.add(twin1);
        possibleTwins.add(twin2);
        possibleTwins.add(twin3);
        possibleTwins.add(twin4);
        
        Board actualTwin = board.twin();
        
        assertTrue(possibleTwins.contains(actualTwin));
        
    }
    
    @Test
    public void neighbors() {
        int[][] tiles = new int [][] {{ 8, 1, 3 },
                                      { 4, 0, 2 },
                                      { 7, 6, 5 }};

        int[][] neighbor1 = new int [][] {{ 8, 1, 3 },
                                          { 4, 6, 2 },
                                          { 7, 0, 5 }};
        
        int[][] neighbor2 = new int [][] {{ 8, 0, 3 },
                                          { 4, 1, 2 },
                                          { 7, 6, 5 }};
        
        int[][] neighbor3 = new int [][] {{ 8, 1, 3 },
                                          { 4, 2, 0 },
                                          { 7, 6, 5 }};
        
        int[][] neighbor4 = new int [][] {{ 8, 1, 3 },
                                          { 0, 4, 2 },
                                          { 7, 6, 5 }};
        
        Board board = new Board(tiles);
        Board n1 = new Board(neighbor1);
        Board n2 = new Board(neighbor2);
        Board n3 = new Board(neighbor3);
        Board n4 = new Board(neighbor4);
        
        List<Board> expectedNeighbors = new ArrayList<Board>();
        expectedNeighbors.add(n1);
        expectedNeighbors.add(n2);
        expectedNeighbors.add(n3);
        expectedNeighbors.add(n4);
        
        Iterable<Board> iterable = board.neighbors();
        
        List<Board> actualNeighbors = new ArrayList<Board>();
        for (Board curr : iterable) {
            actualNeighbors.add(curr);
        }
        
        assertEquals(expectedNeighbors, actualNeighbors);
    }

}
