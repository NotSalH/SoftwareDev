package medicaldoctor.utils.tests;

import medicaldoctor.entities.User;

public class FakeQueries {

    private FakeQueries() {
    }

    public static final QueryFunc USER_BY_USERNAME = (x, db) -> {
        String usename = ((User) x).getUserName();
        String param = (String) db.getParam("username");
        return usename.equals(param);
    };

}
