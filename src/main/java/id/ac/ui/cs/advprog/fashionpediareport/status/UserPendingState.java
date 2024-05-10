package id.ac.ui.cs.advprog.fashionpediareport.status;

import id.ac.ui.cs.advprog.fashionpediareport.model.UserReport;
import id.ac.ui.cs.advprog.fashionpediareport.enums.ReportStatus;

public class UserPendingState implements UserReportState{
    @Override
    public void approve(UserReport report){
        report.setStatus(ReportStatus.APPROVED);
        report.setState(new UserApprovedState());
    }

    @Override
    public void reject(UserReport report){
        report.setStatus(ReportStatus.REJECTED);
        report.setState(new UserRejectedState());
    }
}
