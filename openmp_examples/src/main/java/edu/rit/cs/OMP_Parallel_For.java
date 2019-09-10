package edu.rit.cs;

public class OMP_Parallel_For {
        public static void main(String[] args) {

            // omp parallel for
            for (int i = 0; i < 50; i += 2) {
                System.out.print(" " + i);
            }
            System.out.println();
        }
}
