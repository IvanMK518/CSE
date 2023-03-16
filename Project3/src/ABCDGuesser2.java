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
public final class ABCDGuesser2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
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

        // while loop will check for a user input that is greater than 1.
        while (userNum <= 1) {
            out.print("please enter any positive number except for 0 and 1: ");
            String input = in.nextLine();

            //format checker ensures that the input is a number that can be parsed.
            if (FormatChecker.canParseDouble(input)) {
                userNum = Double.parseDouble(input);

                //this set of conditional statements will indicate
                //if the user has input a number < 1
                //or a string that cannot be parsed.
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
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param constant
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     * @param w
     * @param x
     * @param y
     * @param z
     */

    private static double calcEstimate(double constant, double w, double x,
            double y, double z, SimpleWriter out) {

        SimpleWriter output = new SimpleWriter1L();
        //array stores values for exponents used.
        final double[] pow = { -5.0, -4.0, -3.0, -2.0, -1.0, -1.0 / 2.0,
                -1.0 / 3.0, -1.0 / 4.0, 0, 1.0 / 4.0, 1.0 / 3.0, 1.0 / 2.0, 1.0,
                2.0, 3.0, 4.0, 5.0 };

        final double percentage = 100;
        double finalEstimate = 0;
        double exp1 = 0, exp2 = 0, exp3 = 0, exp4 = 0;

        //this set of nested for loops will parse through every index of the array
        //to find the indices that result in an accurate calculation.
        //side-note: while loops suck for this program, the for loops were much less
        //a pain in the ass when indexing counters for the exp values.
        //please excuse my 3 am ramblings :).
        for (int i = 0; i < pow.length; i++) {
            double w1 = Math.pow(w, pow[i]);

            for (int j = 0; j < pow.length; j++) {
                double x1 = Math.pow(x, pow[j]);

                for (int k = 0; k < pow.length; k++) {
                    double y1 = Math.pow(y, pow[k]);

                    for (int n = 0; n < pow.length; n++) {
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

                    }

                }

            }

        }
        //prints the exponent values used for the final estimation.
        output.println();
        output.println("exponents used: " + w + "^" + exp1 + ", " + x + "^"
                + exp2 + ", " + y + "^" + exp3 + ", " + z + "^" + exp4);
        output.print("the margin of error is: ");
        double relativeError = (finalEstimate - constant) / constant
                * percentage;
        //prints the relative error to two decimal places.
        output.print(Math.abs(relativeError), 2, false);
        output.println("%");
        output.print("The final estimate is: ");
        output.close();

        return finalEstimate;

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

        //these variables will store the exponent values used for the final estimation.

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

        //prints the final estimate to two decimal places

        out.println(calcEstimate(constant, w, x, y, z, out), 2, false);

        in.close();
        out.close();

    }

}
