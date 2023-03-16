import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * This program will calculate the square root of the user's input by using
 * Newton Iteration.
 *
 * @author Ivan Martinez-Kay
 *
 */
public final class Newton1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton1() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        //margin of error for sqrt estimation
        final double epsilon = 0.0001;
        double r = x;
        //this while loop will continue updating r until
        //the |r^2 - x| /x < epsilon^2 condition is met
        //(the ! is another way of saying iterate until)
        while (!(Math.abs(r * r - x) / x < (epsilon * epsilon))) {
            r = (r + x / r) / 2;
        }
        //returns calculated value to method
        return r;

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
        out.print("Would you like to find the square root of a number? ");
        String prompt = in.nextLine();

        //prompts the user by asking if they want to run a calculation
        while (prompt.equals("y") || prompt.equals("Y")) {

            out.print("What number do you want to find the root of? ");
            double x = in.nextDouble();
            //method call
            out.println(sqrt(x));
            out.print("Would you like to calculate another? ");
            prompt = in.nextLine();

        }
        in.close();
        out.close();

    }
}
