package medicaldoctor.entities;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import medicaldoctor.core.DatabaseScope;
import org.hibernate.query.Query;

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

    @Column(name = "Age", nullable = true)
    private Integer age;

    @Column(name = "Sex", nullable = true, length = 10)
    private String sex;

    @Column(name = "DateOfBirth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "AddressStreet", nullable = true, length = 256)
    private String addressStreet;

    @Column(name = "AddressCity", nullable = true, length = 256)
    private String addressCity;

    @Column(name = "AddressState", nullable = true, length = 10)
    private String addressState;

    @Column(name = "AddressZipCode", nullable = true, length = 30)
    private String addressZipCode;

    @Column(name = "BillingAddressStreet", nullable = true, length = 256)
    private String billingAddressStreet;

    @Column(name = "BillingAddressCity", nullable = true, length = 256)
    private String billingAddressCity;

    @Column(name = "BillingAddressState", nullable = true, length = 10)
    private String billingAddressState;

    @Column(name = "BillingAddressZipCode", nullable = true, length = 30)
    private String billingAddressZipCode;

    @Column(name = "SocialSecurityNumber", nullable = true, length = 128)
    private String socialSecurityNumber;

    @Column(name = "MedicalInsurance", nullable = true, length = 128)
    private String medicalInsurance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PrimaryDoctorUserId", nullable = true)
    private User primaryDoctor;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PatientId")
    private List<PatientVisit> visits = new LinkedList<>();

    public Patient() {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressZipCode() {
        return addressZipCode;
    }

    public void setAddressZipCode(String addressZipCode) {
        this.addressZipCode = addressZipCode;
    }

    public String getBillingAddressStreet() {
        return billingAddressStreet;
    }

    public void setBillingAddressStreet(String billingAddressStreet) {
        this.billingAddressStreet = billingAddressStreet;
    }

    public String getBillingAddressCity() {
        return billingAddressCity;
    }

    public void setBillingAddressCity(String billingAddressCity) {
        this.billingAddressCity = billingAddressCity;
    }

    public String getBillingAddressState() {
        return billingAddressState;
    }

    public void setBillingAddressState(String billingAddressState) {
        this.billingAddressState = billingAddressState;
    }

    public String getBillingAddressZipCode() {
        return billingAddressZipCode;
    }

    public void setBillingAddressZipCode(String billingAddressZipCode) {
        this.billingAddressZipCode = billingAddressZipCode;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getMedicalInsurance() {
        return medicalInsurance;
    }

    public void setMedicalInsurance(String medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    public User getPrimaryDoctor() {
        return primaryDoctor;
    }

    public void setPrimaryDoctor(User primaryDoctor) {
        this.primaryDoctor = primaryDoctor;
    }

    public List<PatientVisit> getVisits() {
        return visits;
    }

    public void addVisit(PatientVisit visit) {
        this.visits.add(visit);
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public static Patient byId(int id) {
        Query<Patient> q = DatabaseScope._getSession()
                .createQuery("FROM Patient WHERE Id = :id", Patient.class);
        q.setParameter("id", id);
        return q.uniqueResult();
    }

    public static List<Patient> getAll() {
        Query<Patient> q = DatabaseScope._getSession()
                .createQuery("FROM Patient", Patient.class);
        return q.list();
    }

}
