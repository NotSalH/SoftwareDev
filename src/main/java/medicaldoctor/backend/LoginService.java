package medicaldoctor.backend;

import medicaldoctor.backend.data.LoginResult;
import medicaldoctor.core.AppSession;
import medicaldoctor.core.DatabaseScope;
import medicaldoctor.entities.User;

public class LoginService {

    private LoginService() {
    }

    /**
     * Will check the if the provided username and password is a valid login.
     *
     * @param username
     * @param password
     * @return LoginResult
     * @throws Exception
     */
    public static LoginResult checkLogin(String username, String password)
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
                if (AppSession.isLoggedIn()) {
                    throw new IllegalStateException("User already logged on");
                }
                AppSession.setActiveUser(user);
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
