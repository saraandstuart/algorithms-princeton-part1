import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * This program takes a command line argument k; reads in a sequence of N 
 * strings from standard input using StdIn.readString(); and prints out exactly 
 * k of them, uniformly at random.  Each item from the sequence can be printed 
 * at most once.
 * 
 * Example command line test:
 * echo "AA BB BB BB BB BB CC CC " | \
 * java -cp ~/git/algorithms-princeton-part1/algortihmslib/algs4.jar:. Subset 8
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
        
        for (String curr : randomizedQueue) {
            if (k > 0) {
                StdOut.println(curr);
            }
            else {
                break;
            }
            k--;
        }
        
    }
}