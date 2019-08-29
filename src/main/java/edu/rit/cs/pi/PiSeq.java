

import java.util.Random;

/**
 * Class PiSeq is a sequential program that calculates an approximate value for
 * &pi; using a Monte Carlo technique. The program generates a number of random
 * points in the unit square (0,0) to (1,1) and counts how many of them lie
 * within a circle of radius 1 centered at the origin. The fraction of the
 * points within the circle is approximately &pi;/4.
 **/
public class PiSeq {

    /**
     * Main program.
     */
    public static void main(String[] args) throws Exception {
        Random prng;

        long seed;
        long N;
        long count;

        // Validate command line arguments.
        if (args.length != 2)
            usage();

        seed = Long.parseLong(args[0]);
        N = Long.parseLong(args[1]);

        // Set up PRNG.
        prng = new Random(seed);

        // Generate n random points in the unit square, count how many are in
        // the unit circle.
        count = 0;
        for (long i = 0; i < N; ++i) {
            double x = prng.nextDouble();
            double y = prng.nextDouble();
            if (x * x + y * y <= 1.0)
                ++count;
        }

        System.out.printf("pi = 4*%d/%d = %.9f%n", count, N, 4.0 * count / N);
    }
    /**
     * Print a usage message and exit.
     */
    private static void usage() {
       System.err.println ("Usage: java PiSeq <seed> <N>");
       System.err.println ("<seed> = Random seed");
       System.err.println ("<N> = Number of random points");
    }
}
