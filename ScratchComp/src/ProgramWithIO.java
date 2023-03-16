import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;

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
     * Test 1.
     *
     *
     * the command line arguments
     */

    private static void method1() {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter a String: ");
        String user = in.nextLine();
        for (int i = 0; i < user.length(); i++) {
            char checker = user.charAt(i);
            if (Character.isUpperCase(checker)) {
                out.print(checker + " ");
            }
        }

        in.close();
        out.close();

    }

    /**
     * Test 2.
     *
     *
     * the command line arguments
     */

    private static void method2() {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter a String: ");
        String user = in.nextLine();
        for (int i = 1; i < user.length(); i += 2) {
            out.print(user.charAt(i) + " ");
        }

        in.close();
        out.close();

    }

    /**
     * Test 3.
     *
     *
     * the command line arguments
     */

    private static void method3() {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter a String: ");
        String user = in.nextLine();
        char[] compare = { 'a', 'e', 'i', 'o', 'u' };
        for (int i = 0; i < user.length(); i++) {
            char userChar = Character.toLowerCase(user.charAt(i));

            for (int j = 0; j < compare.length; j++) {

                if (userChar == compare[j]) {
                    userChar = '_';
                    out.print(userChar);

                }
            }

        }

        in.close();
        out.close();

    }

    /**
     * Test 4.
     *
     *
     * the command line arguments
     */

    private static void method4() {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter a String: ");
        String user = in.nextLine();
        char[] compare = { 'a', 'e', 'i', 'o', 'u' };
        int vowelCount = 0;
        for (int i = 0; i < user.length(); i++) {
            char userChar = Character.toLowerCase(user.charAt(i));

            for (int j = 0; j < compare.length; j++) {

                if (userChar == compare[j]) {
                    vowelCount++;
                }
            }

        }

        out.print(vowelCount);

        in.close();
        out.close();
    }

    /**
     * Test 5.
     *
     *
     * the command line arguments
     */

    private static void method5() {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter a String: ");
        String user = in.nextLine();
        char[] compare = { 'a', 'e', 'i', 'o', 'u' };

        for (int i = 0; i < user.length(); i++) {
            char userChar = Character.toLowerCase(user.charAt(i));

            for (int j = 0; j < compare.length; j++) {

                if (userChar == compare[j]) {
                    out.print(i + " ");
                }
            }

        }

        in.close();
        out.close();
    }

    /**
     * Test 6.
     *
     *
     * the command line arguments
     */
    private static int method6(int[] a) {

        int min = a.length;
        int i = 0;

        while (i < a.length) {
            if (a[i] < min) {
                min = a[i];
            }
            i++;

        }
        return min;

    }

    /**
     * Test 7.
     *
     *
     * the command line arguments
     */
    private static void method7() {

        SimpleWriter out = new SimpleWriter1L();
        int i = 1;
        int h = 8;

        while (i <= h) {
            if (i % 2 == 0) {
                i = i + 3;
            } else if (h % 2 == 0) {
                h = h - 3;
            } else {
                i = i + 2;
            }

            i++;

        }
        out.print(i);
        out.print(h);

    }

    /**
     * Test 8.
     *
     * @param a
     *
     * @return the command line arguments
     */

    private static int method8(boolean[] a) {

        for (int i = 0; i < a.length; i++) {
            if (a[i]) {
                i++;
            }

        }
        return i;

    }

    /**
     * Test 9.
     *
     * @param x
     * @param y
     * @param c
     *
     * @return the command line arguments
     */

    private static int method9(int x, int y, boolean c) {
        if (c) {
            x = x * x;
            c = false;
        } else {
            x = y * y;
            c = true;
        }

        int results = x + y;
        return results;
    }

    /**
     * Test 9.
     *
     * @param n
     *
     *
     * @return the command line arguments
     */

    private static int fib(int n) {
        final int three = 3;
        int result = 0;
        if (n >= three) {
            result = fib(n - 1) + fib(n - 2);
        } else {
            result = 1;
        }
        return result;

    }

    /**
     * Test 10.
     *
     * @param n
     * @param base
     *
     * @return the command line arguments
     */

    private static int powerN(int base, int n) {
        int result = 1;
        int pow = 0;
        if (base == 0) {
            result = 1;
        } else if (base % 2 == 0) {
            pow = powerN(base / 2, n);
            pow *= pow;
            result = pow;
        } else if (base % 2 != 0) {
            pow = powerN(base / 2, n);
            pow *= pow * n;
            result = pow;
        }

        return result;

    }

    /**
     * Test 11.
     *
     * @param t
     *
     *
     * @return the command line arguments
     */

    private static int countTagLeaves(XMLTree t) {

        int result = 0;
        if (t.isTag()) {
            if (t.numberOfChildren() == 0) {
                for (int i = 0; i < t.numberOfChildren(); i++) {
                    result += countTagLeaves(t.child(i));
                }
            } else {
                result = 0;

            }

        }
        return result;

    }

    /**
     * Test 11.
     *
     * @param n
     * @param pos
     *
     * @return the command line arguments
     */

    private static int digitAt(NaturalNumber n, int pos) {

        int digit = n.divideBy10();
        int result = 0;
        if (pos > 0) {
            result = digitAt(n, pos - 1);
        } else {
            result = digit;
        }
        n.multiplyBy10(digit);
        return result;

    }

    /**
     * Test 11.
     *
     * @param n
     * @param pos
     *
     * @return the command line arguments
     */

    private static void foo(NaturalNumber n) {
        n.decrement();
        n = new NaturalNumber1L(7);

    }

    /*
     *
     */

    private static int countDigits(NaturalNumber n, int d) {
        int count = 1;
        int digit = n.divideBy10();

        if ((!n.isZero()) && (digit == d)) {
            count = 1 + countDigits(n, d);
            digit = n.divideBy10();
        }
        n.multiplyBy10(digit);
        return count;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */

    public static void main(String[] args) {
        //final int[] a = { 10, 3, 4, 5, 6, 7, 8, 9, 2 };
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        //boolean[] b = { true, true, false, true };
        //final int i = 10;
        //final int j = 100;
        //boolean k = true;
        //int n = 2;

        //method1();
        //method2();
        //method3();
        //method4();
        //method5();
        //out.print(method6(a));
        //method7();
        //out.print(method8(b));
        //out.println(method9(i, j, k));
        //out.println(i);
        //out.println(k);
        //out.println(j);
        //out.print(0 % 2);
        //out.print(fib(n));
        //out.print(powerN(3, 3));

        /**
         *
         * XMLTree xml = new XMLTree1(
         * "http://web.cse.ohio-state.edu/software/2221/web-sw1/" +
         * "extras/instructions/xmltree-model/columbus-weather.xml");
         *
         * //out.println(countTagLeaves(xml)); //xml.display();
         *
         * final int num = 3472; NaturalNumber n = new NaturalNumber2(num); int
         * pos = 2; //out.print(digitAt(n, pos));
         *
         * NaturalNumber num1 = new NaturalNumber2(10); NaturalNumber num2 = new
         * NaturalNumber2();
         *
         * foo(num1); out.print(num1);
         *
         * NaturalNumber hi = new NaturalNumber1L(); out.print(hi);
         *
         */

        /**
         *
         * NaturalNumber zero = new NaturalNumber2(0); NaturalNumber one = new
         * NaturalNumber2(1); NaturalNumber num = new NaturalNumber2(18);
         *
         * if (zero.isZero()) { zero.copyFrom(one); }
         *
         * zero.transferFrom(zero.divide(num));
         *
         * out.print(zero);
         */

        /**
         * NaturalNumber two = new NaturalNumber2(2); NaturalNumber b = new
         * NaturalNumber2(4); b.add(b.divide(two)); out.print(b);
         */

        //int d = 2;
        //NaturalNumber n = new NaturalNumber2(123222);

        //out.println(countDigits(n, d));
        //out.print(n);

        //NaturalNumber test = new NaturalNumber2(9);
        //foo(test);
        //       out.print(test);

        in.close();
        out.close();
    }

}
