package medicaldoctor.tests.backend;

import javafx.util.Pair;
import medicaldoctor.backend.data.LoginResult;
import medicaldoctor.backend.LoginService;
import medicaldoctor.core.AppSession;
import medicaldoctor.entities.LogRecord;
import medicaldoctor.entities.User;
import medicaldoctor.util.Encryption;
import medicaldoctor.utils.tests.FakeDatabase;
import medicaldoctor.utils.tests.FakeQueries;
import medicaldoctor.utils.tests.LoginScope;
import org.junit.Assert;
import org.junit.Test;

public class WhenLoggingOn {

    private static final String USERNAME = "username1";
    private static final String PASSWORD = "password1";
    private static final String ADDITIONAL_PASSWORD = "really_good_password";

    @Test
    public void shouldResultWrongUsernameIfUsernameDoesNotExist() throws Exception {
        FakeDatabase db = setupDatabase().getValue();
        LoginResult result = LoginService.checkLogin("5892754", PASSWORD);
        Assert.assertEquals(LoginResult.WRONG_USERNAME, result);
        Assert.assertEquals(0, db.getLoggedEventsCount());
        AppSession.setActiveUser(null);
    }

    @Test
    public void shouldResultWrongPasswordIfUsernameDoesExistButWithWrongPassword() throws Exception {
        FakeDatabase db = setupDatabase().getValue();
        LoginResult result = LoginService.checkLogin(USERNAME, "594137549317");
        Assert.assertEquals(LoginResult.WRONG_PASSWORD, result);
        Assert.assertEquals(0, db.getLoggedEventsCount());
        AppSession.setActiveUser(null);
    }

    @Test
    public void shouldResultSuccessIfMatchingUsernameAndPassword() throws Exception {
        FakeDatabase db = setupDatabase().getValue();
        LoginResult result = LoginService.checkLogin(USERNAME, PASSWORD);
        Assert.assertEquals(LoginResult.SUCCESS, result);
        Assert.assertEquals(USERNAME, AppSession.getActiveUser().getUserName());
        Assert.assertEquals(1, db.getLoggedEventsCount());
        AppSession.setActiveUser(null);
    }

    @Test
    public void usernameShouldBeCaseInsensitive() throws Exception {
        FakeDatabase db = setupDatabase().getValue();
        LoginResult result = LoginService.checkLogin(USERNAME.toUpperCase(), PASSWORD);
        Assert.assertEquals(LoginResult.SUCCESS, result);
        Assert.assertEquals(USERNAME, AppSession.getActiveUser().getUserName());
        Assert.assertEquals(1, db.getLoggedEventsCount());
        AppSession.setActiveUser(null);
    }

    @Test
    public void shouldResultWrongPasswordIfAdditionalPasswordIsWrong() throws Exception {
        Pair<User, FakeDatabase> pair = setupDatabase();
        User testUser = pair.getKey();
        FakeDatabase db = pair.getValue();
        try (LoginScope s = new LoginScope(testUser)) {
            LoginResult result = LoginService.checkAdditionalPassword("garbage");
            Assert.assertEquals(LoginResult.WRONG_PASSWORD, result);
        }
    }

    @Test
    public void shouldResultSuccessIfMatchingAdditionalPassword() throws Exception {
        Pair<User, FakeDatabase> pair = setupDatabase();
        User testUser = pair.getKey();
        FakeDatabase db = pair.getValue();
        try (LoginScope s = new LoginScope(testUser)) {
            LoginResult result = LoginService.checkAdditionalPassword(ADDITIONAL_PASSWORD);
            Assert.assertEquals(LoginResult.SUCCESS, result);
        }
    }

    private Pair<User, FakeDatabase> setupDatabase() throws Exception {
        User user = createUser(USERNAME, PASSWORD);
        user.setAdditionalPasswordHashAndSalt(ENCRYPTION.hashPassword(ADDITIONAL_PASSWORD));
        User otherUser = createUser("admin", "test");
        FakeDatabase db = new FakeDatabase(FakeQueries.USER_BY_USERNAME, otherUser, user);
        return new Pair(user, db);
    }

    private static final Encryption ENCRYPTION = AppSession.ENCRYPTION;

    private User createUser(String username, String password) throws Exception {
        User user = new User();
        user.setUserName(username);
        user.setPasswordHashAndSalt(ENCRYPTION.hashPassword(password));
        return user;
    }

}
