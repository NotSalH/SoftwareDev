package medicaldoctor.entities;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import medicaldoctor.controllers.LookUp;
import medicaldoctor.core.DatabaseScope;
import medicaldoctor.core.Permission;
import org.hibernate.query.Query;

@Entity
@Table(name = "UserType", uniqueConstraints = {
    @UniqueConstraint(columnNames = "Id")
    , @UniqueConstraint(columnNames = "Name")})
@SuppressWarnings("PersistenceUnitPresent")
public class UserType extends AbstractEntity {

    public static final UserType ADMIN = new UserType("Network Admin", true, LookUp.ADMIN_DASHBOARD);
    public static final UserType EXECUTIVE = new UserType("Executive", true, LookUp.ADMIN_DASHBOARD);
    public static final UserType STAFF = new UserType("Staff", false, LookUp.STAFF_DASHBOARD);
    public static final UserType NURSE = new UserType("Nurse", false, LookUp.NURSE_DASHBOARD);
    public static final UserType DOCTOR = new UserType("Doctor", false, LookUp.DOCTOR_DASHBOARD);
    public static final UserType RADIOLOGIC_LAB_WORKER = new UserType("Radiologic Lab Worker", false, LookUp.LAB_WORKER);
    public static final UserType HEMATOLOGIC_LAB_WORKER = new UserType("Hematologic Lab Worker", false, LookUp.LAB_WORKER);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "Name", nullable = false, length = 50, unique = true)
    private String name;

    @Column(name = "dashboardName", nullable = false, length = 50)
    private String dashboardName;

    @Column(name = "HasAdditionalPassword", nullable = false)
    private boolean hasAdditionalPassword;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "UserTypeId", nullable = false)
    private List<UserTypePermission> permissions = new LinkedList<>();

    public UserType() {
        // hibernate
    }

    public UserType(String name, boolean hasAdditionalPassword, String dashboardName) {
        this.name = name;
        this.hasAdditionalPassword = hasAdditionalPassword;
        this.dashboardName = dashboardName;
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

    public String getDashboardName() {
        return this.dashboardName;
    }

    public void addPermission(Permission permission) {
        permissions.add(new UserTypePermission(permission));
    }

    public boolean hasPermission(Permission permission) {
        for (UserTypePermission check : permissions) {
            if (check.getPermission() == permission) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserType other = (UserType) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    public static UserType byName(String usertype) {
        Query<UserType> q = DatabaseScope._getSession()
                .createQuery("FROM UserType WHERE Name = :usertype", UserType.class);
        q.setParameter("usertype", usertype);
        return q.uniqueResult();
    }

}
