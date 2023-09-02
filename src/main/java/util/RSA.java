package util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;


public class RSA {

    public static KeyPair genRSAKeys() throws NoSuchAlgorithmException {

        // generate Keys
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);    //generated keysize = 2048 bits;
        KeyPair pair = generator.generateKeyPair();

        return pair;
    }

     public static byte[] applyRSAEncryptingData (String message, PublicKey publicKey) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {


         //now encrypting data
         Cipher encryptCipher = Cipher.getInstance("RSA");
         encryptCipher.init(Cipher.ENCRYPT_MODE,publicKey);

         byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
         byte[] encryptedMessageBytes = encryptCipher.doFinal(messageBytes); // <----------
         //String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);
         // System.out.println(encodedMessage);

         return encryptedMessageBytes;

     }

    public static String applyRSADecryptingData( byte[] encryptedMessageBytes, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        Cipher decryptCipher = Cipher.getInstance("RSA");

        decryptCipher.init(Cipher.DECRYPT_MODE,privateKey);
        byte [] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
        String decryptedMessage = new String(decryptedMessageBytes,StandardCharsets.UTF_8);

         return decryptedMessage;
    }
}
