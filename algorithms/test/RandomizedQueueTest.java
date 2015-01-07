import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


/**
 * 24th October 2014
 * Unit Test for RandomizedQueue
 * @author Stuart Shannon
 */
public class RandomizedQueueTest {
    
    @Test
    public void emptyToNonEmptyToEmptyTransitionShouldBehaveCorrectly() {
        RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();
        
        randomQueue.enqueue("item");
        assertFalse(randomQueue.isEmpty());
        
        randomQueue.dequeue();
        assertTrue(randomQueue.isEmpty());
        
        randomQueue.enqueue("item");
        assertFalse(randomQueue.isEmpty());
        
        randomQueue.dequeue();
        assertTrue(randomQueue.isEmpty());
    }
    
    @Test
    public void multipleIteratorsCanBeUsedSimultaneously() {
        RandomizedQueue<Integer> randomQueue = new RandomizedQueue<Integer>();
        int N = 5;
        
        for (int i=0 ; i<N ; i++) {
            randomQueue.enqueue(i);
        }
        
        String actual1 = "";
        String actual2 = "";
        
        for(Integer curr1 : randomQueue) {
            actual1 += curr1;
            for (Integer curr2 : randomQueue) {
                actual2 += curr2;
            }
        }
        
        assertEquals(5, actual1.length());
        assertEquals(25, actual2.length());
        assertFalse(actual1.equals(actual2));
    }
}
