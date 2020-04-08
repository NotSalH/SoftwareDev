package medicaldoctor.entities;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import medicaldoctor.core.DatabaseScope;
import medicaldoctor.core.Permission;
import medicaldoctor.core.RecentViewType;
import medicaldoctor.util.HashAndSalt;
import org.hibernate.query.Query;

@Entity
@Table(name = "User", uniqueConstraints = {
    @UniqueConstraint(columnNames = "Id")
    , @UniqueConstraint(columnNames = "UserName")})
@SuppressWarnings("PersistenceUnitPresent")
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "FirstName", nullable = false, length = 100)
    private String firstName;

    @Column(name = "LastName", nullable = false, length = 100)
    private String lastName;

    @Column(name = "UserName", nullable = false, length = 50, unique = true)
    private String userName;

    @Column(name = "PasswordHash", nullable = false, length = 128)
    private String passwordHash;

    @Column(name = "PasswordSalt", nullable = false, length = 128)
    private String passwordSalt;

    @Column(name = "AdditionalPasswordHash", nullable = true, length = 128)
    private String additionalPasswordHash;

    @Column(name = "AdditionalPasswordSalt", nullable = true, length = 128)
    private String additionalPasswordSalt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserTypeId", nullable = false)
    private UserType userType;

    @Column(name = "Department", length = 100)
    private String department;

    @Column(name = "OfficeNum", length = 100)
    private Integer officeNum;

    public User() {
        // hibernate
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public HashAndSalt getPasswordHashAndSalt() {
        return new HashAndSalt(passwordHash, passwordSalt);
    }

    public void setPasswordHashAndSalt(HashAndSalt hashAndSalt) {
        this.passwordHash = hashAndSalt.getHash();
        this.passwordSalt = hashAndSalt.getSalt();
    }

    public HashAndSalt getAdditionalPasswordHashAndSalt() {
        return new HashAndSalt(additionalPasswordHash, additionalPasswordSalt);
    }

    public void setAdditionalPasswordHashAndSalt(HashAndSalt hashAndSalt) {
        this.additionalPasswordHash = hashAndSalt.getHash();
        this.additionalPasswordSalt = hashAndSalt.getSalt();
    }

    public boolean hasAdditionalPassword() {
        return this.additionalPasswordHash != null;
    }

    public void setType(UserType userType) {
        this.userType = userType;
    }

    public UserType getType() {
        return userType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getOfficeNum() {
        return officeNum;
    }

    public void setOfficeNum(Integer officeNum) {
        this.officeNum = officeNum;
    }

    public boolean hasPermission(Permission permission) {
        return getType().hasPermission(permission);
    }

    public List<User> getRecentlyViewedDoctors() {
        return getRecentlyViewedObjects(x -> x.getViewedUser(), RecentViewType.VIEWED_DOCTOR);
    }

    public List<Patient> getRecentlyViewedPatients() {
        return getRecentlyViewedObjects(x -> x.getViewedPatient(), RecentViewType.VIEWED_PATIENT);
    }

    public List<Patient> getRecentlyRegisteredPatients() {
        return getRecentlyViewedObjects(x -> x.getViewedPatient(), RecentViewType.REGISTERED_PATIENT);
    }

    private <R> List<R> getRecentlyViewedObjects(
            Function<? super RecentView, ? extends R> mapper,
            RecentViewType recentViewType) {
        Query<RecentView> q = DatabaseScope._getSession()
                .createQuery("FROM RecentView "
                        + "WHERE UserId = :userid AND RecentViewType = :type "
                        + "ORDER BY ViewDateTime DESC", RecentView.class);
        q.setParameter("userid", this.getId());
        q.setParameter("type", recentViewType.ordinal());
        return q.getResultStream().map(mapper).collect(Collectors.toList());
    }

    public static User byUsername(String username) {
        Query<User> q = DatabaseScope._getSession()
                .createQuery("FROM User WHERE UserName = :username", User.class);
        q.setParameter("username", username);
        return q.uniqueResult();
    }

}
