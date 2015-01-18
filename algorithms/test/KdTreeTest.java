import org.junit.Test;

/**
 * {@link KdTree} unit test
 * 
 * @author Stuart Shannon
 */
public class KdTreeTest {

	@Test(expected=NullPointerException.class)
	public void throwsNullPointerExceptionIfNullPointPassedToInsert() {
		KdTree kdTree = new KdTree();
		kdTree.insert(null);
	}

	@Test(expected=NullPointerException.class)
	public void throwsNullPointerExceptionIfNullPointPassedToContains() {
		KdTree kdTree = new KdTree();
		kdTree.contains(null);
	}

	@Test(expected=NullPointerException.class)
	public void throwsNullPointerExceptionIfNullRectPassedToRange() {
		KdTree kdTree = new KdTree();
		kdTree.range(null);
	}

	@Test(expected=NullPointerException.class)
	public void throwsNullPointerExceptionIfNullPointPassedToNearest() {
		KdTree kdTree = new KdTree();
		kdTree.nearest(null);
	}

}
