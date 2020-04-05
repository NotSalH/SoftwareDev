package medicaldoctor.setup;

import medicaldoctor.core.DatabaseScope;

/**
 * Completely delete everything, dropping all tables.
 */
public class EmptyTestDatabase {

    private EmptyTestDatabase() {
    }

    private static DatabaseScope s;

    public static void main(String[] args) throws Exception {
        try (DatabaseScope s_ = new DatabaseScope()) {
            s = s_;
            s.beginTransaction();
            for (Class c : Entities.LIST) {
                s._runSQL("DROP TABLE IF EXISTS " + c.getSimpleName());
            }
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

}
