package medicaldoctor.core;

import medicaldoctor.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * All database interaction exists within the lifecycle of this object. These
 * methods are specifically relate the lifecycle of the session. Use within a
 * "try with resources".
 */
public class DatabaseScope implements AutoCloseable {

    private static Session session;
    private static Transaction transaction;
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    /**
     * Open a new database session.
     */
    public DatabaseScope() {
        if (session != null) {
            throw new IllegalStateException("Database session already open");
        }
        session = SESSION_FACTORY.openSession();
    }

    /**
     * Perform an INSERT/UPDATE/DELETE operation.
     *
     * @param sql
     * @return number of updated records
     */
    public int runUpdate(String sql) {
        return session.createQuery(sql).executeUpdate();
    }

    /**
     * Begin a transaction, remember to commit after updates, and rollback in
     * catch block.
     */
    public void beginTransaction() {
        if (transaction != null) {
            throw new IllegalStateException("Already in database transaction");
        }
        transaction = session.beginTransaction();
    }

    /**
     * Commit transaction updates to database, place at the end of updates.
     */
    public void commit() {
        transaction.commit();
        transaction = null;
    }

    /**
     * Cancel transaction, place in catch block.
     */
    public void rollback() {
        transaction.rollback();
        transaction = null;
    }

    @Override
    public void close() throws Exception {
        session.close();
        session = null;
        transaction = null;
    }

    /**
     * Closes the session factory, used only in highest program scope.
     */
    public static void _shutdown() {
        SESSION_FACTORY.close();
    }

    /**
     * Static access to the active session for entity operations. Prefer methods
     * on instances for controller operations.
     *
     * @return the active session
     */
    public static Session _getSession() {
        if (session == null) {
            throw new IllegalStateException("Not in database session scope");
        }
        return session;
    }

    private static SessionFactory buildSessionFactory() {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        config.addAnnotatedClass(User.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties()).build();
        return config.buildSessionFactory(serviceRegistry);
    }

}
