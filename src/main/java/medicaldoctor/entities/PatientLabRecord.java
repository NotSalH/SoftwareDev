package medicaldoctor.entities;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import medicaldoctor.core.DatabaseScope;
import medicaldoctor.core.LabTestStatus;
import medicaldoctor.core.LabTestType;
import medicaldoctor.core.LabType;
import org.hibernate.query.Query;

@Entity
@Table(name = "PatientLabRecord", uniqueConstraints = {
    @UniqueConstraint(columnNames = "Id")})
@SuppressWarnings("PersistenceUnitPresent")
public class PatientLabRecord extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LabWorkerUserId", nullable = true)
    private User labWorker;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RequesterUserId", nullable = false)
    private User requester;

    @Enumerated(EnumType.ORDINAL)
    private LabType labType;

    @Enumerated(EnumType.ORDINAL)
    private LabTestType labTestType;

    @Enumerated(EnumType.ORDINAL)
    private LabTestStatus labTestStatus;

    @Column(name = "Result", nullable = true, length = 65535, columnDefinition = "Text")
    private String result;

    @Column(name = "RequestDateTime", nullable = true)
    private LocalDateTime requestDateTime;

    @Column(name = "ResultDateTime", nullable = true)
    private LocalDateTime resultDateTime;

    public PatientLabRecord() {
        // hibernate
    }

    public Integer getId() {
        return id;
    }

    public User getLabWorker() {
        return labWorker;
    }

    public void setLabWorker(User labWorker) {
        this.labWorker = labWorker;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public LabType getLabType() {
        return labType;
    }

    public LabTestType getLabTestType() {
        return labTestType;
    }

    public void setLabTestType(LabTestType labTestType) {
        this.labTestType = labTestType;
        this.labType = labTestType.getLabType();
    }

    public LabTestStatus getLabTestStatus() {
        return labTestStatus;
    }

    public void setLabTestStatus(LabTestStatus labTestStatus) {
        this.labTestStatus = labTestStatus;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(LocalDateTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public LocalDateTime getResultDateTime() {
        return resultDateTime;
    }

    public void setResultDateTime(LocalDateTime resultDateTime) {
        this.resultDateTime = resultDateTime;
    }

    public static List<PatientLabRecord> getRecentLabRecords(LabType labType) {
        Query<PatientLabRecord> q = DatabaseScope._getSession()
                .createQuery("FROM PatientLabRecord "
                        + "WHERE LabType = :labtype"
                        + "ORDER BY RequestDateTime DESC", PatientLabRecord.class);
        q.setParameter("labtype", labType.ordinal());
        return q.getResultList();
    }

}
