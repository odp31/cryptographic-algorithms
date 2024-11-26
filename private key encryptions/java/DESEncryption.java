// data encryption standard
// older and less secure
// block cipher with a 56-bit key


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DESEncryption
  {
    public static byte[] encrypt(byte[] plaintext, byte[] key) throws Exception
    {
      Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
      SecretKeySpec keySpec = new SecretKeySpec(key, "DES");
      cipher.init(Cipher.ENCRYPT_MODE, keySpec);
      byte[] ciphertext = cipher.doFinal(plaintext);
      return ciphertext;
    }
    public static byte[] decrypt(byte[] ciphertext, byte[] key) throws Exception
    {
      Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
      SecretKeySpec keySpec = new SecretKeySpec(key, "DES");
      cipher.init(Cipher.DECRYPT_MODE, keySpec);
      byte[] plaintext = cipher.doFinal(ciphertext);
      return plaintext;
    }
  }
