package medicaldoctor.setup;

import medicaldoctor.Global;
import medicaldoctor.TransactionScope;
import medicaldoctor.entities.User;

public final class InitTestDatabase {

    private InitTestDatabase() {
    }

    private static TransactionScope t;

    public static void main(String[] args) throws Exception {
        try (TransactionScope t_ = new TransactionScope()) {
            t = t_;
            deletePreviousData();
            insertUsers();
            t.commit();
        } finally {
            Global.shutdown();
        }
    }

    private static void deletePreviousData() {
        t.run("DELETE FROM User");
    }

    private static void insertUsers() {
        User user;

        user = new User();
        user.setFirstName("Network");
        user.setLastName("Admin");
        user.setUserName("admin");
        user.setPasswordHash("123");
        user.setPasswordSalt("456");
        t.save(user);
    }

}
