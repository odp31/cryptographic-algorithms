// java cryptography architecture (JCA)
// provides a robust framework for cryptographic operations through the JCA
// below is a basic example of RSA public-key encryption using the JCA

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import java.util.Base64;

public class PublicKeyEncryption
  {
    public static void main(String[] args) throws Exception {
      // generate a key pair (RSA)
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      keyPairGenerator.initialize(2048);
      KeyPair keyPair = keyPairGenerator.generateKeyPair();

      // get public and private keys 
      PublicKey publicKey = keyPair.getPublic();
      PrivateKey privateKey = keyPair.getPrivate();

      // encrypt a message
      String message = "Hello, world!";
      Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA256AndMGF1Padding");
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
      byte[] cipherText = cipher.doFinal(message.getBytes());
      String encryptedMessage = Base64.getEncoder().encodeToString(cipherText);

      // decrypt the message
      cipher.init(Cipher.DECRYPT_MOD, privateKey);
      byte[] decryptedText = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
      String decryptedMessage = new String(decryptedText);

      System.out.println("encrypted message: " + encryptedMessage);
      System.out.println("Decrypted message: " + decryptedMessage);
    }
  }
