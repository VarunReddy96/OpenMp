package edu.rit.cs.examples;

public class OMP_Parallel {
    public static void main(String[] args) {

        int foo = 5;
        // omp parallel
        {
            System.out.println("hello");
            System.out.println("world");
            System.out.println(foo);
        }

        System.out.println("last line");
    }
}
