package medicaldoctor.tests.util;

import medicaldoctor.util.Encryption;
import medicaldoctor.util.HashAndSalt;
import org.junit.Assert;
import org.junit.Test;

public class EncryptionTest {
    
    @Test
    public void encryptedPasswordsShouldMatch() throws Exception {
        Encryption e = new Encryption();
        final String password = "banana@muffins%&33";
        HashAndSalt encoded = e.hashPassword(password);
        Assert.assertTrue(e.checkPassword(password, encoded));
    }
    
}
