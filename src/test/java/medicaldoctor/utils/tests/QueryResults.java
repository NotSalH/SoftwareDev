package medicaldoctor.utils.tests;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import medicaldoctor.entities.AbstractEntity;

public class QueryResults {

    public LinkedList<AbstractEntity> items;
    public Queue<QueryFunc> queries;
    public HashMap<String, Object> params;

    public QueryResults() {
        items = new LinkedList();
        queries = new LinkedList();
        params = new HashMap();
    }

    public QueryResults addRecord(AbstractEntity o) {
        items.add(o);
        return this;
    }

    public QueryResults addQuery(QueryFunc f) {
        queries.add(f);
        return this;
    }

    public QueryResults clearParams() {
        params.clear();
        return this;
    }

    public QueryResults setParam(String key, Object o) {
        params.put(key, o);
        return this;
    }

    public Object getParam(String key) {
        return params.get(key);
    }

}
