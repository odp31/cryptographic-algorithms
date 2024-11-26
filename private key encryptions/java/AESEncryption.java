// Advanced Encryption Standard
// widely used and secure
// block cipher: encrypts fixed-size blocks of data
// key sizes; 128, 192, or 256 bits 
// symmetric (private)


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryption
  {
    public static byte[] encrypt(byte[] plaintext, byte[] key) throws Exception
    {
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
      cipher.init(Cipher.ENCRYPT_MODE, keySpec);

      byte[] ciphertext = cipher.doFinal(plaintext);
      return ciphertext;
    }
    public static byte[] decrypt(byte[] ciphertext, byte[] key) throws Exception
    {
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
      cipher.init(Cipher.DECRYPT_MODE, keySpec);

      byte[] plaintext = cipher.doFinal(ciphertext);
      return plaintext;
    }
  }
