package edu.rit.cs;

public class OMP_Barrier {
        public static void main(String[] args) {
            // omp parallel
            {
                System.out.println("pre");

                // omp barrier
                {} /* stop here and wait for all threads before continue */

                System.out.println("post");
            }
        }
}
