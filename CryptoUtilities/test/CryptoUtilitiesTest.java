import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Ivan Martinez-kay
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_5734() {
        NaturalNumber n = new NaturalNumber2(5734);
        NaturalNumber nExpected = new NaturalNumber2(5734);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertTrue(result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_1000_100_10() {
        NaturalNumber n = new NaturalNumber2(1000);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber p = new NaturalNumber2(100);
        NaturalNumber pExpected = new NaturalNumber2(100);
        NaturalNumber m = new NaturalNumber2(10);
        NaturalNumber mExpected = new NaturalNumber2(10);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    //tests isWitnesstoCompositeness

    @Test
    public void testIsWitnessToCompositenessBoundary() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber w = new NaturalNumber2(3);
        assertTrue(!CryptoUtilities.isWitnessToCompositeness(w, n));
    }

    @Test
    public void testIsWitnessToCompositenessRoutine() {
        final NaturalNumber n = new NaturalNumber2(42);
        NaturalNumber w = new NaturalNumber2(2);
        assertTrue(CryptoUtilities.isWitnessToCompositeness(w, n));
    }

    @Test
    public void testIsWitnessToCompositenessChallenge() {
        final NaturalNumber n = new NaturalNumber2(69420);
        final NaturalNumber w = new NaturalNumber2(500);
        assertTrue(CryptoUtilities.isWitnessToCompositeness(w, n));
    }

    //tests prime 2

    @Test
    public void testIsPrime2Boundary() {
        final NaturalNumber n = new NaturalNumber2(2);
        assertTrue(CryptoUtilities.isPrime2(n));
    }

    @Test
    public void testIsPrime2Routine() {
        final NaturalNumber n = new NaturalNumber2(17);
        assertTrue(CryptoUtilities.isPrime2(n));
    }

    @Test
    public void testIsPrime2Challenge() {
        NaturalNumber n = new NaturalNumber2(Integer.MAX_VALUE);
        assertTrue(CryptoUtilities.isPrime2(n));
    }

    @Test
    public void testGenerateNextLikelyToPrimeBoundary() {
        NaturalNumber n = new NaturalNumber2(2);
        final NaturalNumber three = new NaturalNumber2(3);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(n, three);

    }

    //tests nextLikelyPrimeGenerated

    @Test
    public void testGenerateNextLikelyToPrimeRoutine() {
        final NaturalNumber n = new NaturalNumber2(15);
        final NaturalNumber seventeen = new NaturalNumber2(17);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(n, seventeen);

    }

    @Test
    public void testGenerateNextLikelyToPrimeChallenge() {
        NaturalNumber n = new NaturalNumber2(Integer.MAX_VALUE - 1);
        final NaturalNumber max = new NaturalNumber2(2147483647);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(n, max);
    }

}
