import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESEncryption
  {
    public static byte[] encrypt(String plainText, String key) throws Exception {
      Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
      SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
      DESKeySpec dks = new DESKeySpec(key.getBytes());
      javax.crypto.SecretKey secretKey = factory.generateSecret(dks);
      cipher.init(Cipher.ENCRYPT_MODE, secretKey);
      byte[] encryptedText = cipher.doFinal(plainText.getBytes());
      return encryptedText;
    }

    public static String decrypt(byte[] encryptedText, String key) throws Exception
    {
      Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
      SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
      DESKeySpec dks = new DESKeySpec(key.getBytes());
      javax.crypto.SecretKey secretKey = factory.generateSecret(dks);
      cipher.init(Cipher.DECRYPT_MODE, secretKey);
      byte[] decryptedText = cipher.doFinal(encryptedText);
      return new String(decryptedText);
    }
    public static void main(String[] args) throws Exception
    {
      String secretKey = "yourSecretKey";
      String plainText = "Hello, world!";

      byte[] encryptedText = encrypt(plainText, secretKey);
      System.out.println("encrypted text: " + new String(encryptedText));

      String decryptedText = decrypt(encryptedText, secretKey);
      System.out.println("Decrypted text: " + decryptedText);
    }
  }
