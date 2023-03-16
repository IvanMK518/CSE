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
public final class ProgramWithIO {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramWithIO() {
    }

    /**
     * Returns the number of digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to count
     * @return the number of digits of {@code n}
     * @ensures numberOfDigits = [number of digits of n]
     */
    private static int numberOfDigits(NaturalNumber n) {

        int result = 1;
        int digit = n.divideBy10();
        if (!n.isZero()) {
            result += numberOfDigits(n);
        }
        n.multiplyBy10(digit);
        return result;
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static int sumOfDigits(NaturalNumber n) {

        int digit = n.divideBy10();
        int result = digit;
        if (!n.isZero()) {
            result += sumOfDigits(n);
        }

        return result;

    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static NaturalNumber sumOfDigits2(NaturalNumber n) {

        int digit = n.divideBy10();
        NaturalNumber result = new NaturalNumber2(digit);
        if (!n.isZero()) {
            result.add(sumOfDigits2(n));
        }
        n.multiplyBy10(digit);
        return result;

    }

    /**
     * Divides {@code n} by 2.
     *
     * @param n
     *            {@code NaturalNumber} to be divided
     * @updates n
     * @ensures 2 * n <= #n < 2 * (n + 1)
     */
    private static void divideBy2(NaturalNumber n) {

        final int result = 10;

        if (!n.isZero()) {
            int digit = n.divideBy10();
            int digit2 = n.divideBy10();

            if (digit2 % 2 != 0) {
                digit += result;

            }
            digit /= 2;
            n.multiplyBy10(digit2);
            divideBy2(n);
            n.multiplyBy10(digit);
        }

    }

    /**
     * Checks whether a {@code String} is a palindrome.
     *
     * @param s
     *            {@code String} to be checked
     * @return true if {@code s} is a palindrome, false otherwise
     * @ensures isPalindrome = (s = rev(s))
     */
    private static boolean isPalindrome(String s) {
        boolean flag = false;
        if (s.length() <= 1) {
            flag = true;
        } else if (s.charAt(0) != s.charAt(s.length() - 1)) {
            flag = false;
        } else {
            flag = isPalindrome(s.substring(1, s.length() - 1));
        }
        return flag;
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

        out.print("Enter a number: ");
        NaturalNumber u = new NaturalNumber2(in.nextInteger());
        int count = numberOfDigits(u);
        out.println("digits: " + count);

        out.print("Enter a number: ");
        NaturalNumber p = new NaturalNumber2(in.nextInteger());
        int count2 = sumOfDigits(p);
        out.println("sum of digits: " + count2);

        out.print("Enter a number: ");
        NaturalNumber n = new NaturalNumber2(in.nextInteger());
        NaturalNumber count3 = new NaturalNumber2(sumOfDigits2(n));
        out.println("sum of digits: " + count3);

        out.print("Enter a number: ");
        NaturalNumber b = new NaturalNumber2(in.nextInteger());
        divideBy2(b);
        out.println("number divided by 2: " + b);

        out.print("Enter a string: ");
        String user = in.nextLine();

        if (isPalindrome(user)) {
            out.print("This is a palindrome.");
        } else {
            out.print("This is not a palindrome.");
        }

        in.close();
        out.close();
    }

}
