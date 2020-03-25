package medicaldoctor.tests.backend;

import medicaldoctor.backend.LoginResult;
import medicaldoctor.backend.LoginService;
import medicaldoctor.core.AppSession;
import medicaldoctor.entities.User;
import medicaldoctor.util.Encryption;
import medicaldoctor.utils.tests.FakeDatabase;
import medicaldoctor.utils.tests.QueryFunc;
import org.junit.Assert;
import org.junit.Test;

public class WhenLoggingOn {

    private static final String USERNAME = "username1";
    private static final String PASSWORD = "password1";

    @Test
    public void shouldResultWrongUsernameIfUsernameDoesNotExist() throws Exception {
        setupDatabase();
        LoginResult result = LoginService.checkLogin("5892754", PASSWORD);
        Assert.assertEquals(LoginResult.WRONG_USERNAME, result);
    }

    @Test
    public void shouldResultWrongPasswordIfUsernameDoesExistButWithWrongPassword() throws Exception {
        setupDatabase();
        LoginResult result = LoginService.checkLogin(USERNAME, "594137549317");
        Assert.assertEquals(LoginResult.WRONG_PASSWORD, result);
    }

    @Test
    public void shouldResultSuccessIfMatchingUsernameAndPassword() throws Exception {
        setupDatabase();
        LoginResult result = LoginService.checkLogin(USERNAME, PASSWORD);
        Assert.assertEquals(LoginResult.SUCCESS, result);
        Assert.assertEquals(USERNAME, AppSession.getActiveUser().getUserName());
    }

    private void setupDatabase() throws Exception {
        User user = createUser(USERNAME, PASSWORD);
        User otherUser = createUser("admin", "test");
        QueryFunc q = (x, qr) -> {
            String usename = ((User) x).getUserName();
            String param = (String) qr.getParam("username");
            return usename.equals(param);
        };
        FakeDatabase.setDatabase(q, otherUser, user);
    }

    private User createUser(String username, String password) throws Exception {
        Encryption encryption = new Encryption();
        User user = new User();
        user.setUserName(username);
        user.setPasswordHashAndSalt(encryption.hashPassword(password));
        return user;
    }

}
