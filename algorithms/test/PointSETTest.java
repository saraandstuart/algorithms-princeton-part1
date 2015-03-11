import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	public void shouldReturnFalseWhenNonEmpty() {
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
	public void shouldReturnTrueWhenEmpty() {
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
	
    @Test
    public void shouldReturnIterableOfPointsInsideRectangle() {
        //given
        RectHV rect = new RectHV(0.1, 0.1, 0.5, 0.5);

        Point2D point1 = new Point2D(0.1, 0.1);
        Point2D point2 = new Point2D(0.2, 0.2);
        Point2D point3 = new Point2D(0.5, 0.5);
        Point2D point4 = new Point2D(0.6, 0.6);

        PointSET points = new PointSET();
        points.insert(point1);
        points.insert(point2);
        points.insert(point3);
        points.insert(point4);

        List<Point2D> expectedPointsInRectangle = Arrays.asList(point1, point2, point3);
        
        //when
        Iterable<Point2D> range = points.range(rect);

        List<Point2D> actualPointsInRectangle = new ArrayList<Point2D>();
        for (Point2D p : range) {
            actualPointsInRectangle.add(p);
        }

        //then
        assertEquals(expectedPointsInRectangle, actualPointsInRectangle);
    }

    @Test
    public void shouldFindNearestPoint() {
        //given
        Point2D point1 = new Point2D(0.1, 0.1);
        Point2D point2 = new Point2D(0.2, 0.2);
        Point2D point3 = new Point2D(0.3, 0.3);

        PointSET points = new PointSET();
        points.insert(point1);
        points.insert(point2);

        Point2D expectedNearest = point2;
        
        //when
        Point2D actualNearest = points.nearest(point3);
        
        //then
        assertEquals(expectedNearest, actualNearest);
    }


}
