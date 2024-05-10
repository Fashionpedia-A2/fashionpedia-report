package id.ac.ui.cs.advprog.fashionpediareport.status;

import id.ac.ui.cs.advprog.fashionpediareport.model.ListingReport;

public interface ListingReportState {
    void approve(ListingReport report);
    void reject(ListingReport report);
}
