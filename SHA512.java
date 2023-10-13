import java.security.*;
import java.nio.charset.*;

//This code is already made for you, it is extended by Hashing to generate the SHA-512 hash of a String. No explicit call to any method here is required.

/**
 * Class that implements SHA512 encrypting.
 */
public class SHA512 {

    /**
     * This method hashes a message.
     * @param message user given message.
     * @return encrpyted message.
     */
    protected static String hashSHA512(String message) {
        String sha512ValueHexa = "";
        try {
            MessageDigest digest512 = MessageDigest.getInstance("SHA-512");
            sha512ValueHexa = byteToHex(digest512.digest(message.getBytes(StandardCharsets.UTF_8)));
        }
        catch(NoSuchAlgorithmException exp) {
            exp.getMessage();
        }
        return sha512ValueHexa;
    }

    /**
     * This method turns a byte array into a Hexidecimal.
     * @param digest the byte array.
     * @return Hexidecimal string.
     */
    public static String byteToHex(byte[] digest) {
        StringBuilder vector = new StringBuilder();
        for (byte c : digest) {
            vector.append(String.format("%02X", c));
        }
        String output = vector.toString();
        return output;
    }
}
