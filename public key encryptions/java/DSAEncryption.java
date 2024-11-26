// Digital Signature Algorithm
// primarily used for digital signatures 
// generates a pair of keys; public and private
// signing: message is hashed, hash is signed using the private key
// verification: signature is verified using public key 

// public key used for digital signatures

import java.security.*;
import java.security.spec.DSAParameterSpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

public class DSAExample
  {
    public static void main(String[] args) throws Exception
    {
      // generate DSA key pair
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
      keyPairGenerator.initialize(1024);

      // key size
      KeyPair keyPair = keyPairGenerator.generateKeyPair();
      PrivateKey privateKey = keyPair.getPrivate();
      PublicKey publicKey = keyPair.getPublic();

      // sign a message
      String message = "Hello, world!";
      Signature signature = Signature.getInstance("SHA256withDSA");
      signature.initSign(privateKey);
      signature.update(message.getBytes());

      byte[] signedData = signature.sign();

      // verify signature 
      Signature verificationSignatrue = Signature.getInstance("SHA256withDSA");
      verificationSignature.initVerify(publicKey);

      verificationSignature.update(message.getBytes());
      boolean verified = verificationSignature.verify(signedData);
      System.out.println("signature verified: " + verified);
    }
  }
