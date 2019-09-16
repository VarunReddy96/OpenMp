package edu.rit.cs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class test implements Runnable{
    public void run(){
        System.out.println(Thread.currentThread());
    }

    public static void main(String[] args) {
        // omp parallel threadNum(4)
        {
            System.out.print("hello ");
        }
    }
}

class task{
    public static void main(String[] args) {
        ExecutorService e = Executors.newFixedThreadPool(5);
        e.execute(new test());
        e.execute(new test());
        e.execute(new test());
        e.shutdown();

    }
}