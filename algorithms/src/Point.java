import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

/*************************************************************************
 * Name: Stuart Shanon
 * Email: saraandstuart@gmail.com
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/


public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER  = new Comparator<Point>() {

        @Override
        public int compare(Point q, Point r) {
            return Double.compare(slopeTo(q), slopeTo(r));
        }
    };

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {

        int yDiff = (that.y - this.y);
        int xDiff = (that.x - this.x);
        
        double result = 0;
        
        if (xDiff == 0 && yDiff == 0) {
            result = Double.NEGATIVE_INFINITY;
        }
        else {
            result = yDiff / (double)xDiff;
        }
        
        return result;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        int result = this.y - that.y;
        
        if (result == 0) {
            result = this.x - that.x;
        }
        
        return result;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        /* Tests are in a junit test case called PointTest */
    }
}