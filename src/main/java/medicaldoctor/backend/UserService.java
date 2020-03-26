package medicaldoctor.backend;

import medicaldoctor.backend.data.ChangePasswordResult;
import medicaldoctor.backend.data.NewUserRequest;
import medicaldoctor.backend.data.NewUserResult;
import medicaldoctor.core.AppSession;
import medicaldoctor.core.DatabaseScope;
import medicaldoctor.entities.User;
import medicaldoctor.util.HashAndSalt;

public class UserService {

    private UserService() {
    }

    /**
     * Register a new user in the database with the provided form information,
     * and also a generated username and temporary password.
     *
     * @param request
     * @return a pair of the new user and their plaintext temporary password.
     * @throws Exception
     */
    public static NewUserResult createNewUser(NewUserRequest request) throws Exception {
        String username = generateUniqueUsername(request);
        String tempPassword = AppSession.TEMPORARY_PASSWORD_GENERATOR
                .generate();
        HashAndSalt tempPasswordHash = AppSession.ENCRYPTION
                .hashPassword(tempPassword);

        User newUser = new User();
        newUser.setFirstName(request.firstName);
        newUser.setLastName(request.lastName);
        newUser.setUserName(username);
        newUser.setPasswordHashAndSalt(tempPasswordHash);
        newUser.setType(request.userType);
        newUser.setDepartment(request.department);
        newUser.setOfficeNum(request.officeNum);

        try (DatabaseScope scope = new DatabaseScope()) {
            newUser.save();
        }
        return new NewUserResult(newUser, tempPassword);
    }

    private static String generateUniqueUsername(NewUserRequest request) throws Exception {
        String username = AppSession.USER_NAME_GENERATOR
                .generate(request.firstName, request.lastName);
        int counter = 0;
        try (DatabaseScope scope = new DatabaseScope()) {
            while (User.byUsername(username) != null) {
                counter++;
                username = AppSession.USER_NAME_GENERATOR
                        .generate(request.firstName, request.lastName, counter);
            }
        }
        return username;
    }

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
