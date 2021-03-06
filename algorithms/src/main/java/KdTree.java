import java.util.Comparator;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

/**
 * Kd-tree exercise KdTree
 * 
 * @author Stuart Shannon
 * 
 */
public class KdTree {

    private static final double X_MIN = 0.0;
    private static final double X_MAX = 1.0;
    private static final double Y_MIN = 0.0;
    private static final double Y_MAX = 1.0;
    
    private static final Comparator<Point2D> POINT2D_X_ORDER = new Point2DXOrder();
    private static final Comparator<Point2D> POINT2D_Y_ORDER = new Point2DYOrder();
    
    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
        }

        private int compare(Point2D that, boolean vertical) {
            if (vertical) {
                return POINT2D_X_ORDER.compare(p, that);
            }
            return POINT2D_Y_ORDER.compare(p, that);
        }
        
    }
    
    // compare points according to their x-coordinate
    private static class Point2DXOrder implements Comparator<Point2D> {
        public int compare(Point2D p, Point2D q) {
            if (p.x() < q.x()) return -1;
            if (p.x() > q.x()) return +1;
            return 0;
        }
    }

    // compare points according to their y-coordinate
    private static class Point2DYOrder implements Comparator<Point2D> {
        public int compare(Point2D p, Point2D q) {
            if (p.y() < q.y()) return -1;
            if (p.y() > q.y()) return +1;
            return 0;
        }
    }

    private Node root;

    private int size;

    public KdTree() {
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new NullPointerException("Atempt to insert null point");
        }
        root = insert(root, p, X_MIN, Y_MIN, X_MAX, Y_MAX, true);
    }

    private Node insert(Node x, Point2D p, double xmin, double ymin, double xmax, double ymax, boolean vertical) {
        if (x == null) {
            size++;
            return new Node(p, new RectHV(xmin, ymin, xmax, ymax));
        }

        int cmp = x.compare(p, vertical);
        
        boolean nextOrientationVertical = nextOrientation(vertical);

        if (goLeft(cmp)) {
            if (nextOrientationVertical) {
                x.lb = insert(x.lb, p, xmin, ymin, xmax, x.p.y(), nextOrientation(vertical));
            }
            else { //horizontal
                x.lb = insert(x.lb, p, xmin, ymin, x.p.x(), ymax, nextOrientation(vertical));
            }
        }
        else { //goRight
            if (nextOrientationVertical) {
                x.rt = insert(x.rt, p, xmin, x.p.y(), xmax, ymax, nextOrientation(vertical));
            }
            else { //horizontal
                x.rt = insert(x.rt, p, x.p.x(), ymin, xmax, ymax, nextOrientation(vertical));
            }
        }

        return x;
    }
    
    private boolean nextOrientation(boolean vertical) {
        return !vertical;
    }
    
    private boolean goLeft(int cmp) {
        return cmp > 0;
    }

    // does the set contain point p? 
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        return contains(root, p, true);
    }

    private boolean contains(Node x, Point2D p, boolean vertical) {
        if (x == null) return false;

        int cmp = x.compare(p, vertical);
        int cmp2 = x.compare(p, !vertical);

        if (cmp == 0 && cmp2 == 0) {
            return true;
        }
        else if (goLeft(cmp)) {
            return contains(x.lb, p, !vertical);
        }
        else { //goRight
            return contains(x.rt, p, !vertical);
        }
    }


    // draw all points and subdivisions to standard draw 
    public void draw() {
        draw(root, true);
    }
    
    private void draw(Node x, boolean vertical) {
        if (x == null) return;
        
        // draw point
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        x.p.draw();
        
        // draw line
        StdDraw.setPenRadius();
        if (vertical) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(x.p.x(), x.rect.ymin(), x.p.x(), x.rect.ymax());
        }
        else { //horizontal
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.p.y());
        }
        
        draw(x.lb, nextOrientation(vertical));
        draw(x.rt, nextOrientation(vertical));
    }

    // all points that are inside the rectangle 
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new NullPointerException();
        }
        
        SET<Point2D> pointsInRectangle = new SET<Point2D>();
        range(root, rect, pointsInRectangle);

        return pointsInRectangle;
    }
    
    /**
     * <pre>
     * Optimization : 
     * Instead of checking whether the query rectangle intersects the rectangle 
     * corresponding to a node, it suffices to check only whether the query 
     * rectangle intersects the splitting line segment: if it does, then 
     * recursively search both subtrees; otherwise, recursively search the one 
     * subtree where points intersecting the query rectangle could be.
     * </pre>
     */
    private void range(Node x, RectHV rect, SET<Point2D> pointsInRectangle) {
        if (x == null) return;
        
        if (rect.intersects(x.rect)) {
            if (rect.contains(x.p)) {
                pointsInRectangle.add(x.p);
            }
            range(x.lb, rect, pointsInRectangle);
            range(x.rt, rect, pointsInRectangle);
        }
        
    }

    // a nearest neighbour in the set to point p; null if the set is empty 
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }

        return nearest(root, p, root.p);
    }

    private Point2D nearest(Node x, Point2D queryPoint, Point2D closestSoFar) {
        if (x == null) return closestSoFar;

        double distanceClosestSoFarToQueryPoint = 
                closestSoFar.distanceSquaredTo(queryPoint);
        double distanceRectangleToQueryPoint = 
                x.rect.distanceSquaredTo(queryPoint);
        
        if (distanceRectangleToQueryPoint < distanceClosestSoFarToQueryPoint) {
            if(x.p.distanceSquaredTo(queryPoint) < distanceClosestSoFarToQueryPoint) {
                closestSoFar = x.p;
            }
            
            // First check subtree that is on the same side of the splitting line as the queryPoint
            Node first = null;
            Node second = null;
            if (x.rt != null && x.rt.rect.contains(queryPoint)) {
                first = x.rt;
                second = x.lb;
            }
            else {
                first = x.lb;
                second = x.rt;
            }
            
            closestSoFar = nearest(first, queryPoint, closestSoFar);
            closestSoFar = nearest(second, queryPoint, closestSoFar);
        }

        return closestSoFar;
    }

}