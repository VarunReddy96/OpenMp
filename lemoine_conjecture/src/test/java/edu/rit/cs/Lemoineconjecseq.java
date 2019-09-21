package edu.rit.cs;

/*
 * Lemoineconjecseq.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This program verifies Lemoine's Conjecture for all odd integers in a given range taken from command
 * line arguments and prints out n, p, and q values such that p is the largest among the integers n examined.
 * If more that one integer yielded the same maximum p value, the program must print the one with the largest n value.
 *
 * @author Bujala Varun Reddy(vb6310)
 */

public class Lemoineconjecseq {
    public int maxnum = 0;
    public int p = 0;
    public int q = 0;
    public int counting = 0;
    int previous = 2;
    Prime.Iterator odd_iterator = new Prime.Iterator();
    List<Integer> qlist = new ArrayList<>();

    /**
     * Default Constructor
     *
     */

    public Lemoineconjecseq() {
    }

    /**
     * This method makes sure that the arguments inputed are valid and returs false if not valid.
     *
     * @param lower: The lower bound of the range to verify lemoine conjecture.
     * @param upper: The upper bound of the range to verify lemoine conjecture.
     *
     * @return boolean Returns true only if both the input arguments are valid.
     */

    public boolean checkarguments(int lower, int upper) {
        boolean check = false;
        if (lower % 2 != 0 && lower > 5) {
            check = true;
            //throw new Exception();
        }
        if (check && upper % 2 != 0 && upper > lower){
            return true;
        }
        System.out.println("Enter valid Integers");
        return false;
    }

    /**
     * This method creates a list of q values and verifies the lemoine conjecture for the num inputed.
     *
     * @param num: The num to verify lemoine conjecture.
     */

    public void Qlist(int num){

        int i;
        while (previous < (num + 1) / 2) {

            qlist.add(previous);
            i = odd_iterator.next();
            previous = i;

        }
        checktheorm(qlist,num);

    }


    /**
     * This method is a helper method to verify the lemoine conjecture for the num inputed.
     *
     * @param num:   The num to verify lemoine conjecture.
     * @param qlist: the list of suitable q values.
     */

    public void checktheorm(List<Integer> qlist, int num) {
        for (int j = (qlist.size() - 1); j >= 0; j--) { //the list is reversed so as to find the value of p quickly
            if ((num - 2 * qlist.get(j)) != 2 && Prime.isPrime((num - 2 * qlist.get(j)))) { //checking if the p value is not even and is a prime number.
                if (this.p < (num - 2 * qlist.get(j))) {
                    this.p = (num - 2 * qlist.get(j));
                    this.q = qlist.get(j);
                    this.maxnum = num;
                } else if (this.p == (num - 2 * qlist.get(j))) {
                    if (this.maxnum < num) {
                        this.maxnum = num;
                    }
                }

                break;
            }
        }
    }

    /**
     * This is the main method.
     *
     * @param args
     */

    public static void main(String[] args) {
        // takes in 2 command line arguments
        Lemoineconjecseq lemoine = new Lemoineconjecseq();
        if(!lemoine.checkarguments(Integer.parseInt(args[0]), Integer.parseInt(args[1]))){
            return;
        }
        int x = Integer.parseInt(args[0]);
        lemoine.Qlist(x);
        long starttime = System.currentTimeMillis();
        while (x <= Integer.parseInt(args[1])) {
            if (x % 2 != 0) {
                lemoine.Qlist(x);
            }
            x++;
        }
        long endtime = System.currentTimeMillis();
        System.out.println(lemoine.maxnum + " = " + lemoine.p + " + 2 * " + ((lemoine.maxnum - lemoine.p) / 2));
        System.out.println("Time elapsed : " + (endtime - starttime));
    }
}




