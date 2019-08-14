import edu.rit.crypto.SHA256;
import edu.rit.pj2.Task;
import edu.rit.util.Hex;
import edu.rit.util.Packing;

public class MineCoinSeq extends Task {

// Program shared variables.

   // Command line arguments.
   byte[] coinId;
   int N;

   // Mask for leading zeroes.
   long mask;

   // For computing hash digests.
   byte[] coinIdPlusNonce;
   SHA256 sha256;
   byte[] digest;

   public static void main(String[] args) throws Exception {
      // Validate command line arguments.
      if (args.length != 2)
        usage();
      coinId = Hex.toByteArray (args[0]);
      N = Integer.parseInt (args[1]);
      if (1 > N || N > 63) usage();

      // Set up mask for leading zeroes.
      mask = ~((1L << (64 - N)) - 1L);

      // Set up for computing hash digests.
      coinIdPlusNonce = new byte [coinId.length + 8];
      System.arraycopy (coinId, 0, coinIdPlusNonce, 0, coinId.length);
      sha256 = new SHA256();
      digest = new byte [sha256.digestSize()];

      // Try all nonces until the digest has N leading zero bits.
      for (long nonce = 0L; nonce <= 0x7FFFFFFFFFFFFFFFL; ++ nonce) {
         // Test nonce.
         Packing.unpackLongBigEndian (nonce, coinIdPlusNonce, coinId.length);
         sha256.hash (coinIdPlusNonce);
         sha256.digest (digest);
         sha256.hash (digest);
         sha256.digest (digest);
         if ((Packing.packLongBigEndian (digest, 0) & mask) == 0L)
            {
            // Print results.
            System.out.printf ("Coin ID = %s%n", Hex.toString (coinId));
            System.out.printf ("Nonce   = %s%n", Hex.toString (nonce));
            System.out.printf ("Digest  = %s%n", Hex.toString (digest));
            break;
            }
         }
      }
   }
