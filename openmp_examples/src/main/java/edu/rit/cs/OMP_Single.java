package edu.rit.cs.examples;

public class OMP_Single {
    public static void main(String[] args) {
        // omp parallel
        {
            System.out.println("By all 1");

            // omp single
            {
                System.out.println("only once");
            }

            System.out.println("By all 2");
        }
    }
}