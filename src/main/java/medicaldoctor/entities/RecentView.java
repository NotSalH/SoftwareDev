package medicaldoctor.entities;

import java.time.LocalDateTime;
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
import medicaldoctor.core.RecentViewType;

@Entity
@Table(name = "RecentView", uniqueConstraints = {
    @UniqueConstraint(columnNames = "Id")})
@SuppressWarnings("PersistenceUnitPresent")
public class RecentView extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ViewedUserId", nullable = true)
    private User viewedUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ViewedPatientId", nullable = true)
    private Patient viewedPatient;

    @Enumerated(EnumType.ORDINAL)
    private RecentViewType recentViewType;

    @Column(name = "ViewDateTime", nullable = false)
    private LocalDateTime viewDateTime;

    public RecentView() {
        // hibernate
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getViewedUser() {
        return viewedUser;
    }

    public void setViewedUser(User viewedUser) {
        this.viewedUser = viewedUser;
    }

    public Patient getViewedPatient() {
        return viewedPatient;
    }

    public void setViewedPatient(Patient viewedPatient) {
        this.viewedPatient = viewedPatient;
    }

    public RecentViewType getRecentViewType() {
        return recentViewType;
    }

    public void setRecentViewType(RecentViewType recentViewType) {
        this.recentViewType = recentViewType;
    }

    public LocalDateTime getViewDateTime() {
        return viewDateTime;
    }

    public void setViewDateTime(LocalDateTime viewDateTime) {
        this.viewDateTime = viewDateTime;
    }

}
