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
public final class PasswordChecker {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private PasswordChecker() {
    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param passwordCandidate
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String passwordCandidate,
            SimpleWriter out) {
        boolean uflag = false;
        boolean lflag = false;
        boolean numflag = false;

        for (int i = 0; i < passwordCandidate.length(); i++) {

            char upCase = passwordCandidate.charAt(i);
            if (Character.isUpperCase(upCase)) {
                uflag = true;
            }

            if (Character.isDigit(upCase)) {
                lflag = true;
            }

            if (Character.isLowerCase(upCase)) {
                numflag = true;
            }
        }

        if ((uflag && lflag) || (uflag && numflag) || (lflag && numflag)) {
            out.print("your password works");

        } else {
            out.print("your password sucks");
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
        out.print("Please Enter an appropriate password: ");
        String password = in.nextLine();
        final int length = 8;
        if (password.length() < length) {
            out.print("your password must be at least 8 characters long!");
            out.print("Enter another one:");
            password = in.nextLine();
        }
        checkPassword(password, out);

        /*
         * Put your main program code here
         */
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
