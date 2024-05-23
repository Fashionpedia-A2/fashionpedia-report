package id.ac.ui.cs.advprog.fashionpediareport.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import id.ac.ui.cs.advprog.fashionpediareport.model.UserReport;

import java.util.List;

@Repository
public interface UserReportRepository extends JpaRepository<UserReport, String> {
    List<UserReport> findByUserId(String userId);
    List<UserReport> findByReportedUserId(String reportedUserId);
}