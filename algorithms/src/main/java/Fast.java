import java.util.Arrays;
import java.util.TreeSet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * Draws points in the plane and links each 4-tuple of collinear points. Uses
 * a faster technique than brute force.
 * 
 * This program reads points from an input file in the format: An integer N, 
 * followed by N pairs of integers (x,y), each between 0 and 32,767.
 * e.g.
 * %more input6.txt
 * 6
 * 19000 10000
 * 18000 10000
 * 32000 10000
 * 21000 10000
 *  1234  5678
 * 14000 10000
 * 
 * 30th October 2014
 * 
 * @author Stuart Shannon
 * 
 */
public class Fast {

    public static void main(String[] args) {
        
        // re-scale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        // read number of points
        int N = in.readInt();
        Point[] points = new Point[N];
        // read points
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            p.draw();
            points[i] = p;
        }

        // reset the pen radius
        StdDraw.setPenRadius();
        
        Point[] sortedPoints = new Point[N];
        System.arraycopy(points, 0, sortedPoints, 0, points.length);
        
        for (int p = 0; p < N; p++) {
            Point origin = points[p];
            // Sort the points according to the slope they make with p.
            Arrays.sort(sortedPoints, origin.SLOPE_ORDER);
            TreeSet<Point> collinearPoints = new TreeSet<Point>();
            
            // find collinear points w.r.t. the current origin point
            for (int q = 0; q < (N - 1); q++) {
                if (p == q) continue;       // skip if this is origin point
                
                Point curr = sortedPoints[q];
                Point next = sortedPoints[q+1];
                
                if(origin.SLOPE_ORDER.compare(curr, next) == 0) {
                    collinearPoints.add(curr);
                    collinearPoints.add(next);
                }
                else if (collinearPoints.size() > 2) {
                    collinearPoints.add(origin);
                    if (collinearPoints.size() > 3) {
                        drawLineBetweenCollinearPoints(collinearPoints);
                        printCollinearPoints(collinearPoints);
                    }
                    collinearPoints.clear();
                }
                else {
                    collinearPoints.clear();
                }
                
                // if all points are collinear and we get to the last point
                if ((q == (N - 2)) && collinearPoints.size() > 2) {
                    collinearPoints.add(origin);
                    if (collinearPoints.size() > 3) {
                        drawLineBetweenCollinearPoints(collinearPoints);
                        printCollinearPoints(collinearPoints);
                    }
                    collinearPoints.clear();
                }
            }
        }
        
        // display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
    }

    private static void printCollinearPoints(TreeSet<Point> collinearPoints) {
        String sep = "";
        for (Point point : collinearPoints) {
            StdOut.print(sep);
            StdOut.print(point);
            sep = " -> ";
        }
        StdOut.println();
    }

    private static void drawLineBetweenCollinearPoints(
            TreeSet<Point> collinearPoints) {
        Point start = collinearPoints.first();
        Point finish = collinearPoints.last();
        start.drawTo(finish);
    }
}
