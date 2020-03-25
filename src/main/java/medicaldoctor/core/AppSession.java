package medicaldoctor.core;

import medicaldoctor.entities.User;

/**
 * Static global state of the program session.
 */
public final class AppSession {

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
