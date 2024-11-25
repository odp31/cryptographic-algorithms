import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import java.security.SecureRandom;
import java.util.Base64;

public class AESEncryption
  {
    public static byte[] encrypt(String plainText, byte[] key) throws Exception
    {
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      byte[] iv = new byte[cipher.getBlockSize()];
      SecureRandom.getInstanceStrong().nextBytes(iv);

      IvParameterSpec ivSpec = new IvParameterSpec(iv);
      SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpect);
      byte[] encrypted = cipher.doFinal(plainText.getBytes());
      byte[] cipherText = Base64.getEncoder().encode(iv);
      cipherText = Base64.getEncoder().encode(encrypted);
      return cipherText;
    }

    public static String decrypt(byte[] cipherText, byte[] key) throws Exception
    {
      byte[] iv = Base64.getDecoder().decode(cipherText);
      cipherText = Base64.getDecoder().decode(cipherText, iv.length);
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
      IvParameterSpec ivSpec = new IvParameterSpec(iv);
      cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
      byte[] plainText = cipher.doFinal(cipherText);
      return new String(plainText);
    }

    public static void main(String[] args) throws Exception
    {
      String secretKey = "yourSecretKey";
      String plainText = "Hello, world!";

      byte[] encryptedText = encrypt(plainText, secretKey.getBytes());
      System.out.println("Encrypted text: " + new String(encryptedText));

      String decryptedText = decrypt(encryptedText, secretKey.getBytes());
      System.out.println("decrypted text: " + decryptedText);
    }
  }

      
