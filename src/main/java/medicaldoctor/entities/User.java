package medicaldoctor.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import medicaldoctor.core.DatabaseScope;
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

    @Column(name = "AdditionalPasswordHash", nullable = false, length = 128)
    private String additionalPasswordHash;

    @Column(name = "AdditionalPasswordSalt", nullable = false, length = 128)
    private String additionalPasswordSalt;

    public User() {
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

    public static User byUsername(String username) {
        Query<User> q = DatabaseScope._getSession()
                .createQuery("FROM User WHERE UserName = :username", User.class);
        q.setParameter("username", username);
        return q.uniqueResult();
    }

}
