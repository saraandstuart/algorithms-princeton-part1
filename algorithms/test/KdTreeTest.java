import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * {@link KdTree} unit test
 * 
 * @author Stuart Shannon
 */
public class KdTreeTest {

    @Test(expected=NullPointerException.class)
    public void throwsNullPointerExceptionIfNullPointPassedToInsert() {
        //given
        KdTree kdTree = new KdTree();
        //when
        kdTree.insert(null);
        //then exception
    }

    @Test(expected=NullPointerException.class)
    public void throwsNullPointerExceptionIfNullPointPassedToContains() {
        //given
        KdTree kdTree = new KdTree();
        //when
        kdTree.contains(null);
        //then exception
    }

    @Test(expected=NullPointerException.class)
    public void throwsNullPointerExceptionIfNullRectPassedToRange() {
        //given
        KdTree kdTree = new KdTree();
        //when
        kdTree.range(null);
        //then exception
    }

    @Test(expected=NullPointerException.class)
    public void throwsNullPointerExceptionIfNullPointPassedToNearest() {
        //given
        KdTree kdTree = new KdTree();
        //when
        kdTree.nearest(null);
        //then exception
    }

    @Test
    public void shouldReturnFalseWhenNonEmpty() {
        // Given
        KdTree kdTree = new KdTree();
        Point2D point2D = new Point2D(0.1, 0.1);
        kdTree.insert(point2D);

        // When
        boolean result = kdTree.isEmpty();

        // Then
        assertFalse(result);
    }
    
    @Test
    public void shouldReturnTrueWhenEmpty() {
        // Given
        KdTree kdTree = new KdTree();

        // When
        boolean result = kdTree.isEmpty();

        // Then
        assertTrue(result);
    }
    
    @Test
    public void shouldReturnCorrectSize() {
        // Given
        KdTree kdTree = new KdTree();
        Point2D point2D = new Point2D(0.1, 0.1);
        kdTree.insert(point2D);
        int expected = 1;

        // When
        int actual = kdTree.size();

        // Then
        assertEquals(expected, actual);
    }
    
    @Test
    public void shouldInsertPoint() {
        // Given
        KdTree kdTree = new KdTree();
        assertEquals(0, kdTree.size());

        // When
        Point2D point2D = new Point2D(0.1, 0.1);
        kdTree.insert(point2D);

        // Then
        assertEquals(1, kdTree.size());
    }
    
    @Test
    public void shouldReturnTrueWhenContainsPoint() {
        // Given
        KdTree kdTree = new KdTree();
        Point2D point = new Point2D(0.1, 0.1);
        kdTree.insert(point);

        // When
        boolean result = kdTree.contains(point);

        // Then
        assertTrue(result);
    }
    
    @Test
    public void shouldReturnFalseWhenDoesNotContainPoint() {
        // Given
        KdTree kdTree = new KdTree();
        Point2D point = new Point2D(0.1, 0.1);

        // When
        boolean result = kdTree.contains(point);

        // Then
        assertFalse(result);
    }

}
