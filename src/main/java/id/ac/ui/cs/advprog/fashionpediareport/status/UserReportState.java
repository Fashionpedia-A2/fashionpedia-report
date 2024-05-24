package id.ac.ui.cs.advprog.fashionpediareport.status;

import id.ac.ui.cs.advprog.fashionpediareport.model.UserReport;

public interface UserReportState {
    void approve(UserReport report);
    void reject(UserReport report);
}