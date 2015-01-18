import org.junit.Test;

/**
 * {@link PointSET} unit test
 * 
 * @author Stuart Shannon
 */
public class PointSETTest {

    @Test(expected=NullPointerException.class)
    public void throwsNullPointerExceptionIfNullPointPassedToInsert() {
        PointSET pointSet = new PointSET();
        pointSet.insert(null);
    }
    
    @Test(expected=NullPointerException.class)
    public void throwsNullPointerExceptionIfNullPointPassedToContains() {
        PointSET pointSet = new PointSET();
        pointSet.contains(null);
    }
    
    @Test(expected=NullPointerException.class)
    public void throwsNullPointerExceptionIfNullRectPassedToRange() {
        PointSET pointSet = new PointSET();
        pointSet.range(null);
    }
    
    @Test(expected=NullPointerException.class)
    public void throwsNullPointerExceptionIfNullPointPassedToNearest() {
        PointSET pointSet = new PointSET();
        pointSet.nearest(null);
    }
    
    

}
