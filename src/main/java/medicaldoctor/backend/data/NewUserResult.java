package medicaldoctor.backend.data;

import medicaldoctor.entities.User;

public class NewUserResult {

    public User user;

    public String temporaryPassword;

    public NewUserResult(User user, String temporaryPassword) {
        this.user = user;
        this.temporaryPassword = temporaryPassword;
    }

}
