package medicaldoctor.tests.backend;

import junit.framework.Assert;
import medicaldoctor.backend.UserService;
import medicaldoctor.backend.data.NewUserRequest;
import medicaldoctor.backend.data.NewUserResult;
import medicaldoctor.core.AppSession;
import medicaldoctor.entities.User;
import medicaldoctor.entities.UserType;
import medicaldoctor.util.Encryption;
import medicaldoctor.utils.tests.FakeDatabase;
import medicaldoctor.utils.tests.FakeQueries;
import medicaldoctor.utils.tests.HardcodedPasswordGenerator;
import medicaldoctor.utils.tests.LoginScope;
import medicaldoctor.utils.tests.TestEntities;
import org.junit.Test;

public class WhenCreatingANewUser {

    private static final Encryption ENCRYPTION = AppSession.ENCRYPTION;

    private static final String FIRST_NAME = "Apple";
    private static final String LAST_NAME = "Banana";
    private static final String USER_TYPE = UserType.DOCTOR.getName();
    private static final String DEPARTMENT = "cheese factory";
    private static final Integer OFFICE_NUM = 555;
    private static final String PASSWORD = "amazing_password";

    private static final String EXPECTED_USER_NAME = "abanana";

    @Test
    public void shouldSuccessfullyCreateNewUser() throws Exception {
        FakeDatabase db = new FakeDatabase();
        db.addQuery(FakeQueries.USER_BY_USERNAME);
        db.addQuery(FakeQueries.USERTYPE_BY_NAME);
        AppSession.TEMPORARY_PASSWORD_GENERATOR = new HardcodedPasswordGenerator(PASSWORD);
        NewUserRequest request = new NewUserRequest();
        request.firstName = FIRST_NAME;
        request.lastName = LAST_NAME;
        request.userType = USER_TYPE;
        request.department = DEPARTMENT;
        request.officeNum = OFFICE_NUM;

        try (LoginScope s = new LoginScope(TestEntities.getAdminUser())) {
            NewUserResult newUserResult = UserService.createNewUser(request);

            Assert.assertEquals(EXPECTED_USER_NAME, newUserResult.user.getUserName());
            Assert.assertEquals(PASSWORD, newUserResult.temporaryPassword);

            User savedUser = (User) db.getSavedEntitiesOfType(User.class)[0];

            Assert.assertEquals(FIRST_NAME, savedUser.getFirstName());
            Assert.assertEquals(LAST_NAME, savedUser.getLastName());
            Assert.assertEquals(EXPECTED_USER_NAME, savedUser.getUserName());
            Assert.assertEquals(USER_TYPE, savedUser.getType().getName());
            Assert.assertEquals(DEPARTMENT, savedUser.getDepartment());
            Assert.assertEquals(OFFICE_NUM, savedUser.getOfficeNum());
            Assert.assertTrue(ENCRYPTION.checkPassword(PASSWORD, savedUser.getPasswordHashAndSalt()));

            Assert.assertEquals(1, db.getLoggedEventsCount());
        }
    }

    @Test
    public void shouldCreateUserNameWithIdToMakeUnique() throws Exception {
        User problemCausingUser1 = new User();
        problemCausingUser1.setUserName(EXPECTED_USER_NAME);
        problemCausingUser1.setFirstName("one");

        User problemCausingUser2 = new User();
        problemCausingUser2.setUserName(EXPECTED_USER_NAME + "1");
        problemCausingUser2.setFirstName("two");

        FakeDatabase db = new FakeDatabase(problemCausingUser1, problemCausingUser2);
        db.addQuery(FakeQueries.USER_BY_USERNAME, 3);
        db.addQuery(FakeQueries.USERTYPE_BY_NAME);
        AppSession.TEMPORARY_PASSWORD_GENERATOR = new HardcodedPasswordGenerator(PASSWORD);

        NewUserRequest request = new NewUserRequest();
        request.firstName = FIRST_NAME;
        request.lastName = LAST_NAME;
        request.userType = USER_TYPE;
        request.department = DEPARTMENT;
        request.officeNum = OFFICE_NUM;

        try (LoginScope s = new LoginScope(TestEntities.getAdminUser())) {
            NewUserResult newUserResult = UserService.createNewUser(request);

            Assert.assertEquals(EXPECTED_USER_NAME + "2", newUserResult.user.getUserName());
            Assert.assertEquals(PASSWORD, newUserResult.temporaryPassword);

            User savedUser = (User) db.getSavedEntitiesOfType(User.class)[0];

            Assert.assertEquals(FIRST_NAME, savedUser.getFirstName());
            Assert.assertEquals(LAST_NAME, savedUser.getLastName());
            Assert.assertEquals(EXPECTED_USER_NAME + "2", savedUser.getUserName());
            Assert.assertEquals(USER_TYPE, savedUser.getType().getName());
            Assert.assertEquals(DEPARTMENT, savedUser.getDepartment());
            Assert.assertEquals(OFFICE_NUM, savedUser.getOfficeNum());
            Assert.assertTrue(ENCRYPTION.checkPassword(PASSWORD, savedUser.getPasswordHashAndSalt()));

            Assert.assertEquals(1, db.getLoggedEventsCount());
        }
    }

}
