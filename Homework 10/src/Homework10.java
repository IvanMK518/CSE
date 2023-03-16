import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
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
public final class Homework10 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Homework10() {
    }

    /**
     * Swaps the two given {@code NaturalNumber}s.
     *
     * @param n1
     *            the first {@code NaturalNumber}
     * @param n2
     *            the second {@code NaturalNumber}
     * @updates n1
     * @updates n2
     * @ensures n1 = #n2 and n2 = #n1
     */
    private static void swapNN(NaturalNumber n1, NaturalNumber n2) {

        NaturalNumber temp = new NaturalNumber2(n1);

        n1.copyFrom(n2);
        n2.copyFrom(temp);

    }

    /**
     * Swaps the two given {@code NaturalNumber}s.
     *
     * @param n1
     *            the first {@code NaturalNumber}
     * @param n2
     *            the second {@code NaturalNumber}
     * @updates n1
     * @updates n2
     * @ensures n1 = #n2 and n2 = #n1
     */
    private static void swapNN2(NaturalNumber n1, NaturalNumber n2) {

        NaturalNumber temp = new NaturalNumber2(n1);

        n1.transferFrom(n2);
        n2.transferFrom(temp);

    }

    /**
     * Squares a given {@code NaturalNumber}.
     *
     * @param n
     *            the number to square
     * @updates n
     * @ensures n = #n * #n
     */
    private static void square(NaturalNumber n) {

        NaturalNumber temp = new NaturalNumber2(n);

        n.multiply(temp);

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

        NaturalNumber i = new NaturalNumber2(1);
        NaturalNumber j = new NaturalNumber2(0);

        NaturalNumber k = new NaturalNumber2(5);
        NaturalNumber n = new NaturalNumber2(8);

        swapNN(i, j);
        out.print(i + " ");
        out.println(j + " ");

        swapNN2(k, n);
        out.print(k + " ");
        out.println(n + " ");

        square(n);
        out.print(n);

        in.close();
        out.close();
    }

}
