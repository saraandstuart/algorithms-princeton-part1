/**
 * Kd-tree exercise KdTree
 * 
 * @author Stuart Shannon
 * 
 */
public class KdTree {

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
        root = insert(root, p, true);
    }

    private Node insert(Node x, Point2D p, boolean vertical) {
        if (x == null) {
            size++;
            return new Node(p, null);
        }

        int cmp = x.compare(p, vertical);

        if (goLeft(cmp)) {
            x.lb = insert(x.lb, p, !vertical);
        }
        else if (goRight(cmp)) {
            x.rt = insert(x.rt, p, !vertical);
        }

        return x; // current node's p is equal to insertion node's p
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

        return true; // current node's p is equal to insertion node's p
    }


    // draw all points to standard draw 
    public void draw() {

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