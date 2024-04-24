package id.ac.ui.cs.advprog.fashionpediareport.model;

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
        this.status = "PENDING";
        this.date = LocalDate.now();
    }
}
