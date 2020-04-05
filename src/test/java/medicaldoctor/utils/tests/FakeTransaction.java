package medicaldoctor.utils.tests;

import javax.transaction.Synchronization;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public class FakeTransaction implements Transaction {

    @Override
    public void commit() {
    }

    ////////////////////////////////////////////////////////////////////////////
    @Override
    public TransactionStatus getStatus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void registerSynchronization(Synchronization s) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setTimeout(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getTimeout() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void begin() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void rollback() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setRollbackOnly() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean getRollbackOnly() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isActive() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
