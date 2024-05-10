package id.ac.ui.cs.advprog.fashionpediareport.model;

import id.ac.ui.cs.advprog.fashionpediareport.enums.ReportStatus;
import id.ac.ui.cs.advprog.fashionpediareport.status.UserReportState;
import id.ac.ui.cs.advprog.fashionpediareport.status.UserPendingState;

import java.time.LocalDate;
import java.util.UUID;

import lombok.*;


@Getter
@Setter
public class UserReport{
    UUID id;
    String reportedUsername;
    String alasan;
    ReportStatus status;
    LocalDate date;
    UserReportState state;
    
    public UserReport(String reportedusername, String alasan){
        this.id = UUID.randomUUID();
        this.reportedUsername = reportedusername;
        this.alasan = alasan;
        this.status = ReportStatus.PENDING;
        this.date = LocalDate.now();
        this.state = new UserPendingState();
    }

    public void approve(){
        state.approve(this);
    }

    public void reject(){
        state.reject(this);
    }
}
