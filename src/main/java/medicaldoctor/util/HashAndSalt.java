package medicaldoctor.util;

public class HashAndSalt {

    private final String hash;

    private final String salt;

    public HashAndSalt(String hash, String salt) {
        this.hash = hash;
        this.salt = salt;
    }

    public String getHash() {
        return hash;
    }

    public String getSalt() {
        return salt;
    }

}
