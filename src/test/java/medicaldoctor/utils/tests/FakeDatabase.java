package medicaldoctor.utils.tests;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import medicaldoctor.core.DatabaseScope;
import medicaldoctor.entities.AbstractEntity;

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
    }

    /**
     * Set up database for testing with a single query, and a list of records in
     * the database.
     *
     * @param func the single query
     * @param records the records in the test database
     */
    public FakeDatabase(QueryFunc func, AbstractEntity... records) {
        for (AbstractEntity record : records) {
            addRecord(record);
        }
        addQuery(func);
        DatabaseScope._overrideSessionFactory(new FakeSessionFactory(this));
    }

    public FakeDatabase addRecord(AbstractEntity o) {
        items.add(o);
        return this;
    }

    public FakeDatabase addQuery(QueryFunc f) {
        queries.add(f);
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

}
