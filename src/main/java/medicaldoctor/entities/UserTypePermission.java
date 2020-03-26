package medicaldoctor.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import medicaldoctor.core.Permission;

@Entity
@Table(name = "UserTypePermission", uniqueConstraints = {
    @UniqueConstraint(columnNames = "Id")})
@SuppressWarnings("PersistenceUnitPresent")
public class UserTypePermission extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Integer id;

    @Enumerated(EnumType.ORDINAL)
    private Permission permission;

    public UserTypePermission() {
        // hibernate
    }

    public UserTypePermission(Permission permission) {
        this.permission = permission;
    }

    public int getId() {
        return id;
    }

    public Permission getPermission() {
        return permission;
    }

}
