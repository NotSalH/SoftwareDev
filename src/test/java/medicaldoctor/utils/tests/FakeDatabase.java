package medicaldoctor.utils.tests;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import medicaldoctor.core.DatabaseScope;
import medicaldoctor.entities.AbstractEntity;
import medicaldoctor.entities.LogRecord;
import medicaldoctor.entities.UserType;

/**
 * Setup Testing Database.
 */
public class FakeDatabase {

    public LinkedList<AbstractEntity> items;
    public Queue<QueryFunc> queries;
    public HashMap<String, Object> params;
    public LinkedList<Object> savedEntities;

    public FakeDatabase() {
        items = new LinkedList();
        queries = new LinkedList();
        params = new HashMap();
        savedEntities = new LinkedList();
        DatabaseScope._overrideSessionFactory(new FakeSessionFactory(this));

        for (UserType userType : UserType.ALL) {
            items.add(userType);
        }
    }

    /**
     * Set up database for testing with a single query, and a list of records in
     * the database.
     *
     * @param func the single query
     * @param records the records in the test database
     */
    public FakeDatabase(AbstractEntity... records) {
        this();
        for (AbstractEntity record : records) {
            addRecord(record);
        }
    }

    /**
     * Set up database for testing with a single query, and a list of records in
     * the database.
     *
     * @param func the single query
     * @param records the records in the test database
     */
    public FakeDatabase(QueryFunc func, AbstractEntity... records) {
        this(func, 1, records);
    }

    /**
     * Set up database for testing with a single query, and a list of records in
     * the database.
     *
     * @param func the query
     * @param repeatQuery number of times query should be run
     * @param records the records in the test database
     */
    public FakeDatabase(QueryFunc func, int queryCount, AbstractEntity... records) {
        this(records);
        for (int i = 0; i < queryCount; i++) {
            addQuery(func);
        }
    }

    public FakeDatabase addRecord(AbstractEntity o) {
        items.add(o);
        return this;
    }

    public FakeDatabase addQuery(QueryFunc f) {
        addQuery(f, 1);
        return this;
    }

    public FakeDatabase addQuery(QueryFunc f, int queryCount) {
        for (int i = 0; i < queryCount; i++) {
            queries.add(f);
        }
        return this;
    }

    public FakeDatabase clearParams() {
        params.clear();
        return this;
    }

    public FakeDatabase setParam(String key, Object o) {
        params.put(key, o);
        return this;
    }

    public Object getParam(String key) {
        return params.get(key);
    }

    public Object[] getSavedEntitiesOfType(Class check) {
        return savedEntities
                .stream()
                .filter(x -> x.getClass().equals(check))
                .toArray();
    }

    public int getLoggedEventsCount() {
        return getSavedEntitiesOfType(LogRecord.class).length;
    }

}
