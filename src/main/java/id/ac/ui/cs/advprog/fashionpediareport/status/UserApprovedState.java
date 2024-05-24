package id.ac.ui.cs.advprog.fashionpediareport.status;

import id.ac.ui.cs.advprog.fashionpediareport.model.UserReport;

public class UserApprovedState implements UserReportState {
    @Override
    public void approve(UserReport Report) {
        // sudah di approve
    }

    @Override
    public void reject(UserReport Report) {
        // tidak bisa reject report yang sudah ter-approve
    }
    
}
