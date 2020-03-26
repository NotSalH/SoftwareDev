package medicaldoctor.utils.tests;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Parameter;
import javax.persistence.TemporalType;
import medicaldoctor.entities.AbstractEntity;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.engine.spi.RowSelection;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.graph.RootGraph;
import org.hibernate.query.ParameterMetadata;
import org.hibernate.query.Query;
import org.hibernate.query.QueryParameter;
import org.hibernate.query.QueryProducer;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;

public class FakeQuery<R> implements org.hibernate.query.Query<R> {

    public FakeDatabase db;

    public FakeQuery(FakeDatabase db) {
        this.db = db;
    }

    @Override
    public Query<R> setParameter(String string, Object o) {
        db.setParam(string, o);
        return this;
    }

    @Override
    public R uniqueResult() {
        R r = null;
        QueryFunc f = db.queries.remove();
        for (AbstractEntity x : db.items) {
            if (f.call(x, db)) {
                r = (R) x;
                break;
            }
        }
        db.clearParams();
        return r;
    }

    ////////////////////////////////////////////////////////////////////////////
    @Override
    public QueryProducer getProducer() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<R> uniqueResultOptional() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Stream<R> stream() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> applyGraph(RootGraph rg, GraphSemantic gs) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameter(Parameter<Instant> prmtr, Instant instnt, TemporalType tt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameter(Parameter<LocalDateTime> prmtr, LocalDateTime ldt, TemporalType tt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameter(Parameter<ZonedDateTime> prmtr, ZonedDateTime zdt, TemporalType tt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameter(Parameter<OffsetDateTime> prmtr, OffsetDateTime odt, TemporalType tt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameter(String string, Instant instnt, TemporalType tt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameter(String string, LocalDateTime ldt, TemporalType tt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameter(String string, ZonedDateTime zdt, TemporalType tt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameter(String string, OffsetDateTime odt, TemporalType tt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameter(int i, Instant instnt, TemporalType tt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameter(int i, LocalDateTime ldt, TemporalType tt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameter(int i, ZonedDateTime zdt, TemporalType tt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameter(int i, OffsetDateTime odt, TemporalType tt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ScrollableResults scroll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ScrollableResults scroll(ScrollMode sm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<R> list() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public FlushMode getHibernateFlushMode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CacheMode getCacheMode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getCacheRegion() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer getFetchSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LockOptions getLockOptions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getComment() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getQueryString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ParameterMetadata getParameterMetadata() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setMaxResults(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setFirstResult(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setHint(String string, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> Query<R> setParameter(Parameter<T> prmtr, T t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameter(Parameter<Calendar> prmtr, Calendar clndr, TemporalType tt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameter(Parameter<Date> prmtr, Date date, TemporalType tt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameter(String string, Object o, Type type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameter(String string, Calendar clndr, TemporalType tt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameter(String string, Date date, TemporalType tt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameter(int i, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameter(int i, Calendar clndr, TemporalType tt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameter(int i, Date date, TemporalType tt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> Query<R> setParameter(QueryParameter<T> qp, T t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <P> Query<R> setParameter(int i, P p, TemporalType tt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <P> Query<R> setParameter(QueryParameter<P> qp, P p, Type type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameter(int i, Object o, Type type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <P> Query<R> setParameter(QueryParameter<P> qp, P p, TemporalType tt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <P> Query<R> setParameter(String string, P p, TemporalType tt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setFlushMode(FlushModeType fmt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setLockMode(LockModeType lmt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setReadOnly(boolean bln) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setHibernateFlushMode(FlushMode fm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setCacheMode(CacheMode cm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setCacheable(boolean bln) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setCacheRegion(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setTimeout(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setFetchSize(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setLockOptions(LockOptions lo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setLockMode(String string, LockMode lm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setComment(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> addQueryHint(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <P> Query<R> setParameterList(QueryParameter<P> qp, Collection<P> clctn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameterList(String string, Collection clctn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameterList(String string, Collection clctn, Type type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameterList(String string, Object[] os, Type type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setParameterList(String string, Object[] os) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setProperties(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setProperties(Map map) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setEntity(int i, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setEntity(String string, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Query<R> setResultTransformer(ResultTransformer rt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int executeUpdate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getMaxResults() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getFirstResult() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<String, Object> getHints() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Parameter<?>> getParameters() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Parameter<?> getParameter(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> Parameter<T> getParameter(String string, Class<T> type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Parameter<?> getParameter(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> Parameter<T> getParameter(int i, Class<T> type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isBound(Parameter<?> prmtr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T getParameterValue(Parameter<T> prmtr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getParameterValue(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getParameterValue(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public FlushModeType getFlushMode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LockModeType getLockMode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T unwrap(Class<T> type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RowSelection getQueryOptions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isCacheable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer getTimeout() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isReadOnly() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Type[] getReturnTypes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<R> iterate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String[] getNamedParameters() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public org.hibernate.Query<R> setParameterList(int i, Collection clctn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public org.hibernate.Query<R> setParameterList(int i, Collection clctn, Type type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public org.hibernate.Query<R> setParameterList(int i, Object[] os, Type type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public org.hibernate.Query<R> setParameterList(int i, Object[] os) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Type determineProperBooleanType(int i, Object o, Type type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Type determineProperBooleanType(String string, Object o, Type type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String[] getReturnAliases() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
