package medicaldoctor.utils.tests;

import medicaldoctor.entities.User;
import medicaldoctor.util.Encryption;
import medicaldoctor.util.HashAndSalt;

public class TestEntities {

    private TestEntities() {
    }

    private static final Encryption ENCRYPTION = new Encryption();

    public static final String TEST_STAFF_USERNAME = "receptionistusername";
    public static final String TEST_STAFF_PASSWORD = "receptionistnursepassword";
    public static final HashAndSalt TEST_STAFF_HASH = ENCRYPTION.hashPassword(TEST_STAFF_PASSWORD);

    public static User getStaffUser() {
        User user = new User();
        user.setUserName(TEST_STAFF_USERNAME);
        user.setPasswordHashAndSalt(TEST_STAFF_HASH);
        return user;
    }

}
