import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        if (args == null || args.length != 1) {
            throw new IllegalArgumentException("There should be exactly one parameter");
        }
        int k = Integer.parseInt(args[0]);
        if (k < 0) {
            throw new IllegalArgumentException("k should be greater than 0");
        }

        int count = 0;
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        while (!StdIn.isEmpty() && k > 0) {
            String input = StdIn.readString();
            if (k > 1 && count++ >= k) {
                queue.dequeue();
            }
            if (k == 1 && count++ >= k) {
                int rand = StdRandom.uniform(2);
                if (rand == 0) {
                    queue.dequeue();
                }
            }
            queue.enqueue(input);
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}