package setup;

/**
 * Created by kreuter on 10/18/15.
 */
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class Cryptography {
    private static String INITIALKEY = "hrgYKTyUZP7OHhKAv04d";
    private SecretKey secretKey;

    public Cryptography() {
        byte[] keyBytes = INITIALKEY.getBytes();
        secretKey = new SecretKeySpec(keyBytes, "AES");
    }
}
