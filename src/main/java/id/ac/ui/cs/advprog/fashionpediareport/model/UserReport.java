package id.ac.ui.cs.advprog.fashionpediareport.model;

import jakarta.persistence.*;
import id.ac.ui.cs.advprog.fashionpediareport.enums.ReportStatus;
import id.ac.ui.cs.advprog.fashionpediareport.status.UserReportState;
import id.ac.ui.cs.advprog.fashionpediareport.status.UserPendingState;

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
    }

    public UserReport() {

    }

    public void approve(){
        state.approve(this);
    }

    public void reject(){
        state.reject(this);
    }
}
