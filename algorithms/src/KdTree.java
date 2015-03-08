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
                return Point2D.X_ORDER.compare(p, that);
            }
            return Point2D.Y_ORDER.compare(p, that);
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

        if (goLeft(cmp)) {
            if (vertical) {
                x.lb = insert(x.lb, p, xmin, ymin, xmax, x.p.y(), toggleOrientation(vertical));
            }
            else { //horizontal
                x.lb = insert(x.lb, p, xmin, ymin, x.p.x(), ymax, toggleOrientation(vertical));
            }
        }
        else if (goRight(cmp)) {
            if (vertical) {
                x.rt = insert(x.rt, p, xmin, x.p.y(), xmax, ymax, toggleOrientation(vertical));
            }
            else { //horizontal
                x.rt = insert(x.rt, p, x.p.x(), ymin, xmax, ymax, toggleOrientation(vertical));
            }
        }

        return x; // current node's p is equal to insert's node's p
    }
    
    private boolean toggleOrientation(boolean vertical) {
        return !vertical;
    }

    private boolean goLeft(int cmp) {
        return cmp > 0;
    }

    private boolean goRight(int cmp) {
        return cmp < 0;
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

        if (goLeft(cmp)) {
            return contains(x.lb, p, !vertical);
        }
        else if (goRight(cmp)) {
            return contains(x.rt, p, !vertical);
        }

        return true; // current node's p is equal to contain's node's p
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
        
        draw(x.lb, toggleOrientation(vertical));
        draw(x.rt, toggleOrientation(vertical));
    }

    // all points that are inside the rectangle 
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new NullPointerException();
        }

        return null;
    }

    // a nearest neighbor in the set to point p; null if the set is empty 
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }

        return null;
    }

}