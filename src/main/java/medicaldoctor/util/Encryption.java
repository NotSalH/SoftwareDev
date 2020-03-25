package medicaldoctor.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class Encryption {

    private final SecretKeyFactory hashFactory;
    private final int iterations;
    private final int size;

    public Encryption(int iterations, int size) {
        try {
            this.hashFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException nsax) {
            throw new EncryptionException(nsax);
        }
        this.iterations = iterations;
        this.size = size;
    }

    public Encryption() {
        this(65536, 128);
    }

    public HashAndSalt hashPassword(String password) {
        return hashPassword(password, generateSalt());
    }

    public boolean checkPassword(String password, HashAndSalt check) {
        try {
            String actual = hashPassword(password, Hex.decodeHex(check.getSalt())).getHash();
            String expected = check.getHash();
            return actual.equals(expected);
        } catch (DecoderException de) {
            throw new EncryptionException(de);
        }
    }

    private HashAndSalt hashPassword(String password, byte[] salt) {
        try {
            PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, iterations, size * 4);
            byte[] hash = hashFactory.generateSecret(keySpec).getEncoded();
            return new HashAndSalt(bytesToString(hash), bytesToString(salt));
        } catch (InvalidKeySpecException ikse) {
            throw new EncryptionException(ikse);
        }
    }

    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[size / 2];
        random.nextBytes(salt);
        return salt;
    }

    private static String bytesToString(byte[] bytes) {
        return new String(Hex.encodeHex(bytes));
    }

}
