*) I have submitted 2 .java files(excluding the Prime.java)as part of Programming project 1. To compile sequentially
    javac -d classes Lemoineconjec.java Prime.java
    java -cp classes edu.rit.cs.Lemoineconjec 1000001 1999999
   To compile in parallel using omp4j
     java -jar <path-to-JAR> -d classes1 Lemoineconjec.java Prime.java
     java -cp classes1 edu.rit.cs.Lemoineconjec 1000001 1999999

*) Lemoineconjec is the main class for this project. If this .java file is run using javac command it executes sequentially 
   and if omp4j.jar is used it performs the computation in parallel.

*) The second file I have added is Lemoineconjecseq. This .java file doesnot contain any omp4j.jar directives and will only 
   execute sequentially. I have added this since from my analysis I found that this is the most efficient way to perform 
   sequential compuatation for Lemoine conjecture.

*) In the Lemoineconjecseq I have kept track of Prime iterator and a list a q values to acheive this efficiency.

*) This implementation is not recommended to convert this code to a parallel version using omp4j directives since the list of q's
   would become a shared variable for all the threads and performing update operations synchronously on the list would make the code
   run almost in a sequential way. Also if this list is made private it would lead to high space complexity.

*) Hence in Lemoineconjec I have created a fixed q list which is shared by all threads.

*) The speedup = (Time taken to run sequentially)/(Time taken to run in parallel)
   Time taken to run sequentially by Lemoineconjec = 20.154 sec (averaged over 8 computations on kraken cluster)
   Time taken Lemoineconjec to run in parallel     = 4.814 sec  (averaged over 8 computations on Kraken cluster)

*) Hence the speed up acheived = 21.154/4.814 = 4.394. This is an exampe of strong scaling since given the problem size the more 
   number of threads running the faster we will see the result.
 
*) Time taken to run sequentially by Lemoineconjecseq = 0.7 sec(averaged over 8 computations on kraken cluster)


		  