package medicaldoctor.tests.util;

import medicaldoctor.core.AppSession;
import medicaldoctor.entities.User;
import medicaldoctor.utils.tests.LoginScope;
import org.junit.Assert;
import org.junit.Test;

public class LoginScopeTest {

    @Test
    public void shouldCorrectlySetActiveUser() {
        User user = new User();
        user.setUserName("test");
        try {
            Assert.assertNull(AppSession.getActiveUser());
            Assert.assertTrue(false);
        } catch (IllegalStateException ise) {
        }
        Assert.assertFalse(AppSession.isLoggedIn());
        try (LoginScope s = new LoginScope(user)) {
            Assert.assertEquals("test", AppSession.getActiveUser().getUserName());
            Assert.assertTrue(AppSession.isLoggedIn());
        }
        try {
            Assert.assertNull(AppSession.getActiveUser());
            Assert.assertTrue(false);
        } catch (IllegalStateException ise) {
        }
        Assert.assertFalse(AppSession.isLoggedIn());
    }

}
