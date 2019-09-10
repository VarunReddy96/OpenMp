/*
 * lemoineconjec.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */


import java.util.ArrayList;
import java.util.Collections;

/**
 * This program verifies Lemoine's Conjecture for all odd integers in a given range and prints out
 * n, p, and q values such that p is the largest among the integers n examined.
 *
 * @author Bujala Varun Reddy(vb6310)
 */

public class lemoineconjec {
    public int maxnum = 0;
    public int p = 0;
    public int q = 0;
    public int counting = 0;

    public lemoineconjec(){} //default constructor

    /**
     * This method creates a list of q values and verifies the lemoine conjecture for the num inputed.
     *
     * @param num: The num to verify lemoine conjecture.
     *
     */

    public void findQ(int num) {
        Prime.Iterator odd = new Prime.Iterator();
        int i = 2;
        ArrayList<Integer> qlist = new ArrayList<>();
        while (i < num / 2) {
            //System.out.println("Adding i:");
            qlist.add(i);
            i = odd.next();
        }
        //Collections.reverse(qlist); // the list is reversed so as to find the value of p quickly
        this.checktheorm(qlist, num);

    }

    /**
     * This method is a helper method to verify the lemoine conjecture for the num inputed.
     *
     * @param num: The num to verify lemoine conjecture.
     * @param qlist: the list of suitable q values.
     *
     */

    public void checktheorm(ArrayList<Integer> qlist, int num) {
        //System.out.println("Entering checktheorm with x: "+ x);
        for(int j = (qlist.size()-1);j>=0;j--){
            //System.out.println("the value of j is: "+j + " and value is :"+qlist.get(j)+"counting: "+ this.counting);
            if((num-2*qlist.get(j))!=2 && Prime.isPrime((num-2*qlist.get(j)))){ //checking if the p value is not even and is a prime number.
                if (this.p < (num-2*qlist.get(j))) {
                    this.p = (num-2*qlist.get(j));
                    this.q = qlist.get(j);
                    this.maxnum = num;
                    //System.out.println("IN if");
                    //System.out.println(x + "= " + (x-2*j) + "+ 2 * " + j);
                } else if (this.p == (num-2*qlist.get(j))) {
                    if (this.q < qlist.get(j)) {
                        this.q = qlist.get(j);
                        this.maxnum = num;
                        //System.out.println("IN else if");
                        //System.out.println(x + "= " + (x-2*j) + "+ 2 * " + j);
                    }
                }
                //System.out.println(num + "= " + (num-2*qlist.get(j)) + "+ 2 * " + qlist.get(j));
                //this.counting++;

                break;
            }
            this.counting++;
            //System.out.println("the value after if--------of j is: "+j + " counting: "+ this.counting);
        }
    }

    /**
     * This is the main method.
     *
     * @param args
     */

    public static void main(String[] args) {
        // takes in 2 command line arguments
        try {
            int x = Integer.parseInt(args[0]);
            lemoineconjec t = new lemoineconjec();
            long starttime = System.currentTimeMillis();
            while (x <= Integer.parseInt(args[1])) {
                if (x % 2 != 0) {
                    t.findQ(x);
                }
                x++;
            }
            long endtime = System.currentTimeMillis();
            System.out.println("The value to be printed in main is " + t.maxnum + " = " + t.p + " + 2 * " + ((t.maxnum - t.p) / 2));
            System.out.println("The operations are: " + t.counting);
            System.out.println("Time elapsed : "+(endtime-starttime));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
