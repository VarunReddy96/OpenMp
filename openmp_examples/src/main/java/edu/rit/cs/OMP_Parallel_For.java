package edu.rit.cs;

public class OMP_Parallel_For {
        public static void main(String[] args) {
            int dimension = 5000;

            MyTimer myTimer = new MyTimer("SEQ_For");
            myTimer.start_timer();
            int a=0;
            for (int i = 0; i < dimension; i += 1) {
                a=i*2;
            }

            myTimer.stop_timer();
            myTimer.print_elapsed_time();





            MyTimer myTimerOMP = new MyTimer("OMP_Parallel_For");
            myTimerOMP.start_timer();

            int b=0;

            // omp parallel for private(b)
            for (int i = 0; i < dimension; i += 1) {
                b=i*2;
            }

            myTimerOMP.stop_timer();
            myTimerOMP.print_elapsed_time();


        }
}
