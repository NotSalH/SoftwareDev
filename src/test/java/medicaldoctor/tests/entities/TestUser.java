package medicaldoctor.tests.entities;

import junit.framework.Assert;
import medicaldoctor.controllers.LookUp;
import medicaldoctor.core.Permission;
import medicaldoctor.entities.User;
import medicaldoctor.entities.UserType;
import org.junit.Test;

public class TestUser {

    @Test
    public void userShouldHavePermission() {
        UserType type = new UserType("Tester", true, LookUp.ADMIN_DASHBOARD);
        type.addPermission(Permission.REGISTER_PATIENT);
        type.addPermission(Permission.REGISTER_NEW_USER);
        User user = new User();
        user.setType(type);
        Assert.assertTrue(user.hasPermission(Permission.REGISTER_PATIENT));
    }

    @Test
    public void userShouldNOTHavePermission() {
        UserType type = new UserType("Tester", true, LookUp.STAFF_DASHBOARD);
        type.addPermission(Permission.REGISTER_NEW_USER);
        User user = new User();
        user.setType(type);
        Assert.assertFalse(user.hasPermission(Permission.REGISTER_PATIENT));
    }

}
