package medicaldoctor.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Patient", uniqueConstraints = {
    @UniqueConstraint(columnNames = "Id")})
@SuppressWarnings("PersistenceUnitPresent")
public class Patient extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Integer id;
    
    @Column(name = "FirstName", nullable = false, length = 100)
    private String firstName;

    @Column(name = "LastName", nullable = false, length = 100)
    private String lastName;
    
    @Column(name = "Age", length = 100)
    private Integer age;
    
    
    public int getAge(){
        return age;
    }
    
    public String getName(){
        return firstName + " " + lastName; 
    }

}
