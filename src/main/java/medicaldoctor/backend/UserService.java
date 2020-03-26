package medicaldoctor.backend;

import medicaldoctor.backend.data.ChangePasswordResult;
import medicaldoctor.core.AppSession;
import medicaldoctor.core.DatabaseScope;
import medicaldoctor.entities.User;
import medicaldoctor.util.HashAndSalt;

public class UserService {

    private UserService() {
    }

    private static final Encryption ENCRYPTION = new Encryption();

    /**
     * Will check to make sure the provide passwords are the same, and different
     * than the current password, then update the active user record.
     *
     * @param newPassword
     * @param confirmPassword
     * @return ChangePasswordResult
     * @throws Exception
     */
    public static ChangePasswordResult changePassword(String newPassword,
            String confirmPassword) throws Exception {
        User user = AppSession.getActiveUser();
        if (!newPassword.equals(confirmPassword)) {
            return ChangePasswordResult.PASSWORDS_NOT_MATCHING;
        }
        if (AppSession.ENCRYPTION.checkPassword(newPassword, user.getPasswordHashAndSalt())) {
            return ChangePasswordResult.PASSWORD_NOT_CHANGED;
        }
        HashAndSalt newHash = AppSession.ENCRYPTION.hashPassword(newPassword);
        user.setPasswordHashAndSalt(newHash);
        try (DatabaseScope scope = new DatabaseScope()) {
            user.save();
        }
        return ChangePasswordResult.SUCCESS;
    }

}
