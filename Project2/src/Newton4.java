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
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @param epsilon
     *            margin of error of square root computation
     * @return estimate of square root
     */
    private static double sqrt(double x, double epsilon) {

        double r = x;

        //condition checks for 0, because the sqrt of 0 is undefined.
        //NaN stands for not a number.
        if (r == 0) {
            r = Double.NaN;
        } else {
            //this while loop will continue updating r until
            //the |r^2 - x| /x < epsilon^2 condition is met
            //(the ! is another way of saying iterate until)
            while (!(Math.abs(r * r - x) / x < (epsilon * epsilon))) {
                r = (r + x / r) / 2;
            }
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

        out.print("Enter an error range: ");
        //margin of error for sqrt estimation. USER CHOICE.
        double epsilon = in.nextDouble();

        out.print("What number do you want to find the root of? ");
        double x = in.nextDouble();

        //this loop iterates for any non-negative value.
        while (x >= 0) {
            //method call
            out.println(sqrt(x, epsilon));
            out.print("Enter another x value: ");
            x = in.nextDouble();

        }

        in.close();
        out.close();

    }
}
