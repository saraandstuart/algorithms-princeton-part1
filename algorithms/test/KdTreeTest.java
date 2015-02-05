import org.junit.Test;

/**
 * {@link KdTree} unit test
 * 
 * @author Stuart Shannon
 */
public class KdTreeTest {

	@Test(expected=NullPointerException.class)
	public void throwsNullPointerExceptionIfNullPointPassedToInsert() {
		//given
		KdTree kdTree = new KdTree();
		//when
		kdTree.insert(null);
		//then exception
	}

	@Test(expected=NullPointerException.class)
	public void throwsNullPointerExceptionIfNullPointPassedToContains() {
		//given
		KdTree kdTree = new KdTree();
		//when
		kdTree.contains(null);
		//then exception
	}

	@Test(expected=NullPointerException.class)
	public void throwsNullPointerExceptionIfNullRectPassedToRange() {
		//given
		KdTree kdTree = new KdTree();
		//when
		kdTree.range(null);
		//then exception
	}

	@Test(expected=NullPointerException.class)
	public void throwsNullPointerExceptionIfNullPointPassedToNearest() {
		//given
		KdTree kdTree = new KdTree();
		//when
		kdTree.nearest(null);
		//then exception
	}
	
	

}
