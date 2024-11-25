import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class RSAEncryption
  {
    public static void main(String[] args) throws Exception)
    {
      // generate a 2048 bit RSA key pair
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      keyPairGenerator.initialize(2048);
      KeyPair keyPair = keyPairGenerator.generateKeyPair();

      // get public and private keys
      PublicKey publicKey = keyPair.getPublic();
      PrivateKey privateKey = keyPair.getPrivate();

      // encrypt a message using public key
      String message = "Hello, World";
      Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA256AndMGF1Padding");
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
      byte[] cipherText = cipher.doFinal(message.getBytes());
      // decrypt message using private key
      cipher.init(Cipher.DECRYPT_MODE, privateKey);
      byte[] decryptedText = cipher.doFinal(cipherText);

      System.out.println("encrypted message: " + new String(cipherText));
      System.out.println("decrypted message: " + new String(decryptedText));

    }
  }
