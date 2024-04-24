package id.ac.ui.cs.advprog.fashionpediareport.model;

import id.ac.ui.cs.advprog.fashionpediareport.enums.ReportStatus;
import java.time.LocalDate;
import java.util.UUID;

import lombok.*;


@Getter
@Setter
public class UserReport{
    UUID id;
    String reportedUsername;
    String alasan;
    String status;
    LocalDate date;
    
    public UserReport(String reportedusername, String alasan){
        this.id = UUID.randomUUID();
        this.reportedUsername = reportedusername;
        this.alasan = alasan;
        this.status = ReportStatus.PENDING.getValue();
        this.date = LocalDate.now();
    }
}
