import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is an implementation of a double-ended or deque (pronounced "deck") 
 * which is a generalisation of a stack and a queue that supports inserting and 
 * removing items from either the front or the back of the data structure.
 * 
 * 7th October 2014
 * 
 * @author Stuart Shannon
 * 
 */
public class Deque<Item> implements Iterable<Item> {
    private int N;          // size of the stack
    private Node sentinel;
    
    private class Node {
        private Item item;
        private Node prev;
        private Node next;
    }
    
    // construct an empty deque
    public Deque() {
        sentinel = new Node();
        sentinel.item = null;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        assert check();
    }
    
    // is the deque empty?
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }
    
    // returns the number of items in the deque.
    public int size() {
        return N;
    }
    
    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null) 
            throw new NullPointerException("Attempt to add null item");
        
        Node first = new Node();
        first.item = item;
        first.prev = sentinel;
        first.next = sentinel.next;
        
        sentinel.next.prev = first;
        sentinel.next = first;
        
        N++;
        assert check();
    }
    
    // insert the item at the end
    public void addLast(Item item) {
        if (item == null) 
            throw new NullPointerException("Attempt to add null item");
        
        Node last = new Node();
        last.item = item;
        last.prev = sentinel.prev;
        last.next = sentinel;
        
        sentinel.prev.next = last;
        sentinel.prev = last;
        
        N++;
        assert check();
    }
    
    // delete and return the item at the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        
        Item item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel; // To avoid loitering
        
        N--;
        assert check();
        return item;
    }
    
    // delete and return the item at the end
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        
        Item item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel; // To avoid loitering
        
        N--;
        assert check();
        return item;
    }
    
    // check internal invariants
    private boolean check() {
        if (sentinel.item != null)
            return false;
        if (N < 0)
            return false;
        else if (N == 0 || N == 1) {
            if (sentinel.next != sentinel.prev)
                return false;
            if (N == 0 && sentinel.next != sentinel)
                return false;
            if (N == 1 && sentinel.next == sentinel)
                return false;
        }
        else {
            if (sentinel.next == sentinel)
                return false;
            if (sentinel.prev == sentinel)
                return false;
        }
        
        // check internal consistency of instance variable N
        int countForward = 0;
        for (Node cursor = sentinel; cursor.next != sentinel; 
                cursor = cursor.next)
            countForward++;
        int countBackward = 0;
        for (Node cursor = sentinel; cursor.prev != sentinel; 
                cursor = cursor.prev)
            countBackward++;
        if (N != countForward || N != countBackward)
            return false;
        
        return true;
    }
    
    // This iterator, doesn't implement remove() since it's optional
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node cursor = sentinel;

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public boolean hasNext() {
            return cursor.next != sentinel;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            cursor = cursor.next;
            return cursor.item;
        }
    }
    
    /**
     * Returns a string representation of this stack.
     * @return the sequence of items in the stack in LIFO order, separated by spaces
     */
//    public String toString() {
//        StringBuilder s = new StringBuilder();
//        for (Item item : this)
//            s.append(item + " ");
//        return s.toString();
//    }

}
