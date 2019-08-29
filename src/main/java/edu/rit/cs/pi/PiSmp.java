import java.util.Random;
import java.util.Arrays;

/**
 * Class PiSmp is a parallel program that calculates an approximate value for
 * &pi; using a Monte Carlo technique. The program generates a number of random
 * points in the unit square (0,0) to (1,1) and counts how many of them lie
 * within a circle of radius 1 centered at the origin. The fraction of the
 * points within the circle is approximately &pi;/4.
 **/
public class PiSmp {


    /**
     * Main program.
     */
    public static void main(String[] args) throws Exception {
        long seed;
        long N;

        long count;

        // Validate command line arguments.
        if (args.length != 2)
            usage();

        seed = Long.parseLong(args[0]);
        N = Integer.parseInt(args[1]);


        long[] threadCounter = new long[Runtime.getRuntime().availableProcessors()];

        // Generate n random points in the unit square, count how many are in
        // the unit circle.

        // omp parallel
        {

            Random prng = new Random(seed);
            for (long i = 0; i < N / OMP4J_NUM_THREADS; ++i) {

                double x = prng.nextDouble();
                double y = prng.nextDouble();
                if (x * x + y * y <= 1.0)
                    threadCounter[OMP4J_THREAD_NUM] = threadCounter[OMP4J_THREAD_NUM] + 1;
            }

            // omp barrier
            {}

            for (int i = OMP4J_NUM_THREADS / 2; i > 0; i >>= 1) {
                if (OMP4J_THREAD_NUM < i)
                    threadCounter[OMP4J_THREAD_NUM] += threadCounter[OMP4J_THREAD_NUM + i];

                // omp barrier
                {}
            }

        }

        System.out.printf("pi = 4*%d/%d = %.9f%n", count, N, 4.0 * threadCounter[0] / N);

    }

    /**
     * Print a usage message and exit.
     */
    private static void usage() {
        System.err.println("Usage: java PiSeq <seed> <N>");
        System.err.println("<seed> = Random seed");
        System.err.println("<N> = Number of random points");
    }
}
