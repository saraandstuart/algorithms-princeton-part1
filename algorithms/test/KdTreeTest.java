import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        //given
        KdTree kdTree = new KdTree();
        Point2D point2D = new Point2D(0.1, 0.1);
        kdTree.insert(point2D);

        //when
        boolean result = kdTree.isEmpty();

        //then
        assertFalse(result);
    }

    @Test
    public void shouldReturnTrueWhenEmpty() {
        //given
        KdTree kdTree = new KdTree();

        //when
        boolean result = kdTree.isEmpty();

        //then
        assertTrue(result);
    }

    @Test
    public void shouldReturnCorrectSize() {
        //given
        KdTree kdTree = new KdTree();
        Point2D point2D = new Point2D(0.1, 0.1);
        kdTree.insert(point2D);
        int expected = 1;

        //when
        int actual = kdTree.size();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void insert100DistinctPointsAndCheckSizeAfterEachInsertion() {
        //given
        KdTree kdTree = new KdTree();

        SET<Point2D> points = new SET<Point2D>();

        while (points.size() < 100) {
            double x = StdRandom.uniform(0.0, 1.0);
            double y = StdRandom.uniform(0.0, 1.0);

            points.add(new Point2D(x, y));
        }

        int i = 0;
        for (Point2D point : points) {
            kdTree.insert(point);
            i++;
            assertTrue(kdTree.size() == i);
        }

    }

    @Test
    public void shouldInsertPoint() {
        //given
        KdTree kdTree = new KdTree();
        assertEquals(0, kdTree.size());

        //when
        Point2D point2D = new Point2D(0.1, 0.1);
        kdTree.insert(point2D);

        //then
        assertEquals(1, kdTree.size());
    }

    @Test
    public void shouldReturnTrueWhenContainsPoint() {
        //given
        KdTree kdTree = new KdTree();
        Point2D point = new Point2D(0.1, 0.1);
        kdTree.insert(point);

        //when
        boolean result = kdTree.contains(point);

        //then
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenDoesNotContainPoint() {
        //given
        KdTree kdTree = new KdTree();
        Point2D point = new Point2D(0.1, 0.1);

        //when
        boolean result = kdTree.contains(point);

        //then
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

        KdTree points = new KdTree();
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

        KdTree points = new KdTree();
        points.insert(point1);
        points.insert(point2);

        Point2D expectedNearest = point2;

        //when
        Point2D actualNearest = points.nearest(point3);

        //then
        assertEquals(expectedNearest, actualNearest);
    }

}
