// rivest cipher 4
// encrypts individual bytes of data
// variable key size 
// has been deemed insecure

public class RC4
  {
    public static byte[] encrypt(byte[] plaintext, byte[] key)
    {
      byte[] S = new byte[256];
      byte[] T = new byte[256];

      // initialize S and T arrays
      for(int i = 0; i < 256; i++) {
        S[i] = (byte) i;
        T[i] = key[i % key.length];
      }
      // KSA: key scheduling algorithm
      int j = 0; 
      for(int i = 0; i < 256; i++)
        {
          j = (j + S[i] + T[i]) % 256;
          byte temp = S[i];
          S[i] = S[j];
          S[j] = temp;
        }
      // PRGA: psuedo-random generation algorithm
      int i = 0;
      j = 0; 
      byte[] ciphertext = new byte[plaintext.length];
      for(int k = 0; k < plaintext.length; k++)
        {
          i = (i + 1) % 256;
          j = (j + S[i]) % 256;
          byte temp = S[i];
          S[i] = S[j];
          S[j] = temp;
          int K = S[(S[i] + S[j]) % 256];
          ciphertext[k] = (byte) (plaintext[k] ^ K);
        }
      return ciphertext;
    }
  }


