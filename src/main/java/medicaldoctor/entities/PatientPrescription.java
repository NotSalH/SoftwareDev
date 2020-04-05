package medicaldoctor.entities;

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

@Entity
@Table(name = "PatientPrescription", uniqueConstraints = {
    @UniqueConstraint(columnNames = "Id")})
@SuppressWarnings("PersistenceUnitPresent")
public class PatientPrescription extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "Prescription", nullable = true, length = 1024)
    private String prescription;

    @Column(name = "Instructions", nullable = true, length = 65535, columnDefinition = "Text")
    private String instructions;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PharmacyDetailsId", nullable = false)
    private PharmacyDetails pharmacyDetails = new PharmacyDetails();

    public PatientPrescription() {
        // hibernate
    }

    public Integer getId() {
        return id;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public PharmacyDetails getPharmacy() {
        return pharmacyDetails;
    }

}
