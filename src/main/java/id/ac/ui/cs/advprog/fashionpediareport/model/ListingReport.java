package id.ac.ui.cs.advprog.fashionpediareport.model;

import jakarta.persistence.*;
import id.ac.ui.cs.advprog.fashionpediareport.enums.ReportStatus;
import id.ac.ui.cs.advprog.fashionpediareport.status.ListingApprovedState;
import id.ac.ui.cs.advprog.fashionpediareport.status.ListingPendingState;
import id.ac.ui.cs.advprog.fashionpediareport.status.ListingRejectedState;
import id.ac.ui.cs.advprog.fashionpediareport.status.ListingReportState;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;


@Getter
@Setter
@Entity
@Table(name = "listing_report")
public class ListingReport{
    @Id
    String id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "reported_listing_id", nullable = false)
    String reportedListingId;

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
    ListingReportState state;
    
    public ListingReport(String userId, String reportedListingId, String alasan){
        this.userId = userId;
        this.id = UUID.randomUUID().toString();
        this.reportedListingId = reportedListingId;
        this.alasan = alasan;
        this.status = ReportStatus.PENDING;
        this.date = LocalDate.now();
        this.state = new ListingPendingState();
        this.stateStr = ReportStatus.PENDING.toString();
    }

    public ListingReport() {

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
                this.state = new ListingApprovedState();
                break;
            case "REJECTED":
                this.state = new ListingRejectedState();
                break;
            default:
                this.state = new ListingPendingState();
                break;
        }
    }
}