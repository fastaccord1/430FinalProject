package setup;

/**
 * Created by kreuter on 10/18/15.
 */
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Cryptography {
    private static String KEYFILE = "key.txt";
    private SecretKey secretKey;
    private Cipher aesCipher;

    public Cryptography() {
        String key = null;
        try {
            key = new String(Files.readAllBytes(Paths.get(KEYFILE)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] keyBytes = key.getBytes();
        secretKey = new SecretKeySpec(keyBytes, "AES");
        aesCipher = null;
        try {
            aesCipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    protected String decrypt(String encrypted){
        String decrypted = null;
        byte[] encryptedBytes = encrypted.getBytes();
        try {
            aesCipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] plainText = aesCipher.doFinal(encryptedBytes);
            return plainText.toString();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return decrypted;
    }

    protected String encrypt(String plainText){
        String encrypted = null;
        byte[] plainTextBytes = plainText.getBytes();
        try {
            aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = aesCipher.doFinal(plainTextBytes);
            return encryptedBytes.toString();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return encrypted;
    }
}
