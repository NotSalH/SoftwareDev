package medicaldoctor.utils.tests;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Query;
import javax.persistence.SynchronizationType;
import javax.persistence.criteria.CriteriaBuilder;
import org.hibernate.Cache;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.StatelessSessionBuilder;
import org.hibernate.TypeHelper;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.engine.spi.FilterDefinition;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.Statistics;

public class FakeSessionFactory implements SessionFactory {

    public QueryResults results;

    private boolean isClosed = false;

    public FakeSessionFactory(QueryResults results) {
        this.results = results;
    }

    @Override
    public Session openSession() throws HibernateException {
        return new FakeSession(results);
    }

    @Override
    public void close() throws HibernateException {
        isClosed = true;
    }

    @Override
    public boolean isClosed() {
        return isClosed;
    }

    ////////////////////////////////////////////////////////////////////////////
    @Override
    public SessionFactoryOptions getSessionFactoryOptions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SessionBuilder withOptions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Session getCurrentSession() throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public StatelessSessionBuilder withStatelessOptions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public StatelessSession openStatelessSession() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public StatelessSession openStatelessSession(Connection cnctn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Statistics getStatistics() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Cache getCache() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set getDefinedFilterNames() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public FilterDefinition getFilterDefinition(String string) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean containsFetchProfileDefinition(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TypeHelper getTypeHelper() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ClassMetadata getClassMetadata(Class type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ClassMetadata getClassMetadata(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CollectionMetadata getCollectionMetadata(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<String, ClassMetadata> getAllClassMetadata() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map getAllCollectionMetadata() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EntityManager createEntityManager() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EntityManager createEntityManager(Map map) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EntityManager createEntityManager(SynchronizationType st) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EntityManager createEntityManager(SynchronizationType st, Map map) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isOpen() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<String, Object> getProperties() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PersistenceUnitUtil getPersistenceUnitUtil() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addNamedQuery(String string, Query query) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T unwrap(Class<T> type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> void addNamedEntityGraph(String string, EntityGraph<T> eg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> List<EntityGraph<? super T>> findEntityGraphsByType(Class<T> type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Metamodel getMetamodel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Reference getReference() throws NamingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
