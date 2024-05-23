package id.ac.ui.cs.advprog.fashionpediareport.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.fashionpediareport.model.UserReport;
import id.ac.ui.cs.advprog.fashionpediareport.repository.UserReportRepository;
import id.ac.ui.cs.advprog.fashionpediareport.enums.ReportStatus;

import java.util.List;
import java.util.Optional;

@Service
public class UserReportServiceImpl implements UserReportService{
    @Autowired 
    private UserReportRepository userReportRepository;

    @Override
    public UserReport createReport(UserReport report){
        return userReportRepository.save(report);
    }

    @Override
    public List<UserReport> findAll(){
        return userReportRepository.findAll();
    }

    public List<UserReport> findReportByUserId(String userId){
        return userReportRepository.findByUserId(userId);
    }

    @Override
    public List<UserReport> findReportbyReportedUserId(String reportedUserId){
        return userReportRepository.findByReportedUserId(reportedUserId);
    }

    @Override
    public Optional<UserReport> findReportbyId(String id){
        return userReportRepository.findById(id);
    }

    @Override
    public UserReport updateReport(UserReport report){
        return userReportRepository.save(report);
    }

    @Override
    public void deleteReport(String id){
        userReportRepository.deleteById(id);
    }

    @Override
    public UserReport updateReportStatus(String id, String status) {
        UserReport report = userReportRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Couldn't find the Report"));
        if (status.equalsIgnoreCase(ReportStatus.APPROVED.toString())) {
            report.approve();
        } else if (status.equalsIgnoreCase(ReportStatus.REJECTED.toString())) {
            report.reject();
        } else {
            throw new IllegalArgumentException("Invalid status");
        }

        userReportRepository.save(report);
        return report;
    }
}
