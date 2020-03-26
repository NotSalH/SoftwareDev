package medicaldoctor.setup;

import medicaldoctor.entities.User;
import medicaldoctor.entities.UserType;
import medicaldoctor.entities.UserTypePermission;

public class Entities {

    private Entities() {
    }

    // sort so dependent classes are deleted before depended on classes
    public static Class[] LIST = new Class[]{
        User.class, UserTypePermission.class, UserType.class};

    public static void main(String[] args) {
        for (Class c : LIST) {
            System.out.println(c.getSimpleName());
        }
    }

}
