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
@Table(name = "Patient", uniqueConstraints = {
    @UniqueConstraint(columnNames = "Id")
    , @UniqueConstraint(columnNames = "Full Name")})
@SuppressWarnings("PersistenceUnitPresent")

public class Patient extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "FirstName", nullable = false, length = 100)
    private String firstName;

    @Column(name = "LastName", nullable = false, length = 100)
    private String lastName;

    @Column(name = "Age", nullable = false, length = 50, unique = true)
    private Integer age;

    @Column(name = "Adress", nullable = false, length = 128)
    private String adress;

    @Column(name = "Social", nullable = false, length = 128)
    private String social;

    @Column(name = "isPrior", nullable = false, length = 128)
    private Boolean isPrior;
    
    public Patient() {
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
    
    public Integer getAge(){
        return this.age;
    }
    
    public void setAge(int age){
        this.age = age;
    }
    
    public String getAddress(){
        return this.adress;
    }
    
    public void setAddress(String adress){
        this.adress = adress;
    }
    
    public String getSocial(){
        return this.social;
    }
    
    public void setSocial(String social){
        this.social = social;
    }
    
    public Boolean getPrior(){
        return this.isPrior;
    }
    
    public void setIsPrior(boolean prior){
        this.isPrior = prior;
    }
    
}
