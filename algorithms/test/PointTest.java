import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 26th October 2014
 * Unit Test for Point
 * @author Stuart Shannon
 */
public class PointTest {

    @Test
    public void compareToPointsTest() {
        Point p = new Point(1, 0);
        Point q = new Point(0, 0);
        
        assertTrue(p.compareTo(q) > 0);
        
        Point r = new Point(0, 0);
        Point s = new Point(1, 0);
        
        assertTrue(r.compareTo(s) < 0);
        
        Point t = new Point(0, 1);
        Point u = new Point(0, 0);
        
        assertTrue(t.compareTo(u) > 0);
        
        Point v = new Point(0, 0);
        Point w = new Point(0, 1);
        
        assertTrue(v.compareTo(w) < 0);
        
        Point a = new Point(0, 0);
        Point b = new Point(0, 0);
        
        assertTrue(a.compareTo(b) == 0);
    }
    
    @Test
    public void slopeToBetweenHorizontalPointsShouldReturnPositiveZero() {
        Point p = new Point(5, 2);
        Point q = new Point(7, 2);
        
        double expected = Double.POSITIVE_INFINITY;
        double slope = p.slopeTo(q);        // result should be +0.0
        double actual = 1/slope;            // dividing 1 by +0.0 results in Double.POSITIVE_INFINITY
        
        assertEquals(expected, actual, 0.0000001);
    }
    
    @Test
    public void slopeToBetweenVerticalPointsShouldReturnPositiveInfinity() {
        Point p = new Point(7, 4);
        Point q = new Point(7, 23);
        
        double expected = Double.POSITIVE_INFINITY;
        double actual = p.slopeTo(q);
        
        assertEquals(expected, actual, 0.0000001);
    }

    @Test
    public void slopeToBetweenIdenticalPointsShouldReturnNegativeInfinity() {
        Point p = new Point(5, 5);
        Point q = new Point(5, 5);
        
        double expected = Double.NEGATIVE_INFINITY;
        double actual = p.slopeTo(q);
        
        assertEquals(expected, actual, 0.0000001);
    }
    
    @Test
    public void shouldCompareThreePointsBySlope() {
        Point p = new Point(5, 5);
        Point q = new Point(7, 11);
        Point r = new Point(20, 78);
        
        int expected = -1;
        int actual = p.SLOPE_ORDER.compare(q, r);
        
        assertEquals(expected, actual);
    }
    
}
