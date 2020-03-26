package medicaldoctor.utils.tests;

import medicaldoctor.core.AppSession;
import medicaldoctor.entities.User;
import medicaldoctor.entities.UserType;
import medicaldoctor.util.Encryption;
import medicaldoctor.util.HashAndSalt;

public class TestEntities {
    
    private TestEntities() {
    }
    
    private static final Encryption ENCRYPTION = AppSession.ENCRYPTION;
    
    public static final String TEST_STAFF_USERNAME = "receptionistusername";
    public static final String TEST_STAFF_PASSWORD = "receptionistnursepassword";
    public static final HashAndSalt TEST_STAFF_HASH = ENCRYPTION.hashPassword(TEST_STAFF_PASSWORD);
    
    public static User getStaffUser() {
        User user = new User();
        user.setUserName(TEST_STAFF_USERNAME);
        user.setPasswordHashAndSalt(TEST_STAFF_HASH);
        user.setType(UserType.STAFF);
        return user;
    }
    
    public static User getAdminUser() {
        User user = new User();
        user.setUserName("aaaa");
        user.setType(UserType.ADMIN);
        return user;
    }
    
}
