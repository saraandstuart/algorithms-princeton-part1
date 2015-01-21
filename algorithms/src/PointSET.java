
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
    
    public void insert(Point2D p) {
        if (p == null) {
            throw new NullPointerException("Atempt to insert null point");
        }
        
        points.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        
        return points.contains(p);
    }
    
    public void draw() {
    	for (Point2D p : points) {
    		p.draw();
    	}
    }
    
    // all points that are inside the rectangle 
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new NullPointerException();
        }
        
        Stack<Point2D> stack = new Stack<Point2D>();
        
        for (Point2D p : points) {
        	if (rect.contains(p)) {
        		stack.push(p);
        	}
        }
        
        return stack;
    }
    
    // a nearest neighbor in the set to point p; null if the set is empty 
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        
        if (isEmpty()) {
        	return null;
        }
        else {
        	Point2D nearest = null;
        	double minDistance = Double.MAX_VALUE;
        	for (Point2D curr : points) {
        		double currDistance = curr.distanceSquaredTo(p);
        		if (currDistance < minDistance) {
        			minDistance = currDistance;
        			nearest = curr;
        		}
        	}
        	return nearest;
        }
    }

}