package id.ac.ui.cs.advprog.fashionpediareport.model;

import id.ac.ui.cs.advprog.fashionpediareport.enums.ReportStatus;
import id.ac.ui.cs.advprog.fashionpediareport.status.ListingPendingState;
import id.ac.ui.cs.advprog.fashionpediareport.status.ListingReportState;

import java.time.LocalDate;
import java.util.UUID;

import lombok.*;


@Getter
@Setter
public class ListingReport{
    UUID id;
    String reportedListingId;
    String alasan;
    ReportStatus status;
    LocalDate date;
    ListingReportState state;
    
    public ListingReport(String reportedListingId, String alasan){
        this.id = UUID.randomUUID();
        this.reportedListingId = reportedListingId;
        this.alasan = alasan;
        this.status = ReportStatus.PENDING;
        this.date = LocalDate.now();
        this.state = new ListingPendingState();
    }

    public void approve(){
        state.approve(this);
    }

    public void reject(){
        state.reject(this);
    }
}