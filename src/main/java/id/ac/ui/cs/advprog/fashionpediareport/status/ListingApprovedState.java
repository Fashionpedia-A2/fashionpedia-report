package id.ac.ui.cs.advprog.fashionpediareport.status;

import id.ac.ui.cs.advprog.fashionpediareport.model.ListingReport;

public class ListingApprovedState implements ListingReportState {
    @Override
    public void approve(ListingReport Report) {
        // sudah di approve
    }

    @Override
    public void reject(ListingReport Report) {
        // tidak bisa reject report yang sudah ter-approve
    }
    
}
