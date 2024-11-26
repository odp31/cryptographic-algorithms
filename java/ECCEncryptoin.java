// elliptic curve cryptography (ECC) 
// public key cryptography algorithm that offers efficient and secure encryptoin 
// often used for digital signatures and key exchange

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.*;
import javax.crypto.Cipher;

public class ECCEncryption
  {
    public static byte[] encrypt(byte[] plaintext, PublicKey publicKey) throws Exception
    {
      Security.addProvider(new BouncyCastleProvider());
      Cipher cipher = Cipher.getInstance("ECIES", "BC");
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
      byte[] ciphertext = cipher.doFinal(plaintext);
      return ciphertext;
    }
  }
