/***
 * Peizhao Hu, Peizhao.Hu@rit.edu
 * Based on https://www.cs.rit.edu/~ark/bcbd_2/java2html.php?file=1
 */

package edu.rit.cs.primality;

public class Primality_Seq {

    /**
     * Main program.
     */
    public static void main(String[] args)
            throws Exception
    {
        // Validate command line arguments.
        if (args.length < 1) usage();

        // Test numbers for primality.
        for (int i = 0; i < args.length; ++ i)
            if (isPrime (Long.parseLong (args[i])))
                System.out.printf ("%s%n", args[i]);
    }

    // Hidden operations.

    /**
     * Test the given number for primality.
     *
     * @param  x  Number &ge; 3.
     *
     * @return  True if x is prime, false otherwise.
     */
    private static boolean isPrime(long x)
    {
        if (x % 2 == 0) return false;
        long p = 3;
        long psqr = p*p;
        while (psqr <= x)
        {
            if (x % p == 0) return false;
            p += 2;
            psqr = p*p;
        }
        return true;
    }

    /**
     * Print a usage message and exit.
     */
    private static void usage()
    {
        System.err.println ("Usage: java edu.rit.cs.primality.Primality_Seq <number> ...");
    }


}
