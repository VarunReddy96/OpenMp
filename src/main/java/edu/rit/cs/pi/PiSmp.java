import java.util.Random;
import java.util.Arrays;

public class PiSmp {



    public static void main(String[] args) throws Exception {
        long seed;
        long N;

        long count;

        seed = Long.parseLong(args[0]);
        N = Integer.parseInt(args[1]);

        long[] threadCounter = new long[Runtime.getRuntime().availableProcessors()];;

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

        System.out.print(4.0 * threadCounter[0] / N);
    }

}
