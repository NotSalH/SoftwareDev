package medicaldoctor.setup;

import medicaldoctor.core.AppSession;
import medicaldoctor.core.DatabaseScope;
import medicaldoctor.core.Permission;
import medicaldoctor.entities.User;
import medicaldoctor.entities.UserType;
import medicaldoctor.util.Encryption;

public final class InitTestDatabase {

    private InitTestDatabase() {
    }

    private static final Encryption ENCRYPTION = AppSession.ENCRYPTION;

    private static DatabaseScope s;

    public static void main(String[] args) throws Exception {
        try (DatabaseScope s_ = new DatabaseScope()) {
            s = s_;
            s.beginTransaction();
            deletePreviousData();
            updatePermissions();
            insertUserTypes();
            insertUsers();
            s.commit();
        } catch (Exception e) {
            if (s != null) {
                s.rollback();
            }
            throw e;
        } finally {
            DatabaseScope._shutdown();
        }
    }

    private static void deletePreviousData() {
        for (Class c : Entities.LIST) {
            s.runUpdate("DELETE FROM " + c.getSimpleName());
        }
    }

    private static void updatePermissions() {
        update(UserType.ADMIN, Permission.values());
        update(UserType.EXECUTIVE, Permission.values());
        update(UserType.STAFF,
                Permission.REGISTER_PATIENT);
    }

    private static void update(UserType userType, Permission... permissions) {
        for (Permission permission : permissions) {
            userType.addPermission(permission);
        }
    }

    private static void insertUserTypes() {
        UserType.ADMIN.save();
        UserType.EXECUTIVE.save();
        UserType.STAFF.save();
        UserType.NURSE.save();
        UserType.DOCTOR.save();
        UserType.RADIOLOGIC_LAB_WORKER.save();
        UserType.HEMATOLOGIC_LAB_WORKER.save();
    }

    private static void insertUsers() {
        User user;

        user = new User();
        user.setFirstName("Network");
        user.setLastName("Admin");
        user.setUserName("admin");
        user.setPasswordHashAndSalt(ENCRYPTION.hashPassword("password123"));
        user.setAdditionalPasswordHashAndSalt(ENCRYPTION.hashPassword("secure@123"));
        user.setType(UserType.ADMIN);
        user.save();
    }

}
