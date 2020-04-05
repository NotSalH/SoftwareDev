package medicaldoctor.utils.tests;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.Filter;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.LobHelper;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.NaturalIdLoadAccess;
import org.hibernate.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionEventListener;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionBuilder;
import org.hibernate.SimpleNaturalIdLoadAccess;
import org.hibernate.Transaction;
import org.hibernate.TypeHelper;
import org.hibernate.UnknownProfileException;
import org.hibernate.graph.RootGraph;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.jdbc.Work;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.NativeQuery;
import org.hibernate.stat.SessionStatistics;

public class FakeSession implements Session {

    public FakeDatabase db;

    public FakeSession(FakeDatabase db) {
        this.db = db;
    }

    @Override
    public <T> org.hibernate.query.Query<T> createQuery(String string, Class<T> type) {
        return new FakeQuery<T>(db);
    }

    @Override
    public Serializable save(Object o) {
        db.savedEntities.add(o);
        return null;
    }

    @Override
    public void close() throws HibernateException {
    }

    @Override
    public Transaction beginTransaction() {
        return new FakeTransaction();
    }

    ////////////////////////////////////////////////////////////////////////////
    @Override
    public SharedSessionBuilder sessionWithOptions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void flush() throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFlushMode(FlushMode fm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public FlushModeType getFlushMode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setHibernateFlushMode(FlushMode fm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public FlushMode getHibernateFlushMode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCacheMode(CacheMode cm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CacheMode getCacheMode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SessionFactory getSessionFactory() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void cancelQuery() throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isDirty() throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isDefaultReadOnly() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setDefaultReadOnly(boolean bln) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Serializable getIdentifier(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean contains(String string, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void evict(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T load(Class<T> type, Serializable srlzbl, LockMode lm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T load(Class<T> type, Serializable srlzbl, LockOptions lo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object load(String string, Serializable srlzbl, LockMode lm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object load(String string, Serializable srlzbl, LockOptions lo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T load(Class<T> type, Serializable srlzbl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object load(String string, Serializable srlzbl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void load(Object o, Serializable srlzbl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void replicate(Object o, ReplicationMode rm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void replicate(String string, Object o, ReplicationMode rm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Serializable save(String string, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveOrUpdate(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveOrUpdate(String string, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(String string, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object merge(String string, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void persist(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void persist(String string, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(String string, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void lock(Object o, LockMode lm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void lock(String string, Object o, LockMode lm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LockRequest buildLockRequest(LockOptions lo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void refresh(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void refresh(String string, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void refresh(Object o, LockMode lm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void refresh(Object o, LockOptions lo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void refresh(String string, Object o, LockOptions lo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LockMode getCurrentLockMode(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query createFilter(Object o, String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T get(Class<T> type, Serializable srlzbl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T get(Class<T> type, Serializable srlzbl, LockMode lm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T get(Class<T> type, Serializable srlzbl, LockOptions lo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object get(String string, Serializable srlzbl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object get(String string, Serializable srlzbl, LockMode lm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object get(String string, Serializable srlzbl, LockOptions lo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getEntityName(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IdentifierLoadAccess byId(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> MultiIdentifierLoadAccess<T> byMultipleIds(Class<T> type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public MultiIdentifierLoadAccess byMultipleIds(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> IdentifierLoadAccess<T> byId(Class<T> type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public NaturalIdLoadAccess byNaturalId(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> NaturalIdLoadAccess<T> byNaturalId(Class<T> type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SimpleNaturalIdLoadAccess bySimpleNaturalId(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> SimpleNaturalIdLoadAccess<T> bySimpleNaturalId(Class<T> type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Filter enableFilter(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Filter getEnabledFilter(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void disableFilter(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SessionStatistics getStatistics() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isReadOnly(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setReadOnly(Object o, boolean bln) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void doWork(Work work) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T doReturningWork(ReturningWork<T> rw) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> RootGraph<T> createEntityGraph(Class<T> type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RootGraph<?> createEntityGraph(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RootGraph<?> getEntityGraph(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Connection disconnect() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void reconnect(Connection cnctn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isFetchProfileEnabled(String string) throws UnknownProfileException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void enableFetchProfile(String string) throws UnknownProfileException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void disableFetchProfile(String string) throws UnknownProfileException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TypeHelper getTypeHelper() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LobHelper getLobHelper() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addEventListeners(SessionEventListener... sls) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> org.hibernate.query.Query<T> createQuery(CriteriaQuery<T> cq) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public org.hibernate.query.Query createQuery(CriteriaUpdate cu) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public org.hibernate.query.Query createQuery(CriteriaDelete cd) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> org.hibernate.query.Query<T> createNamedQuery(String string, Class<T> type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public NativeQuery createSQLQuery(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getTenantIdentifier() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isOpen() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isConnected() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Transaction getTransaction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public org.hibernate.query.Query createQuery(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public org.hibernate.query.Query getNamedQuery(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProcedureCall getNamedProcedureCall(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProcedureCall createStoredProcedureCall(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProcedureCall createStoredProcedureCall(String string, Class... types) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProcedureCall createStoredProcedureCall(String string, String... strings) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Criteria createCriteria(Class type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Criteria createCriteria(Class type, String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Criteria createCriteria(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Criteria createCriteria(String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer getJdbcBatchSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setJdbcBatchSize(Integer intgr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public org.hibernate.query.Query createNamedQuery(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public NativeQuery createNativeQuery(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public NativeQuery createNativeQuery(String string, Class type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public NativeQuery createNativeQuery(String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public NativeQuery getNamedNativeQuery(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object merge(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T find(Class<T> type, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T find(Class<T> type, Object o, Map<String, Object> map) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T find(Class<T> type, Object o, LockModeType lmt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T find(Class<T> type, Object o, LockModeType lmt, Map<String, Object> map) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T getReference(Class<T> type, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFlushMode(FlushModeType fmt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void lock(Object o, LockModeType lmt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void lock(Object o, LockModeType lmt, Map<String, Object> map) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void refresh(Object o, Map<String, Object> map) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void refresh(Object o, LockModeType lmt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void refresh(Object o, LockModeType lmt, Map<String, Object> map) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void detach(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LockModeType getLockMode(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setProperty(String string, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<String, Object> getProperties() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public StoredProcedureQuery createNamedStoredProcedureQuery(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String string, Class... types) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String string, String... strings) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void joinTransaction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isJoinedToTransaction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T unwrap(Class<T> type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getDelegate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Metamodel getMetamodel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Session getSession() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
