package id.ac.ui.cs.advprog.fashionpediareport.services;

import id.ac.ui.cs.advprog.fashionpediareport.model.UserReport;
import java.util.List;
import java.util.Optional;

public interface UserReportService {
    UserReport createReport(UserReport report);
    List<UserReport> findAll();
    List<UserReport> findReportByUserId(String userId);
    List<UserReport> findReportbyReportedUserId(String reportedUserId);
    Optional<UserReport> findReportbyId(String id);
    UserReport updateReport(UserReport report);
    void deleteReport(String id);
    UserReport updateReportStatus(String id, String status);
}
