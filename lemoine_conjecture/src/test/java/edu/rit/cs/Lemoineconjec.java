package edu.rit.cs;

/*
 * Lemoineconjec.java
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

public class Lemoineconjec {
    public int maxnum = 0;
    public int p = 0;
    public int q = 0;
    public boolean check = true;
    List<Integer> qlist = new ArrayList<>();

    /**
     * Default Constructor
     *
     */

    public Lemoineconjec() {
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
        Prime.Iterator odd_iterator = new Prime.Iterator();
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
                    //this.q = qlist.get(j);
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
        Lemoineconjec lemoine = new Lemoineconjec();
        if (!lemoine.checkarguments(Integer.parseInt(args[0]), Integer.parseInt(args[1]))) {
            return;
        }
        lemoine.Qlist(Integer.parseInt(args[1]));
        long starttime = System.currentTimeMillis();
        int lower = Integer.parseInt(args[0]);
        int upper = Integer.parseInt(args[1]);
        // omp parallel for
        for (int x = lower; x <= upper; x++) {
            if (x % 2 != 0) {
                boolean val = true;
                for (int j = (lemoine.qlist.size() - 1); val && j >= 0; j--) {
                    if (2 * lemoine.qlist.get(j) < x && (x - 2 * lemoine.qlist.get(j)) != 2 && Prime.isPrime((x - 2 * lemoine.qlist.get(j)))) {
                        // omp critical
                        {

                            if (lemoine.p < (x - 2 * lemoine.qlist.get(j))) {
                                lemoine.p = (x - 2 * lemoine.qlist.get(j));
                                //lemoine.q = lemoine.qlist.get(j);
                                lemoine.maxnum = x;
                            } else if (lemoine.p == (x - 2 * lemoine.qlist.get(j))) {
                                if (lemoine.maxnum < x) {
                                    lemoine.maxnum = x;
                                }
                            }
                            val = false;
                            //break;
                        }
                    }
                }
            }
        }
        long endtime = System.currentTimeMillis();
        System.out.println(lemoine.maxnum + " = " + lemoine.p + " + 2*" + ((lemoine.maxnum - lemoine.p) / 2));
        //System.out.println("Time elapsed : " + (endtime - starttime));
    }
}



