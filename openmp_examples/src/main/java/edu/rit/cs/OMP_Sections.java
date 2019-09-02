package edu.rit.cs.examples;

public class OMP_Sections {
    public static void main(String[] args) {
        // omp sections
        {
            // omp section
            {
                System.out.println("task1 start");
                System.out.println("task1 stop");
            }

            // omp section
            {
                System.out.println("task2 start");
                System.out.println("task2 stop");
            }

            // omp section
            {
                System.out.println("task3 start");
                System.out.println("task3 stop");
            }
        }
    }
}
