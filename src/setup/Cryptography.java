package setup;

/**
 * Created by kreuter on 10/18/15.
 */
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Random;


public class Cryptography {
    private final String KEYFILE = "setup/key.txt";
    private final int SALT_LENGTH = 16;
    private final String KEYGEN_SPEC = "PBKDF2WithHmacSHA1";
    private final int INTERATIONS = 32768;
    private final int AUTH_KEY_LENGTH = 24;
    private SecretKey secretKey;
    private Cipher aesCipher;
    private byte[] salt;

    /**
     *  Default constructor that initializes variables to be used
     */
    public Cryptography() {

        aesCipher = null;
        secretKey = null;
        salt = null;
        try {
            aesCipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        generateSalt();
        keygen();
    }

    /**
     *  This method creates the key to be used for AES
     */
    private void keygen(){
        String baseKey = null;
        try {
            baseKey = new String(Files.readAllBytes(Paths.get(KEYFILE)));
            System.out.println(baseKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
        char[] keyChars = baseKey.toCharArray();

        SecretKeyFactory factory = null;
        try {
            factory = SecretKeyFactory.getInstance(KEYGEN_SPEC);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        KeySpec spec = new PBEKeySpec(keyChars, salt, INTERATIONS, 256 + AUTH_KEY_LENGTH * 8);

        SecretKey tmp = null;
        try {
            tmp = factory.generateSecret(spec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        byte[] fullKey = tmp.getEncoded();

        SecretKey encKey = new SecretKeySpec(Arrays.copyOfRange(fullKey, AUTH_KEY_LENGTH, fullKey.length), "AES");

        this.secretKey = encKey;


    }

    /**
     *
     * @param encrypted encrypted text to be decrypted
     * @return String of decrypted data
     */
    public String decrypt(String encrypted){
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
        return null;
    }

    /**
     *
     * @param plainText plain text input of password
     * @return String of encrypted data
     */
    public String encrypt(String plainText){
        byte[] plainTextBytes = plainText.getBytes();
        try {
            aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = aesCipher.doFinal(plainTextBytes);
            return new String(encryptedBytes);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void generateSalt(){
        Random r = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        r.nextBytes(salt);
        //return salt;
        this.salt = salt;

    }

    private void generateSalt(String salt){
        this.salt = salt.getBytes();
    }
}
