package medicaldoctor.utils.tests;

import medicaldoctor.entities.Patient;
import medicaldoctor.entities.User;

public class FakeQueries {

    private FakeQueries() {
    }

    public static final QueryFunc USER_BY_USERNAME = (x, db) -> {
        String username = ((User) x).getUserName();
        String param = (String) db.getParam("username");
        return username.equals(param);
    };

    public static final QueryFunc PATIENT_BY_ID = (x, db) -> {
        int id = ((Patient) x).getId();
        int param = (Integer) db.getParam("id");
        return id == param;
    };

}
