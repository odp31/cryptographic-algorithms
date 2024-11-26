// elliptic curve cryptography
// based on mathematics of elliptic curves
// offers similar security to RSA but with smaller key sizes
// key length - 256 or 384 bits 
// use cases- secure communication, digital signatures, key exchange 


import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.*;
import javax.crypto.Cipher;

public class ECCEncryption
  {
    public static byte[] encrypt(byte[] plaintext, PublicKey publicKey) throws Exception
    {
      Security.addProvider(new BouncyCastleProvider());

      Cipher cipher = Cipher.getInsatnce("ECIES", "BC");
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);

      byte[] ciphertext = cipher.doFinal(plaintext);
      return ciphertext;
    }
  }
