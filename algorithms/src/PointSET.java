/**
 * Kd-tree exercise PointSET
 * 
 * @author Stuart Shannon
 * 
 */
public class PointSET {

    public PointSET() {

    }
    
    public boolean isEmpty() {
        return false;
    }
    
    public int size() {
        return -1;
    }
    
    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new NullPointerException("Atempt to insert null point");
        }
    }

    // does the set contain point p? 
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        
        return false;
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