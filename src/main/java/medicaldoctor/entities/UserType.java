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
import medicaldoctor.core.Permission;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "UserTypeId", nullable = false)
    private List<UserTypePermission> permissions = new LinkedList<>();

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
    
    public String getDashboardName(){
        if(this.equals(UserType.DOCTOR)){
            return LookUp.DOCTOR_DASHBOARD;
        }
        else if(this.equals(UserType.NURSE)){
            return LookUp.NURSE_DASHBOARD;
        }
        else if(this.equals(UserType.HEMATOLOGIC_LAB_WORKER)){
            return LookUp.LAB_WORKER;
        }
        else if(this.equals(UserType.RADIOLOGIC_LAB_WORKER)){
            return LookUp.LAB_WORKER;
        }
        else if(this.equals(UserType.STAFF)){
            return LookUp.STAFF_DASHBOARD;
        }
        else 
            return LookUp.LOGIN_SCREEN;
    }

}
