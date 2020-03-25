package medicaldoctor.utils.tests;

import medicaldoctor.core.DatabaseScope;
import medicaldoctor.entities.AbstractEntity;

/**
 * Setup Testing Database.
 */
public class FakeDatabase {

    private FakeDatabase() {
    }

    public static QueryResults results;

    public static void setDatabase(QueryResults results) {
        FakeDatabase.results = results;
        DatabaseScope._overrideSessionFactory(new FakeSessionFactory(results));
    }

    /**
     * Set up database for testing with a single query, and a list of records in
     * the database.
     *
     * @param func the single query
     * @param records the records in the test database
     */
    public static void setDatabase(QueryFunc func, AbstractEntity... records) {
        QueryResults results = new QueryResults();
        for (AbstractEntity record : records) {
            results.addRecord(record);
        }
        results.addQuery(func);
        setDatabase(results);
    }

}
