package medicaldoctor.backend;

import medicaldoctor.core.AppSession;
import medicaldoctor.core.DatabaseScope;
import medicaldoctor.entities.User;
import medicaldoctor.util.Encryption;

public class LoginService {

    private LoginService() {
    }

    private static final Encryption ENCRYPTION = new Encryption();

    public static LoginResult checkLogin(String username, String password)
            throws Exception {
        User user;
        try (DatabaseScope scope = new DatabaseScope()) {
            user = User.byUsername(username);
        }
        if (user == null) {
            return LoginResult.WRONG_USERNAME;
        } else {
            boolean passwordMatches = ENCRYPTION
                    .checkPassword(password, user.getPasswordHashAndSalt());
            if (passwordMatches) {
                AppSession.setActiveUser(user);
                return LoginResult.SUCCESS;
            } else {
                return LoginResult.WRONG_PASSWORD;
            }
        }
    }

}
