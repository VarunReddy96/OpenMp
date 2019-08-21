import java.util.Random;

import static java.lang.Math.*;

import java.util.Arrays;


public class ZombieSmp {
    public static void main(String[] args) {
        long seed;
        double W, G, L, dt, eps;
        int steps, snap, N;
        double[] x, y, xnext, ynext, delta;

        if (args.length != 9)
            usage();

        seed = Long.parseLong(args[0]);
        N = Integer.parseInt(args[1]);
        W = Double.parseDouble(args[2]);
        G = Double.parseDouble(args[3]);
        L = Double.parseDouble(args[4]);
        dt = Double.parseDouble(args[5]);
        eps = Double.parseDouble(args[6]);
        steps = Integer.parseInt(args[7]);
        snap = Integer.parseInt(args[8]);

        x = new double[N];
        y = new double[N];
        xnext = new double[N];
        ynext = new double[N];
        Random prng = new Random(seed);
        for (int i = 0; i < N; ++i) {
            x[i] = prng.nextDouble() * W;
            y[i] = prng.nextDouble() * W;
        }

        int t = 0;
        snapshot(t, x, y, N);

        for (; ; ) {
            delta = new double[Runtime.getRuntime().availableProcessors()];

            // omp parallel
            {
                int start = OMP4J_THREAD_NUM * N / OMP4J_NUM_THREADS;
                int end = (OMP4J_THREAD_NUM + 1) * N / OMP4J_NUM_THREADS;

//                System.out.println("start = " + start + "; end = " + end);
//                System.out.println(end);

                for (int i = start; i < end; ++i) {
                    double vx = 0.0;
                    double vy = 0.0;
                    double dx, dy, d, v;

                    for (int j = 0; j < N; ++j) {
                        if (j == i) continue;
                        dx = x[j] - x[i];
                        dy = y[j] - y[i];
                        d = sqrt(dx * dx + dy * dy);
                        v = G * exp(-d / L) - exp(-d);
                        vx += v * dx / d;
                        vy += v * dy / d;
                    }

                    dx = vx * dt;
                    dy = vy * dt;
                    xnext[i] = x[i] + dx;
                    ynext[i] = y[i] + dy;

                    delta[OMP4J_THREAD_NUM] += abs(dx) + abs(dy);
                }


                // omp barrier
                {}

                for (int i = OMP4J_NUM_THREADS / 2; i > 0; i >>= 1) {
                    if (OMP4J_THREAD_NUM < i)
                        delta[OMP4J_THREAD_NUM] += delta[OMP4J_THREAD_NUM + i];

                    // omp barrier
                    {
                    }
                }
            }
            ++t;

            double[] tmp;
            tmp = x;
            x = xnext;
            xnext = tmp;
            tmp = y;
            y = ynext;
            ynext = tmp;


            if ((steps == 0 && delta[0] < eps) || (steps != 0 && t == steps))
                break;

            if (snap > 0 && t % snap == 0)
                snapshot(t, x, y, N);
        }

        if (snap == 0 || t % snap != 0)
            snapshot(t, x, y, N);


    }

    private static void snapshot(int t, double[] x, double[] y, int N) {
        System.out.println("t = " + t);
        System.out.println("------------------------------------------------------------");
        System.out.println("x = " + Arrays.toString(x));
        System.out.println("x = " + Arrays.toString(y));
        System.out.println("#############################################################");
        System.out.flush();
    }


    private static void usage() {
    }

}
