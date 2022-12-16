package Classes;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

public class Encryptor {
    public static String encryptString(String input) {
        String encryptedString = "";
        try {
            // Set the secret key
            String secretKey = "barangaycanumaywestpayrollsystem";
            Key key = new SecretKeySpec(secretKey.getBytes(), "AES");

            // Encrypt the input string
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));

            // Convert the encrypted bytes to a base64-encoded string
            encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }
}
