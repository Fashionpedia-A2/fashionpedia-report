package id.ac.ui.cs.advprog.fashionpediareport.status;

import id.ac.ui.cs.advprog.fashionpediareport.model.ListingReport;

public class ListingRejectedState implements ListingReportState {
    @Override
    public void approve(ListingReport Report) {
        // tidak bisa approve report yang sudah ter-reject
    }

    @Override
    public void reject(ListingReport Report) {
        // sudah di reject
    }
    
}