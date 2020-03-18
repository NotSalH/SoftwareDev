package medicaldoctor.core;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransactionScope implements AutoCloseable {

    private final Session session;
    private final Transaction transaction;

    public TransactionScope() {
        this.session = Global.SESSION_FACTORY.openSession();
        this.transaction = session.beginTransaction();
    }

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void commit() {
        transaction.commit();
    }

    public int run(String sql) {
        return session.createQuery(sql).executeUpdate();
    }

    public void save(Object obj) {
        session.save(obj);
    }

    @Override
    public void close() throws Exception {
        session.close();
    }

}
