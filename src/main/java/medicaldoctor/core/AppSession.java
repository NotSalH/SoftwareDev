package medicaldoctor.core;

import medicaldoctor.entities.User;
import medicaldoctor.util.Encryption;

/**
 * Static global state of the program session.
 */
public final class AppSession {

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
