import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 18th October 2014
 * Unit Test for Deque
 * @author Stuart Shannon
 */
public class DequeTest {

    @Test
    public void addFirstThenRemoveLastShouldRemoveInSameOrderAdded() {
        Deque<Integer> deque = new Deque<Integer>();
        int N = 5;
        
        for (int i=0 ; i < N ; i++) {
            deque.addFirst(i);
        }
        
        for (int expected = 0 ; expected < N ; expected++) {
            int actual = deque.removeLast();
            assertEquals(expected, actual);
        }
    }
    
    @Test
    public void emptyToNonEmptyToEmptyTransitionShouldBehaveCorrectly() {
        Deque<String> deque = new Deque<String>();
        
        deque.addLast("item");
        assertFalse(deque.isEmpty());
        
        deque.removeFirst();
        assertTrue(deque.isEmpty());
        
        deque.addFirst("item");
        assertFalse(deque.isEmpty());
        
        deque.removeLast();
        assertTrue(deque.isEmpty());
    }
    
    @Test
    public void multipleIteratorsCanBeUsedSimultaneously() {
        Deque<Integer> deque = new Deque<Integer>();
        int N = 5;
        
        for (int i=0 ; i<N ; i++) {
            deque.addLast(i);
        }
        
        String expected = "001234101234201234301234401234";
        String actual = "";
        
        for(Integer curr1 : deque) {
            actual += curr1;
            for (Integer curr2 : deque) {
                actual += curr2;
            }
        }
        
        assertEquals(expected, actual);
    }
}
