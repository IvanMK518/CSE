import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
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
public final class ProgramWithIO {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramWithIO() {
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

        Queue<Integer> q = new Queue1L<>();
        q.enqueue(1);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(1);
        q.enqueue(3);

        Set<Integer> s = new Set1L<>();

        int len = q.length();

        for (int i = 0; i < len; i++) {
            int temp = q.dequeue();
            if (!s.contains(temp)) {
                s.add(temp);
            }

        }

        int len2 = s.size();
        for (int i = 0; i < len2; i++) {
            int temp = s.removeAny();
            q.enqueue(temp);

        }

        out.print(q);

        in.close();
        out.close();
    }

}
