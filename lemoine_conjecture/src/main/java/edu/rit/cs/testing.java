package edu.rit.cs;
/*
 * testing.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */


import scala.Int;

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

public class testing {
    public int maxnum = 0;
    public int p = 0;
    public int q = 0;
    //public int counting = 0;
    List<Integer> qlist = new ArrayList<>();


    public testing() {
    } //default constructor


    public boolean checkarguments(int lower, int upper) {
        boolean check = false;
        if (lower % 2 != 0 && lower > 5) {
            check = true;
        }
        if (check && upper % 2 != 0 && upper > lower) {
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

    public void Qlist(int num) {
        edu.rit.cs.Prime.Iterator odd_iterator = new edu.rit.cs.Prime.Iterator();
        int i = 2;
        while (i < (num + 1) / 2) {

            qlist.add(i);
            i = odd_iterator.next();

        }
    }


    /**
     * This method is a helper method to verify the lemoine conjecture for the num inputed.
     *
     * @param num:   The num to verify lemoine conjecture.
     * @param qlist: the list of suitable q values.
     */

    public void checktheorm(List<Integer> qlist, int num) {
        for (int j = (qlist.size() - 1); j >= 0; j--) { //the list is reversed so as to find the value of p quickly
            if (2 * qlist.get(j) < num && (num - 2 * qlist.get(j)) != 2 && Prime.isPrime((num - 2 * qlist.get(j)))) { //checking if the p value is not even and is a prime number.


                if (this.p < (num - 2 * qlist.get(j))) {
                    this.p = (num - 2 * qlist.get(j));
                    this.q = qlist.get(j);
                    this.maxnum = num;
                } else if (this.p == (num - 2 * qlist.get(j))) {
                    if (this.maxnum < num) {
                        //this.q = qlist.get(j);
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
        testing t = new testing();
        if (!t.checkarguments(Integer.parseInt(args[0]), Integer.parseInt(args[1]))) {
            return;
        }
        t.Qlist(Integer.parseInt(args[1]));
        long starttime = System.currentTimeMillis();

        // omp parallel for
        for (int x = Integer.parseInt(args[0]); x <= Integer.parseInt(args[1]); x++) {
            if (x % 2 != 0) {
                for (int j = (t.qlist.size() - 1); j >= 0; j--) {
                    if (2 * t.qlist.get(j) < x && (x - 2 * t.qlist.get(j)) != 2 && Prime.isPrime((x - 2 * t.qlist.get(j)))) {
                        // omp critical
                        {
                            if (t.p < (x - 2 * t.qlist.get(j))) {
                                t.p = (x - 2 * t.qlist.get(j));
                                t.q = t.qlist.get(j);
                                t.maxnum = x;
                            } else if (t.p == (x - 2 * t.qlist.get(j))) {
                                if (t.maxnum < x) {
                                    t.maxnum = x;
                                }
                            }

                            break;
                        }

                    }
                }
            }
        }
        long endtime = System.currentTimeMillis();
        System.out.println("The value to be printed in main is " + t.maxnum + " = " + t.p + " + 2*" + ((t.maxnum - t.p) / 2));
        System.out.println("Time elapsed : " + (endtime - starttime));
    }
}




