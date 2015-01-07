import java.util.Arrays;

/**
 * Draws points in the plane and links each 4-tuple of collinear points. Uses
 * brute force technique i.e. no special optimising.
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
public class Brute {

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

        // check whether each tuple of 4 points lie on the same line segment
        for (int i = 0; i < N; i++) {
            Point p1 = points[i];

            for (int j = i + 1; j < (N - 2); j++) {
                Point p2 = points[j];
                double slopeP1P2 = p1.slopeTo(p2);

                for (int k = j + 1; k < (N - 1); k++) {
                    Point p3 = points[k];
                    double slopeP1P3 = p1.slopeTo(p3);

                    if (slopeP1P2 == slopeP1P3) {

                        for (int l = k + 1; l < N; l++) {
                            Point p4 = points[l];
                            double slopeP1P4 = p1.slopeTo(p4);

                            if(slopeP1P2 == slopeP1P4) {
                                Point[] collinearPoints = 
                                        new Point[] {p1, p2, p3, p4};
                                Arrays.sort(collinearPoints);
                                collinearPoints[0].drawTo(collinearPoints[3]);

                                String sep = "";
                                for (int m = 0; 
                                        m < collinearPoints.length ; m++) {
                                    StdOut.print(sep);
                                    StdOut.print(collinearPoints[m]);
                                    sep = " -> ";
                                }
                                StdOut.println();
                            }
                        }
                    }
                }
            }
        }

        // display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();

    }

}
