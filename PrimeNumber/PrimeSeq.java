/**
 * Class PrimeSeq is a sequential program that tests numbers for primality. It
 * prints the numbers on the command line that are prime.
 **/
class PrimeSeq {
    /**
     * Test the given number for primality.
     *
     * @param x Number &ge; 3.
     * @return True if x is prime, false otherwise.
     */
    private static boolean isPrime(long x) {
        if (x % 2 == 0) return false;
        long p = 3;
        long psqr = p * p;
        while (psqr <= x) {
            if (x % p == 0) return false;
            p += 2;
            psqr = p * p;
        }
        return true;
    }

    /**
     * Main program.
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter atleast one number to check");
        }
        for (int i = 0; i < args.length; i++) {
            if (isPrime(Long.parseLong(args[i])))
                System.out.printf("%s%n", args[i]);
        }
    }
}
