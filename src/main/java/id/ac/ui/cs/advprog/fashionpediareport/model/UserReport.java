package id.ac.ui.cs.advprog.fashionpediareport.model;

import jakarta.persistence.*;
import id.ac.ui.cs.advprog.fashionpediareport.enums.ReportStatus;
import id.ac.ui.cs.advprog.fashionpediareport.status.UserReportState;
import id.ac.ui.cs.advprog.fashionpediareport.status.UserApprovedState;
import id.ac.ui.cs.advprog.fashionpediareport.status.UserPendingState;
import id.ac.ui.cs.advprog.fashionpediareport.status.UserRejectedState;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "user_report")
public class UserReport{

    @Id
    String id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "reported_user_id", nullable = false)
    String reportedUserId;

    @Column(nullable = false)
    String alasan;

    @Column(name= "report_status",nullable = false)
    ReportStatus status;

    @Column(nullable = false)
    LocalDate date;

    @JsonIgnore
    @Column(name="state", nullable = false)
    private String stateStr;

    @Transient
    @JsonIgnore
    UserReportState state;
    
    public UserReport(String userId, String reportedUserId,String alasan){
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.reportedUserId = reportedUserId;
        this.alasan = alasan;
        this.status = ReportStatus.PENDING;
        this.date = LocalDate.now();
        this.state = new UserPendingState();
        this.stateStr = ReportStatus.PENDING.toString();
    }

    public UserReport() {

    }

    public void approve(){
        state.approve(this);
        this.stateStr = "APPROVED";
    }

    public void reject(){
        state.reject(this);
        this.stateStr = "REJECTED";
    }

    @PostLoad
    public void postLoad() {
        switch (stateStr) {
            case "APPROVED":
                this.state = new UserApprovedState();
                break;
            case "REJECTED":
                this.state = new UserRejectedState();
                break;
            default:
                this.state = new UserPendingState();
                break;
        }
    }
}
