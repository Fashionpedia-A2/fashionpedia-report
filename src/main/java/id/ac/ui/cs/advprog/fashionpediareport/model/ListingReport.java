package id.ac.ui.cs.advprog.fashionpediareport.model;

import id.ac.ui.cs.advprog.fashionpediareport.enums.ReportStatus;
import java.time.LocalDate;
import java.util.UUID;

import lombok.*;


@Getter
@Setter
public class ListingReport{
    UUID id;
    String reportedListingId;
    String alasan;
    String status;
    LocalDate date;
    
    public ListingReport(String reportedListingId, String alasan){
        this.id = UUID.randomUUID();
        this.reportedListingId = reportedListingId;
        this.alasan = alasan;
        this.status = ReportStatus.PENDING.getValue();
        this.date = LocalDate.now();
    }
}