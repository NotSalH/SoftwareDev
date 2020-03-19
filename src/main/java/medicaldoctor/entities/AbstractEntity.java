package medicaldoctor.entities;

import java.io.Serializable;
import medicaldoctor.core.DatabaseScope;

/**
 * Common operations between entities.
 */
public abstract class AbstractEntity implements Serializable {

    /**
     * Persist the runtime object to the database.
     */
    public void save() {
        DatabaseScope._getSession().save(this);
    }

}
