package edu.rit.cs;

import java.lang.reflect.Array;

public class OMP_ArrayOperation {

    public static void init(int size, int[] a, int initVal) {
        // omp parallel for
        for(int i=0; i<size; i++) {
            a[i] = initVal;
        }
    }

    public static int[] ArrayAddition(int size, int[] A, int[] B) {
        int[] C = new int[size];

        for(int i=0; i<size; i++) {
                C[i] = A[i] + B[i];
        }
        return C;
    }

    public static int[] ArrayAdditionOMP(int size, int[] A, int[] B) {
        int[] C = new int[size];

        // omp parallel for threadNum(4)
        for(int i=0; i<size; i++) {
            C[i] = A[i] + B[i];
        }
        return C;
    }


    public static void main(String[] args) {
        int size=1000;

        int[] A = new int[size];
        int[] B = new int[size];

        MyTimer myTimer = new MyTimer("ArrayAddition");
        myTimer.start_timer();
        int[] C = ArrayAddition(size, A, B);
        myTimer.stop_timer();
        myTimer.print_elapsed_time();


        MyTimer myTimerOMP = new MyTimer("ArrayAdditionOMP");
        myTimerOMP.start_timer();
        int[] C2 = ArrayAdditionOMP(size, A, B);
        myTimerOMP.stop_timer();
        myTimerOMP.print_elapsed_time();

    }

}
