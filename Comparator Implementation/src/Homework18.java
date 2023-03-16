import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Homework18 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Homework18() {
    }

    /**
     * Removes and returns the minimum value from {@code q} according to the
     * ordering provided by the {@code compare} method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to compare entries
     * @return the minimum value from {@code q}
     * @updates q
     * @requires <pre>
     * q /= empty_string  and
     *  [the relation computed by order.compare is a total preorder]
     * </pre>
     * @ensures <pre>
     * (q * <removeMin>) is permutation of #q  and
     *  for all x: string of character
     *      where (x is in entries (q))
     *    ([relation computed by order.compare method](removeMin, x))
     * </pre>
     */
    private static String removeMin(Queue<String> q, Comparator<String> order) {
        assert q != null : "Violation of: q is not null";
        assert order != null : "Violation of: order is not null";

        String min = q.dequeue();
        int len = q.length();
        for (int i = 0; i < len; i++) {
            String temp = q.dequeue();
            if (order.compare(min, temp) > 0) {
                q.enqueue(min);
                min = temp;
            } else {
                q.enqueue(temp);
            }

        }

        return min;

    }

    /**
     * Sorts {@code q} according to the ordering provided by the {@code compare}
     * method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to sort
     * @updates q
     * @requires [the relation computed by order.compare is a total preorder]
     * @ensures q = [#q ordered by the relation computed by order.compare]
     */
    public static void sort(Queue<String> q, Comparator<String> order) {

        Queue<String> temp = new Queue1L<>();
        while (q.length() > 0) {
            temp.enqueue(removeMin(q, order));
        }
        q.transferFrom(temp);

    }

    /**
     * Compare {@code String}s in lexicographic order.
     */

    //copied from lab... for some reason doesn't work???

    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        Comparator<String> c = new StringLT();
        Queue<String> q = new Queue1L<>();
        q.enqueue("hi");
        q.enqueue("bye");
        q.enqueue("okey");
        q.enqueue("hello");

        out.println(removeMin(q, c));
        sort(q, c);
        out.println(q);

        in.close();
        out.close();
    }

}
