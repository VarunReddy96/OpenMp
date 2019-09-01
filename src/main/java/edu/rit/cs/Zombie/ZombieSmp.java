import java.util.Random;

import static java.lang.Math.*;

import java.util.Arrays;

/**
 * Class ZombieSmp is an SMP parallel program to compute the motion of a group
 * of zombies. This is a so-called "N-bodies" problem.
 */
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

        // Set up N bodies' initial (x,y) coordinates at random in a WxW square
        // region.
        x = new double[N];
        y = new double[N];
        xnext = new double[N];
        ynext = new double[N];
        Random prng = new Random(seed);
        for (int i = 0; i < N; ++i) {
            x[i] = prng.nextDouble() * W;
            y[i] = prng.nextDouble() * W;
        }

        // Snapshot all bodies' initial positions.
        int t = 0;
        snapshot(t, x, y, N);

        // Do time steps.
        for (; ; ) {
            delta = new double[Runtime.getRuntime().availableProcessors()];

            // Do each body i.

            // omp parallel
            {
                int start = OMP4J_THREAD_NUM * N / OMP4J_NUM_THREADS;
                int end = (OMP4J_THREAD_NUM + 1) * N / OMP4J_NUM_THREADS;

                // Accumulate velocity due to every other body j.
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

                    // Move body i in the direction of its velocity.
                    dx = vx * dt;
                    dy = vy * dt;
                    xnext[i] = x[i] + dx;
                    ynext[i] = y[i] + dy;

                    // Accumulate position delta.
                    delta[OMP4J_THREAD_NUM] += abs(dx) + abs(dy);
                }


                // omp barrier
                {}

                // Reduction procedure for each thread's delta
                for (int i = OMP4J_NUM_THREADS / 2; i > 0; i >>= 1) {
                    if (OMP4J_THREAD_NUM < i)
                        delta[OMP4J_THREAD_NUM] += delta[OMP4J_THREAD_NUM + i];

                    // omp barrier
                    {}
                }
            }

            // Advance to next time step.
            ++t;

            // Update positions.
            double[] tmp;
            tmp = x;
            x = xnext;
            xnext = tmp;
            tmp = y;
            y = ynext;
            ynext = tmp;

            // Stop when position delta is less than convergence threshold or
            // when the specified number of time steps have occurred.
            if ((steps == 0 && delta[0] < eps) || (steps != 0 && t == steps))
                break;

            // Snapshot all bodies' positions every <snap> time steps.
            if (snap > 0 && t % snap == 0)
                snapshot(t, x, y, N);
        }

        // Snapshot all bodies' final positions.
        if (snap == 0 || t % snap != 0)
            snapshot(t, x, y, N);


    }

    // Snapshot all bodies' positions.
    private static void snapshot(int t, double[] x, double[] y, int N) {
        for (int i = 0; i < N; ++ i)
            System.out.printf ("%d\t%d\t%g\t%g%n", t, i, x[i], y[i]);
        System.out.flush();
    }

    // Print a usage message and exit.
    private static void usage() {
        System.err.println ("Usage: java ZombieSmp <seed> <N> <W> <G> <L> <dt> <eps> <steps> <snap>");
        System.err.println ("<seed> = Random seed");
        System.err.println ("<N> = Number of bodies");
        System.err.println ("<W> = Region size");
        System.err.println ("<G> = Attraction factor");
        System.err.println ("<L> = Attraction length scale");
        System.err.println ("<dt> = Time step size");
        System.err.println ("<eps> = Convergence threshold");
        System.err.println ("<steps> = Number of time steps (0 = until convergence)");
        System.err.println ("<snap> = Snapshot interval (0 = none)");
    }

}
