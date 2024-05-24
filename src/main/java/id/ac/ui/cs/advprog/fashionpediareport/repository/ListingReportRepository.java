package id.ac.ui.cs.advprog.fashionpediareport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.fashionpediareport.model.ListingReport;

import java.util.List;

@Repository
public interface ListingReportRepository extends JpaRepository<ListingReport, String>{
    List<ListingReport> findByUserId(String userId);
    List<ListingReport> findByReportedListingId(String reportedListingId);
}