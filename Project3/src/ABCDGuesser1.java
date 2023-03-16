import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ABCDGuesser1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser1() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {

        double userNum = 0;

        // while loop will check for a user input that is greater than 0.
        while (userNum <= 0) {
            out.print("please enter any positive number except for 0: ");
            String input = in.nextLine();
            //format checker ensures that the input is a number that can be parsed.
            if (FormatChecker.canParseDouble(input)) {
                userNum = Double.parseDouble(input);

                //this set of conditional statements will indicate
                //if the user has input a number < 0
                //or a string that cannot be parsed.
                if (userNum <= 0) {
                    out.println("You cannot enter a negative number! ");
                }
            } else {
                out.println("You cannnot enter a letters or words! ");
            }
        }
        //will return parsed string input value.
        return userNum;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {

        double userNum = 0;
        // while loop will check for a user input greater than 1
        while (userNum <= 1) {
            out.print("please enter any positive number except for 0 and 1: ");
            String input = in.nextLine();
            //format checker ensures that the input is a number that can be parsed.
            if (FormatChecker.canParseDouble(input)) {
                userNum = Double.parseDouble(input);

                //this set of conditional statements will indicate
                //if the user has input a number < 1
                //or string that cannot be parsed.
                if (userNum <= 1) {
                    out.println(
                            "You cannot enter a number less than or equal to 1! ");
                }
            } else {
                out.println("You cannnot enter a letters or words! ");
            }
        }
        //will return parsed string input value.
        return userNum;
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

        //this method is called to get the value that will be estimated
        //by the de Jager formula.
        out.println("Initial Value.");
        double constant = getPositiveDouble(in, out);
        out.println();

        //this method is called for times to get 4 values.
        out.println("Four values used for estimation.");
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);

        //array stores values for exponents used.
        final double[] pow = { -5.0, -4.0, -3.0, -2.0, -1.0, -1.0 / 2.0,
                -1.0 / 3.0, -1.0 / 4.0, 0, 1.0 / 4.0, 1.0 / 3.0, 1.0 / 2.0, 1.0,
                2.0, 3.0, 4.0, 5.0 };

        //these variables will store the exponent values used for the final estimation.
        double exp1 = 0, exp2 = 0, exp3 = 0, exp4 = 0;
        final double percentage = 100;

        //array index counter.
        int i = 0, j = 0, k = 0, n = 0;

        double finalEstimate = 0;

        //this set of nested while loops will parse through every index of the array
        //to find the indices that result in an accurate calculation.
        while (i < pow.length) {
            double w1 = Math.pow(w, pow[i]);

            while (j < pow.length) {
                double x1 = Math.pow(x, pow[j]);

                while (k < pow.length) {
                    double y1 = Math.pow(y, pow[k]);

                    while (n < pow.length) {
                        double z1 = Math.pow(z, pow[n]);

                        //de Jager formula
                        double estimate = w1 * x1 * y1 * z1;

                        //This conditional will find the best set of exponent values
                        //for the estimation.
                        if (Math.abs(constant - estimate) < Math
                                .abs(constant - finalEstimate)) {
                            finalEstimate = estimate;
                            exp1 = pow[i];
                            exp2 = pow[j];
                            exp3 = pow[k];
                            exp4 = pow[n];

                        }

                        n++;

                    }
                    k++;
                    n = 0;
                }
                j++;
                k = 0;
            }
            i++;
            j = 0;
        }
        //prints the exponent values used for the final estimation.
        out.println();
        out.println("exponents used: " + w + "^" + exp1 + ", " + x + "^" + exp2
                + ", " + y + "^" + exp3 + ", " + z + "^" + exp4);
        //prints the final estimate to two decimal places
        out.print("The final estimate is: ");
        out.println(finalEstimate, 2, false);
        out.print("the margin of error is: ");
        double relativeError = (finalEstimate - constant) / constant
                * percentage;
        //prints the relative error to two decimal places.
        out.print(Math.abs(relativeError), 2, false);
        out.print("%");

        in.close();
        out.close();

    }

}
