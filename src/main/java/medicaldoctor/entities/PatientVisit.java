package medicaldoctor.entities;

import java.time.LocalDateTime;
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

@Entity
@Table(name = "PatientVisit", uniqueConstraints = {
    @UniqueConstraint(columnNames = "Id")})
@SuppressWarnings("PersistenceUnitPresent")
public class PatientVisit extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PatientVisitId")
    private List<PatientLabRecord> labRecords = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PatientVisitId")
    private List<PatientPrescription> prescriptions = new LinkedList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DoctorUserId", nullable = false)
    private User doctor;

    @Column(name = "VisitDateTime", nullable = false)
    private LocalDateTime visitDateTime;

    @Column(name = "ChiefComplaint", nullable = true, length = 65535, columnDefinition = "Text")
    private String chiefComplaint;

    @Column(name = "PresentIllness", nullable = true, length = 65535, columnDefinition = "Text")
    private String presentIllness;

    @Column(name = "Symptoms", nullable = true, length = 65535, columnDefinition = "Text")
    private String symptoms;

    @Column(name = "PhysicalExamNotes", nullable = true, length = 65535, columnDefinition = "Text")
    private String physicalExamNotes;

    @Column(name = "Diagnosis", nullable = true, length = 65535, columnDefinition = "Text")
    private String diagnosis;

    @Column(name = "Impression", nullable = true, length = 65535, columnDefinition = "Text")
    private String impression;

    @Column(name = "AdditionalNotes", nullable = true, length = 65535, columnDefinition = "Text")
    private String additionalNotes;

    public PatientVisit() {
        // hibernate
    }

    public Integer getId() {
        return id;
    }

    public List<PatientLabRecord> getLabRecords() {
        return labRecords;
    }

    public void addLabRecord(PatientLabRecord labRecord) {
        this.labRecords.add(labRecord);
    }

    public List<PatientPrescription> getPrescriptions() {
        return prescriptions;
    }

    public void addPrescription(PatientPrescription prescription) {
        this.prescriptions.add(prescription);
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getVisitDateTime() {
        return visitDateTime;
    }

    public void setVisitDateTime(LocalDateTime visitDateTime) {
        this.visitDateTime = visitDateTime;
    }

    public String getChiefComplaint() {
        return chiefComplaint;
    }

    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint;
    }

    public String getPresentIllness() {
        return presentIllness;
    }

    public void setPresentIllness(String presentIllness) {
        this.presentIllness = presentIllness;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getPhysicalExamNotes() {
        return physicalExamNotes;
    }

    public void setPhysicalExamNotes(String physicalExamNotes) {
        this.physicalExamNotes = physicalExamNotes;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getImpression() {
        return impression;
    }

    public void setImpression(String impression) {
        this.impression = impression;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

}
