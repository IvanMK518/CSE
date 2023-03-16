import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Monte Carlo Estimate: compute percentage of pseudo-random points in [0.0,1.0)
 * interval that fall in the left half subinterval [0.0,0.5).
 */
public final class MonteCarlo {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private MonteCarlo() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * Open input and output streams
         */
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();
        /*
         * Ask user for number of points to generate
         */
        output.print("Number of points: ");
        int n = input.nextInteger();
        /*
         * Declare counters and initialize them
         */
        int ptsInSquare = 0, ptsInCircle = 0;
        /*
         * Create pseudo-random number generator
         */
        Random rnd = new Random1L();
        Random rnd2 = new Random1L();
        final double radius = 1.0;
        final double sqA = 4.0;

        /*
         * Generate points and count how many fall in [0.0,0.5) interval
         */
        while (ptsInSquare < n) {
            /*
             * Generate pseudo-random number in [0.0,1.0) interval
             */
            double x = rnd.nextDouble() * 2.0;
            double y = rnd2.nextDouble() * 2.0;
            /*
             * Increment total number of generated points
             */
            ptsInSquare++;
            /*
             * Check if point is in [0.0,0.5) interval and increment counter if
             * it is
             */
            if ((x - 1) * (x - 1) + (y - 1) * (y - 1) <= radius * radius) {
                ptsInCircle++;
            }
        }
        /*
         * Estimate percentage of points generated in [0.0,1.0) interval that
         * fall in the [0.0,0.5) subinterval
         */
        double estimate = ptsInCircle;
        output.println("Area " + (estimate / n) * sqA);
        output.println("Area of a circle with radius 1: " + Math.PI);
        /*
         * Close input and output streams
         */
        input.close();
        output.close();
    }

}