/**
 * This program takes a command line argument k; reads in a sequence of N 
 * strings from standard input using StdIn.readString(); and prints out exactly 
 * k of them, uniformly at random.  Each item from the sequence can be printed 
 * at most once.
 * 
 * 24th October 2014
 * 
 * @author Stuart Shannon
 * 
 */
public class Subset {
   
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);         // k strings to read
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        
        while (StdIn.hasNextLine() && !StdIn.isEmpty()) {
            String item = StdIn.readString();
            randomizedQueue.enqueue(item);
        }
        
        for (int i = 0; i < k; i++) {
            StdOut.println(randomizedQueue.iterator().next());
        }
        
    }
}