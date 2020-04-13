package medicaldoctor.utils.tests;

import medicaldoctor.entities.Patient;
import medicaldoctor.entities.User;
import medicaldoctor.entities.UserType;

public class FakeQueries {

    private FakeQueries() {
    }

    public static final QueryFunc USER_BY_USERNAME = (x, db) -> {
        if (x instanceof User) {
            String username = ((User) x).getUserName();
            String param = (String) db.getParam("username");
            return username.equals(param);
        }
        return false;
    };

    public static final QueryFunc USERTYPE_BY_NAME = (x, db) -> {
        if (x instanceof UserType) {
            String name = ((UserType) x).getName();
            String param = (String) db.getParam("usertype");
            return name.equals(param);
        }
        return false;
    };

    public static final QueryFunc PATIENT_BY_ID = (x, db) -> {
        if (x instanceof Patient) {
            int id = ((Patient) x).getId();
            int param = (Integer) db.getParam("id");
            return id == param;
        }
        return false;
    };

}
