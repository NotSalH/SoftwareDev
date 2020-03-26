package medicaldoctor.backend;

import medicaldoctor.backend.results.ChangePasswordResult;
import medicaldoctor.core.AppSession;
import medicaldoctor.core.DatabaseScope;
import medicaldoctor.entities.User;
import medicaldoctor.util.Encryption;
import medicaldoctor.util.HashAndSalt;

public class UserService {

    private UserService() {
    }

    private static final Encryption ENCRYPTION = new Encryption();

    public static ChangePasswordResult changePassword(String newPassword,
            String confirmPassword) throws Exception {
        User user = AppSession.getActiveUser();
        if (!newPassword.equals(confirmPassword)) {
            return ChangePasswordResult.PASSWORDS_NOT_MATCHING;
        }
        if (ENCRYPTION.checkPassword(newPassword, user.getPasswordHashAndSalt())) {
            return ChangePasswordResult.PASSWORD_NOT_CHANGED;
        }
        HashAndSalt newHash = ENCRYPTION.hashPassword(newPassword);
        user.setPasswordHashAndSalt(newHash);
        try (DatabaseScope scope = new DatabaseScope()) {
            user.save();
        }
        return ChangePasswordResult.SUCCESS;
    }

}
