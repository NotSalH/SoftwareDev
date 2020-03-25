package medicaldoctor.utils.tests;

import medicaldoctor.core.AppSession;
import medicaldoctor.entities.User;

public class LoginScope implements AutoCloseable {

    public LoginScope(User user) {
        AppSession.setActiveUser(user);
    }

    @Override
    public void close() {
        AppSession.setActiveUser(null);
    }

}
