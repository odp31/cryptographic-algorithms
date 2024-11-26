import java.security.*;
import java.security.spec.DSAParameterSpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

public class DSAEncryption
  {
    public static void main(String[] args) throws Exception
    {
      // generate DSA key pair
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
      keyPairGenerator.initialize(1024);

      // key pair
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
      Signature verificationSignature = Signature.getInstance("SHA256withDSA");
      verificationSignature.initVerify(publicKey);

      verificationSignature.update(message.getBytes());
      boolean verified = verificationSignature.verify(signedData);
      System.out.println("signature verified" + verified);
    }
  }
