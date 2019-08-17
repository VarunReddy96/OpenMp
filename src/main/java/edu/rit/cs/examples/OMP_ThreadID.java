package edu.rit.cs.examples;

public class OMP_ThreadID {
    public static void main(String[] args) {
        // omp parallel threadNum(10)
        {
            System.out.println("Hello from Thread #" + OMP4J_THREAD_NUM + "/" + OMP4J_NUM_THREADS);
        }
    }
}
