package id.ac.ui.cs.advprog.fashionpediareport.status;

import id.ac.ui.cs.advprog.fashionpediareport.model.ListingReport;
import id.ac.ui.cs.advprog.fashionpediareport.enums.ReportStatus;

public class ListingPendingState implements ListingReportState{
    @Override
    public void approve(ListingReport report){
        report.setStatus(ReportStatus.APPROVED);
        report.setState(new ListingApprovedState());
    }

    @Override
    public void reject(ListingReport report){
        report.setStatus(ReportStatus.REJECTED);
        report.setState(new ListingRejectedState());
    }
}
