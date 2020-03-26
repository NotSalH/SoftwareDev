package medicaldoctor.core;

import medicaldoctor.entities.User;
import medicaldoctor.util.Encryption;
import medicaldoctor.util.FirstLetterLastNameUserNameGenerator;
import medicaldoctor.util.PasswordGenerator;
import medicaldoctor.util.RandomPasswordGenerator;
import medicaldoctor.util.UserNameGenerator;

/**
 * Static global state of the program session.
 */
public final class AppSession {

    public static PasswordGenerator TEMPORARY_PASSWORD_GENERATOR
            = new RandomPasswordGenerator(18);
    public static UserNameGenerator USER_NAME_GENERATOR
            = new FirstLetterLastNameUserNameGenerator();
    public static Encryption ENCRYPTION = new Encryption();

    private static User activeUser;

    public static User getActiveUser() {
        if (activeUser == null) {
            throw new IllegalStateException("User is not logged on");
        }
        return activeUser;
    }

    public static void setActiveUser(User user) {
        activeUser = user;
    }

    public static boolean isLoggedIn() {
        return activeUser != null;
    }

}
