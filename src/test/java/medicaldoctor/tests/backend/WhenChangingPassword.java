package medicaldoctor.tests.backend;

import junit.framework.Assert;
import medicaldoctor.backend.UserService;
import medicaldoctor.backend.data.ChangePasswordResult;
import medicaldoctor.entities.User;
import medicaldoctor.util.Encryption;
import medicaldoctor.utils.tests.FakeDatabase;
import medicaldoctor.utils.tests.LoginScope;
import medicaldoctor.utils.tests.TestEntities;
import org.junit.Test;

public class WhenChangingPassword {

    @Test
    public void shouldResultPasswordsDontMatch() throws Exception {
        new FakeDatabase();
        try (LoginScope s = new LoginScope(TestEntities.getStaffUser())) {
            ChangePasswordResult result = UserService
                    .changePassword("apple", "orange");
            Assert.assertEquals(ChangePasswordResult.PASSWORDS_NOT_MATCHING, result);
        }
    }

    @Test
    public void shouldResultPasswordNotChanged() throws Exception {
        new FakeDatabase();
        try (LoginScope s = new LoginScope(TestEntities.getStaffUser())) {
            ChangePasswordResult result = UserService
                    .changePassword(TestEntities.TEST_STAFF_PASSWORD, TestEntities.TEST_STAFF_PASSWORD);
            Assert.assertEquals(ChangePasswordResult.PASSWORD_NOT_CHANGED, result);
        }
    }

    private static final Encryption ENCRYPTION = new Encryption();

    @Test
    public void shouldResultSuccessful() throws Exception {
        FakeDatabase db = new FakeDatabase();
        final String newPassword = "amazing_new_password";
        try (LoginScope s = new LoginScope(TestEntities.getStaffUser())) {
            ChangePasswordResult result = UserService
                    .changePassword(newPassword, newPassword);

            Assert.assertEquals(ChangePasswordResult.SUCCESS, result);
            Assert.assertEquals(1, db.savedEntities.size());
            User savedUser = (User) db.savedEntities.remove();
            String expectedHash = ENCRYPTION
                    .hashPassword(newPassword, savedUser.getPasswordHashAndSalt().getSalt())
                    .getHash();
            Assert.assertEquals(expectedHash, savedUser.getPasswordHashAndSalt().getHash());
        }
    }

}
