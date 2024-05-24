package id.ac.ui.cs.advprog.fashionpediareport.status;

import id.ac.ui.cs.advprog.fashionpediareport.model.UserReport;

public class UserRejectedState implements UserReportState {
    @Override
    public void approve(UserReport Report) {
        // tidak bisa approve report yang sudah ter-reject
    }

    @Override
    public void reject(UserReport Report) {
        // sudah di reject
    }
    
}