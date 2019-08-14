import java.util.Random;
public class PiSeq {



    public static void main(String[] args) throws Exception {
        long seed;
        long N;
        Random prng;
        long count;

        seed = Long.parseLong(args[0]);
        N = Long.parseLong(args[1]);

        prng = new Random(seed);

        count = 0;
        for (long i = 0; i < N; ++i) {
            double x = prng.nextDouble();
            double y = prng.nextDouble();
            if (x * x + y * y <= 1.0)
                ++count;
        }

        System.out.printf("pi = 4*%d/%d = %.9f%n", count, N, 4.0 * count / N);
    }

}
