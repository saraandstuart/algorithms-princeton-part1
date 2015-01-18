import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * {@link PointSET} unit test
 * 
 * @author Stuart Shannon
 */
public class PointSETTest {

    @Test(expected=NullPointerException.class)
    public void throwsNullPointerExceptionIfNullPointPassedToInsert() {
    	// Given
        PointSET pointSet = new PointSET();
        // When
        pointSet.insert(null);
        // Then Exception
    }
    
    @Test(expected=NullPointerException.class)
    public void throwsNullPointerExceptionIfNullPointPassedToContains() {
    	// Given
        PointSET pointSet = new PointSET();
        // When
        pointSet.contains(null);
        // Then Exception
    }
    
    @Test(expected=NullPointerException.class)
    public void throwsNullPointerExceptionIfNullRectPassedToRange() {
    	// Given
        PointSET pointSet = new PointSET();
        // When
        pointSet.range(null);
        // Then Exception
    }
    
    @Test(expected=NullPointerException.class)
    public void throwsNullPointerExceptionIfNullPointPassedToNearest() {
    	// Given
        PointSET pointSet = new PointSET();
        // When
        pointSet.nearest(null);
        // Then Exception
    }
    
    @Test
    public void shouldReturnTrueWhenEmpty() {
    	// Given
    	PointSET pointSet = new PointSET();
    	Point2D point2D = new Point2D(0.1, 0.1);
    	pointSet.insert(point2D);
    	
    	// When
    	boolean result = pointSet.isEmpty();
    	
    	// Then
    	assertFalse(result);
    }
    
    @Test
    public void shouldReturnFalseWhenNonEmpty() {
    	// Given
    	PointSET pointSet = new PointSET();
    	
    	// When
    	boolean result = pointSet.isEmpty();
    	
    	// Then
    	assertTrue(result);
    }
    
    @Test
    public void shouldReturnCorrectSize() {
    	// Given
    	PointSET pointSet = new PointSET();
    	Point2D point2D = new Point2D(0.1, 0.1);
    	pointSet.insert(point2D);
    	int expected = 1;
    	
    	// When
    	int actual = pointSet.size();
    	
    	// Then
    	assertEquals(expected, actual);
    }
    
    @Test
    public void shouldInsertPoint() {
    	// Given
    	PointSET pointSet = new PointSET();
    	assertEquals(0, pointSet.size());
    	
    	// When
    	Point2D point2D = new Point2D(0.1, 0.1);
    	pointSet.insert(point2D);
    	
    	// Then
    	assertEquals(1, pointSet.size());
    }
    
    @Test
    public void shouldReturnTrueWhenContainsPoint() {
    	// Given
    	PointSET points = new PointSET();
    	Point2D point = new Point2D(0.1, 0.1);
    	points.insert(point);
    	
    	// When
    	boolean result = points.contains(point);
    	
    	// Then
    	assertTrue(result);
    }
    
    @Test
    public void shouldReturnFalseWhenDoesNotContainPoint() {
    	// Given
    	PointSET points = new PointSET();
    	Point2D point = new Point2D(0.1, 0.1);
    	
    	// When
    	boolean result = points.contains(point);
    	
    	// Then
    	assertFalse(result);
    }
    

}
