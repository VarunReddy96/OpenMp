# **Prime number checker**

In this exercise we will write a sequential program and a parallel program that tests numbers for primality. It prints the numbers on the command line that are prime.

In the following code we will pass the numbers that we want to check for primality through the command line.

Here are some numbers that you can use (All of them are prime):

100000000000000003, 100000000000000013, 100000000000000019, 
100000000000000021, 100000000000000049, 100000000000000081,
100000000000000099, 100000000000000141, 100000000000000181,
100000000000000337, 100000000000000339, 100000000000000369

**1. Sequential program**
  * Running command  
    1. javac PrimeSeq.java
    2. java PrimeSeq [Prime_numbers_you_want_to_check_primality_of]

  * Runtime  
    ![alt text](SeqRunTime.png "Sequential program runtime")

**2. Parallel program**
  * Running command  
    1. Java -jar [openmp_jar_path] PrimeSmp.java
    2. java PrimeSmp [Prime_numbers_you_want_to_check_primality_of]

  * Runtime  
    ![alt text](smpRunTime.png "Parallel program runtime")
