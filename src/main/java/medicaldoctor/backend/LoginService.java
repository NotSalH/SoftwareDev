package medicaldoctor.backend;

import medicaldoctor.backend.data.LoginResult;
import medicaldoctor.core.AppSession;
import medicaldoctor.core.DatabaseScope;
import medicaldoctor.entities.User;

public class LoginService {

    private LoginService() {
    }

    public static final String MESSAGE_USER_LOGGED_IN = "User logged in.";
    public static final String MESSAGE_USER_LOGGED_OUT = "User logged out.";

    /**
     * Will check the if the provided username and password is a valid login,
     * while setting the session active user if successful.
     *
     * @param username
     * @param password
     * @return LoginResult
     * @throws Exception
     */
    public static LoginResult checkLogin(String username, String password)
            throws Exception {
        return checkLogin(username, password, true);
    }

    /**
     * Will check the if the provided username and password is a valid login,
     * while setting the session active user if successful.
     *
     * @param username
     * @param password
     * @param firstLogin
     * @return LoginResult
     * @throws Exception
     */
    public static LoginResult checkLogin(String username, String password, boolean firstLogin)
            throws Exception {
        User user;
        try (DatabaseScope scope = new DatabaseScope()) {
            user = User.byUsername(username.toLowerCase());
        }
        if (user == null) {
            return LoginResult.WRONG_USERNAME;
        } else {
            boolean passwordMatches = AppSession.ENCRYPTION
                    .checkPassword(password, user.getPasswordHashAndSalt());
            if (passwordMatches) {
                if (firstLogin) {
                    if (AppSession.isLoggedIn()) {
                        throw new IllegalStateException("User already logged on");
                    }
                    AppSession.setActiveUser(user);
                    AppSession.logEventInNewScope(MESSAGE_USER_LOGGED_IN);
                }
                return LoginResult.SUCCESS;
            } else {
                return LoginResult.WRONG_PASSWORD;
            }
        }
    }

    /**
     * Check if the provided additional password is valid for login for the
     * active user.
     *
     * @param password
     * @return LoginResult
     * @throws Exception
     */
    public static LoginResult checkAdditionalPassword(String password) throws Exception {
        User user = AppSession.getActiveUser();
        boolean passwordMatches = AppSession.ENCRYPTION
                .checkPassword(password, user.getAdditionalPasswordHashAndSalt());
        if (passwordMatches) {
            return LoginResult.SUCCESS;
        } else {
            return LoginResult.WRONG_PASSWORD;
        }
    }

}
