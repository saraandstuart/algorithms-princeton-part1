
/**
 * Kd-tree exercise PointSET
 * 
 * @author Stuart Shannon
 * 
 */
public class PointSET {

	private SET<Point2D> points;
	
    public PointSET() {
    	points = new SET<Point2D>();
    }
    
    public boolean isEmpty() {
        return points.isEmpty();
    }
    
    public int size() {
        return points.size();
    }
    
    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new NullPointerException("Atempt to insert null point");
        }
        
        points.add(p);
    }

    // does the set contain point p? 
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        
        return points.contains(p);
    }
    
    // draw all points to standard draw 
    public void draw() {
    	throw new UnsupportedOperationException();
    }
    
    // all points that are inside the rectangle 
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new NullPointerException();
        }
        
        throw new UnsupportedOperationException();
    }
    
    // a nearest neighbor in the set to point p; null if the set is empty 
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        
        throw new UnsupportedOperationException();
    }

}