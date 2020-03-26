package medicaldoctor.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name = "UserType", uniqueConstraints = {
    @UniqueConstraint(columnNames = "Id")
    , @UniqueConstraint(columnNames = "Name")})
@SuppressWarnings("PersistenceUnitPresent")
public class UserType extends AbstractEntity {

    public static final UserType ADMIN = new UserType("Network Admin", true);
    public static final UserType EXECUTIVE = new UserType("Executive", true);
    public static final UserType STAFF = new UserType("Staff", false);
    public static final UserType NURSE = new UserType("Nurse", false);
    public static final UserType DOCTOR = new UserType("Doctor", false);
    public static final UserType RADIOLOGIC_LAB_WORKER = new UserType("Radiologic Lab Worker", false);
    public static final UserType HEMATOLOGIC_LAB_WORKER = new UserType("Hematologic Lab Worker", false);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "Name", nullable = false, length = 50, unique = true)
    private String name;

    @Column(name = "HasAdditionalPassword", nullable = false)
    private boolean hasAdditionalPassword;

    public UserType() {
        // hibernate
    }

    public UserType(String name, boolean hasAdditionalPassword) {
        this.name = name;
        this.hasAdditionalPassword = hasAdditionalPassword;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean hasAdditionalPassword() {
        return hasAdditionalPassword;
    }

}
