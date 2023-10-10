//TODO: Complete java docs and code in missing spots.
//This code is already made for you, you just need to call Hashing.cryptHash whenever you want to generate the hash of a particular String. The output is formatted in HexaDecimal  
/**
 * This class hashes the string values using SHA512 computation formula.
 * 
 * @author Jonathan Rosales.
 */
public class Hashing extends SHA512 {

    /**
     * This method hashes the string.
     * @param s user given string.
     * @return hashed value.
     */
    public static String cryptHash(String s) {
        String digest = hashSHA512(s);
        return digest.substring(0,128);
    }
}
