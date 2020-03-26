package medicaldoctor.tests.backend;

import medicaldoctor.backend.data.LoginResult;
import medicaldoctor.backend.LoginService;
import medicaldoctor.core.AppSession;
import medicaldoctor.entities.User;
import medicaldoctor.util.Encryption;
import medicaldoctor.utils.tests.FakeDatabase;
import medicaldoctor.utils.tests.LoginScope;
import medicaldoctor.utils.tests.QueryFunc;
import org.junit.Assert;
import org.junit.Test;

public class WhenLoggingOn {

    private static final String USERNAME = "username1";
    private static final String PASSWORD = "password1";
    private static final String ADDITIONAL_PASSWORD = "really_good_password";

    @Test
    public void shouldResultWrongUsernameIfUsernameDoesNotExist() throws Exception {
        setupDatabase();
        LoginResult result = LoginService.checkLogin("5892754", PASSWORD);
        Assert.assertEquals(LoginResult.WRONG_USERNAME, result);
        AppSession.setActiveUser(null);
    }

    @Test
    public void shouldResultWrongPasswordIfUsernameDoesExistButWithWrongPassword() throws Exception {
        setupDatabase();
        LoginResult result = LoginService.checkLogin(USERNAME, "594137549317");
        Assert.assertEquals(LoginResult.WRONG_PASSWORD, result);
        AppSession.setActiveUser(null);
    }

    @Test
    public void shouldResultSuccessIfMatchingUsernameAndPassword() throws Exception {
        setupDatabase();
        LoginResult result = LoginService.checkLogin(USERNAME, PASSWORD);
        Assert.assertEquals(LoginResult.SUCCESS, result);
        Assert.assertEquals(USERNAME, AppSession.getActiveUser().getUserName());
        AppSession.setActiveUser(null);
    }

    @Test
    public void usernameShouldBeCaseInsensitive() throws Exception {
        setupDatabase();
        LoginResult result = LoginService.checkLogin(USERNAME.toUpperCase(), PASSWORD);
        Assert.assertEquals(LoginResult.SUCCESS, result);
        Assert.assertEquals(USERNAME, AppSession.getActiveUser().getUserName());
        AppSession.setActiveUser(null);
    }

    @Test
    public void shouldResultWrongPasswordIfAdditionalPasswordIsWrong() throws Exception {
        User testUser = setupDatabase();
        try (LoginScope s = new LoginScope(testUser)) {
            LoginResult result = LoginService.checkAdditionalPassword("garbage");
            Assert.assertEquals(LoginResult.WRONG_PASSWORD, result);
        }
    }

    @Test
    public void shouldResultSuccessIfMatchingAdditionalPassword() throws Exception {
        User testUser = setupDatabase();
        try (LoginScope s = new LoginScope(testUser)) {
            LoginResult result = LoginService.checkAdditionalPassword(ADDITIONAL_PASSWORD);
            Assert.assertEquals(LoginResult.SUCCESS, result);
        }
    }

    private User setupDatabase() throws Exception {
        User user = createUser(USERNAME, PASSWORD);
        user.setAdditionalPasswordHashAndSalt(ENCRYPTION.hashPassword(ADDITIONAL_PASSWORD));
        User otherUser = createUser("admin", "test");
        QueryFunc q = (x, db) -> {
            String usename = ((User) x).getUserName();
            String param = (String) db.getParam("username");
            return usename.equals(param);
        };
        new FakeDatabase(q, otherUser, user);
        return user;
    }

    private static final Encryption ENCRYPTION = new Encryption();

    private User createUser(String username, String password) throws Exception {
        User user = new User();
        user.setUserName(username);
        user.setPasswordHashAndSalt(ENCRYPTION.hashPassword(password));
        return user;
    }

}
