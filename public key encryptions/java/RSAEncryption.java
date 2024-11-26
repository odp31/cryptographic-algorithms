// generates a pair of keys, public and private
// encyrption: plaintext is encrypted using the public key
// decryption: ciphertext is decrypted using the private key
// use cases: secure communication, digital signatures, key exchange 

import java.security.*;
import javax.crypto.Cipher;

public class RSAEncryption
  {
    public static byte[] encrypt(byte[] plaintext, PublicKey publicKey) throws Exception
    {
      Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA256AndMGF1Padding");
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
      byte[] ciphertext = cipher.doFinal(plaintext);
      return ciphertext;
    }
    public static byte[] decrypt(byte[] ciphertext, PrivateKey privateKey) throws Exception
    {
      Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA256AndMGF1Padding");
      cipher.init(Cipher.DECRYPT_MODE, privateKey);
      byte[] plaintext = cipher.doFinal(ciphertext);
      return plaintext;
    }
  }
