import java.util.HashMap;

class PrimeSeq {
    private static boolean isPrime(long x)
    {
        if (x % 2 == 0) return false;
        long p = 3;
        long psqr = p*p;
        while (psqr <= x)
        {
            if (x % p == 0) return false;
            p += 2;
            psqr = p*p;
        }
        return true;
    }
    public static void main(String[] args) {

        for (int i = 0; i < args.length; i ++) {
            if(isPrime(Long.parseLong (args[i])))
                System.out.printf ("%s%n", args[i]);
        }
    }
}
