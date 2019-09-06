package edu.rit.cs;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {


    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void testSearch()
    {
         final int[] primes = new int[]
                {
                        2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67,
                        71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139,
                        149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223,
                        227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293
                };
        assertTrue(Searching.searchSorted(primes, 173) != -1);
    }

    public void testPrime()
    {
        assertTrue(Prime.isPrime(2));
        Prime.Iterator it = new Prime.Iterator();
        assertEquals(3, it.next());
        assertEquals(5, it.next());
    }

}
