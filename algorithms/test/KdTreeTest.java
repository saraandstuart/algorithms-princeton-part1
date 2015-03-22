import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

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
    public void insert100000DistinctPointsIn100000by100000GridAndCheckSizeAfterEachInsertion() {
        insertNDistinctPointsInMbyMGridAndCheckSizeAfterEachInsertion(100000, 100000);
    }
    
    @Test
    public void insert100000DistinctPointsIn10000by10000GridAndCheckSizeAfterEachInsertion() {
        insertNDistinctPointsInMbyMGridAndCheckSizeAfterEachInsertion(100000, 10000);
    }
    
    @Test
    public void insert100000DistinctPointsIn1000by1000GridAndCheckSizeAfterEachInsertion() {
        insertNDistinctPointsInMbyMGridAndCheckSizeAfterEachInsertion(100000, 1000);
    }
    
    @Test
    public void insert10000DistinctPointsIn100by100GridAndCheckSizeAfterEachInsertion() {
        insertNDistinctPointsInMbyMGridAndCheckSizeAfterEachInsertion(10000, 100);
    }
    
    @Test
    public void insert100DistinctPointsIn10by10GridAndCheckSizeAfterEachInsertion() {
        insertNDistinctPointsInMbyMGridAndCheckSizeAfterEachInsertion(100, 10);
    }
    
    @Test
    public void insert1DistinctPointsIn1by1GridAndCheckSizeAfterEachInsertion() {
        insertNDistinctPointsInMbyMGridAndCheckSizeAfterEachInsertion(1, 1);
    }
    
    private void insertNDistinctPointsInMbyMGridAndCheckSizeAfterEachInsertion(int N, int M) {
        TreeSet<Point2D> points = createCollectionOfNDistinctPointsInMbyMGrid(N, M);
        insertThencheckSizeAfterEachInsertion(points);
    }
    
    private TreeSet<Point2D> createCollectionOfNDistinctPointsInMbyMGrid(int N, int M) {
        TreeSet<Point2D> points = new TreeSet<Point2D>();

        while (points.size() < N) {
            double x = StdRandom.uniform(M);
            x = x / M;
            double y = StdRandom.uniform(M);
            y = y / M;

            points.add(new Point2D(x, y));
        }
        
        return points;
    }
    
    private void insertThencheckSizeAfterEachInsertion(Collection<Point2D> points) {
        //given points
        //and
        KdTree kdTree = new KdTree();

        int expectedSize = 0;
        for (Point2D point : points) {
            //when
            kdTree.insert(point);
            expectedSize++;
            int actualSize = kdTree.size();
            //then
            assertEquals(expectedSize, actualSize);
        }
    }
    
    @Test
    public void insert100000PointIn100000by100000GridAndCheckSizeAfterEachInsertion() {
        insertNPointsInMbyMGridAndCheckSizeAfterEachInsertion(100000, 100000);
    }
    
    @Test
    public void insert100000PointIn10000by10000GridAndCheckSizeAfterEachInsertion() {
        insertNPointsInMbyMGridAndCheckSizeAfterEachInsertion(100000, 10000);
    }
    
    @Test
    public void insert100000PointIn1000by1000GridAndCheckSizeAfterEachInsertion() {
        insertNPointsInMbyMGridAndCheckSizeAfterEachInsertion(100000, 1000);
    }
    
    @Test
    public void insert100000PointIn100by100GridAndCheckSizeAfterEachInsertion() {
        insertNPointsInMbyMGridAndCheckSizeAfterEachInsertion(100000, 100);
    }
    
    @Test
    public void insert100000PointIn10by10GridAndCheckSizeAfterEachInsertion() {
        insertNPointsInMbyMGridAndCheckSizeAfterEachInsertion(100000, 10);
    }
    
    @Test
    public void insert10PointIn1by1GridAndCheckSizeAfterEachInsertion() {
        insertNPointsInMbyMGridAndCheckSizeAfterEachInsertion(10, 1);
    }
    
    private void insertNPointsInMbyMGridAndCheckSizeAfterEachInsertion(int N, int M) {
        List<Point2D> points = createCollectionOfNPointsInMbyMGrid(N, M);
        insertThencheckSizeAfterEachInsertion(points);
    }
    
    private List<Point2D> createCollectionOfNPointsInMbyMGrid(int N, int M) {
        List<Point2D> points = new ArrayList<Point2D>();

        while (points.size() < N) {
            double x = StdRandom.uniform(M+1);
            x = x / M;
            double y = StdRandom.uniform(M+1);
            y = y / M;

            points.add(new Point2D(x, y));
        }
        
        return points;
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
    public void insert10000PointsIn1000by1000GridAndCheckContainsWithRandomQueryPoints() {
        insertNPointsInMbyMGridAndCheckContainsWithRandomQueryPoints(10000, 1000);
    }
    
    @Test
    public void insert10000PointsIn100by100GridAndCheckContainsWithRandomQueryPoints() {
        insertNPointsInMbyMGridAndCheckContainsWithRandomQueryPoints(10000, 100);
    }
    
    @Test
    public void insert10000PointsIn10by10GridAndCheckContainsWithRandomQueryPoints() {
        insertNPointsInMbyMGridAndCheckContainsWithRandomQueryPoints(10000, 10);
    }
    
    @Test
    public void insert10000PointsIn1by1GridAndCheckContainsWithRandomQueryPoints() {
        insertNPointsInMbyMGridAndCheckContainsWithRandomQueryPoints(10000, 1);
    }
    
    private void insertNPointsInMbyMGridAndCheckContainsWithRandomQueryPoints(int N, int M) {
        List<Point2D> insertPoints = createCollectionOfNPointsInMbyMGrid(N, M);
        List<Point2D> randomPoints = createCollectionOfNPointsInMbyMGrid(N, M);
        insertThenCheckContainsWithRandomQueryPoints(insertPoints, randomPoints);
    }
    
    @Test
    public void insert100000DistinctPointsIn100000by100000GridAndCheckContainsWithRandomQueryPoints() {
        insertNDistinctPointsInMbyMGridAndCheckContainsWithRandomQueryPoints(100000, 100000);
    }
    
    @Test
    public void insert100000DistinctPointsIn10000by10000GridAndCheckContainsWithRandomQueryPoints() {
        insertNDistinctPointsInMbyMGridAndCheckContainsWithRandomQueryPoints(100000, 10000);
    }
    
    @Test
    public void insert100000DistinctPointsIn1000by1000GridAndCheckContainsWithRandomQueryPoints() {
        insertNDistinctPointsInMbyMGridAndCheckContainsWithRandomQueryPoints(100000, 1000);
    }
    
    @Test
    public void insert10000DistinctPointsIn100by100GridAndCheckContainsWithRandomQueryPoints() {
        insertNDistinctPointsInMbyMGridAndCheckContainsWithRandomQueryPoints(10000, 100);
    }
    
    @Test
    public void insert100DistinctPointsIn10by10GridAndCheckContainsWithRandomQueryPoints() {
        insertNDistinctPointsInMbyMGridAndCheckContainsWithRandomQueryPoints(100, 10);
    }
    
    @Test
    public void insert1DistinctPointsIn1by1GridAndCheckContainsWithRandomQueryPoints() {
        insertNDistinctPointsInMbyMGridAndCheckContainsWithRandomQueryPoints(1, 1);
    }
    
    private void insertNDistinctPointsInMbyMGridAndCheckContainsWithRandomQueryPoints(int N, int M) {
        TreeSet<Point2D> insertPoints = createCollectionOfNDistinctPointsInMbyMGrid(N, M);
        TreeSet<Point2D> randomPoints = createCollectionOfNDistinctPointsInMbyMGrid(N, M);
        insertThenCheckContainsWithRandomQueryPoints(insertPoints, randomPoints);
    }
    
    private void insertThenCheckContainsWithRandomQueryPoints(Collection<Point2D> insertPoints, Collection<Point2D> randomPoints) {
        //given points
        //and
        KdTree kdTree = new KdTree();

        for (Point2D point : insertPoints) {
            kdTree.insert(point);
        }
        
        for (Point2D point : randomPoints) {
            boolean expectedResult = insertPoints.contains(point);
            boolean actualResult = kdTree.contains(point);
            
            assertEquals(expectedResult, actualResult);
        }
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
