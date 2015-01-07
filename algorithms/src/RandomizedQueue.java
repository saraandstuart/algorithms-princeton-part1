import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is an implementation of a randomized deque which is a similar to a 
 * stack or queue, except that the item removed is chosen uniformly at random 
 * from items in the data structure.
 * 
 * 23rd October 2014
 * 
 * @author Stuart Shannon
 * 
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;            // queue elements
    private int N = 0;           // number of elements on queue

    // construct an empty randomized queue
    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        // cast needed since no generic array creation in Java
        q = (Item[]) new Object[2];
    }
    
    // is the queue empty?
    public boolean isEmpty() {
        return N == 0;
    }
    
    // return the number of items on the queue
    public int size() {
        return N;
    }
    
    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= N;
        @SuppressWarnings("unchecked")
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = q[i];
        }
        q = temp;
    }
    
    // add the item
    public void enqueue(Item item) {
        if (item == null) 
            throw new NullPointerException("Attempt to add null item");
        
        // double size of array if necessary and re-copy to front of array
        if (N == q.length) resize(2*q.length);   // double size of array if necessary
        q[N++] = item;                           // add item
    }
    
    // delete and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        
        int randomIndex = StdRandom.uniform(N);
        Item item = q[randomIndex];
        q[randomIndex] = q[--N];
        q[N] = null;
        
        // shrink size of array if necessary
        if (N > 0 && N == q.length/4) resize(q.length/2); 
        return item;
    }
    
    // return (but do not delete) a random item
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return q[StdRandom.uniform(N)];
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    
    // an iterator, doesn't implement remove() since it's optional
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i = 0;
        private Item[] qClone;
        
        @SuppressWarnings("unchecked")
        public RandomizedQueueIterator() {
            i = N;
            qClone = (Item[]) new Object[i];
            
            for (int j = 0; j < N; j++) {
                qClone[j] = q[j];
            }
            
            StdRandom.shuffle(qClone);
        }
        
        public boolean hasNext() {
            return i > 0;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return qClone[--i];
        }
    }
    
    /**
     * Returns a string representation of this queue.
     * @return the sequence of items in the stack in random order, 
     *         separated by spaces
     */
//    public String toString() {
//        StringBuilder s = new StringBuilder();
//        for (Item item : this)
//            s.append(item + " ");
//        return s.toString();
//    }
}
