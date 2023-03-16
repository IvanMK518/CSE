import components.map.Map;
import components.naturalnumber.NaturalNumber;
import components.queue.Queue;
import components.queue.Queue1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.stack.Stack;
import components.stack.Stack1L;

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
     *
     * @param m
     * @return qi
     */
    public static Queue<Integer> getKeyValue(Map<Integer, Integer> m) {
        Queue<Integer> qi = new Queue1L<>();
        int len = m.size();
        for (int i = 0; i < len; i++) {
            Map.Pair<Integer, Integer> temp = m.removeAny();
            int key = temp.key();
            int value = temp.value();
            qi.enqueue(key);
            qi.enqueue(value);
        }

        return qi;

    }

    /**
     *
     * @param s
     * @return temp
     */

    public static Sequence<Integer> stackTop(Sequence<Stack<Integer>> s) {

        Sequence<Integer> temp = new Sequence1L<>();
        SimpleWriter out = new SimpleWriter1L();

        int len = s.length();
        for (int i = 0; i < len; i++) {
            Stack<Integer> tempStack = s.remove(0);
            Integer top = tempStack.pop();
            temp.add(i, top);
        }

        return temp;

    }

    /**
     *
     * @param x
     * @param y
     */

    public static void foo(NaturalNumber x, NaturalNumber y) {
        NaturalNumber rem = x.divide(y);
        x = y;
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
        //Map<Integer, Integer> m = new Map1L<>();
        //m.add(1, 2);
        //m.add(2, 3);
        //m.add(3, 4);
        //out.print(getKeyValue(m));

        Sequence<Stack<Integer>> s = new Sequence1L<>();
        Stack<Integer> stack1 = new Stack1L<>();
        stack1.push(1);
        stack1.push(2);

        Stack<Integer> stack2 = new Stack1L<>();
        stack2.push(2);
        stack2.push(1);

        Stack<Integer> stack3 = new Stack1L<>();
        stack3.push(2);
        stack3.push(5);

        s.add(0, stack1);
        s.add(1, stack2);
        s.add(2, stack3);

        out.print(stackTop(s));

        //NaturalNumber n1 = new NaturalNumber1L(100);

        //NaturalNumber n2 = new NaturalNumber1L(50);
        //foo(n1, n2);

        //out.print(n1 + " " + n2);

        in.close();
        out.close();
    }

}
