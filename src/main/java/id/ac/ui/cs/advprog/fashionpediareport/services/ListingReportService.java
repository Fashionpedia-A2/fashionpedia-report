package id.ac.ui.cs.advprog.fashionpediareport.services;

import id.ac.ui.cs.advprog.fashionpediareport.model.ListingReport;

import java.util.List;
import java.util.Optional;

public interface ListingReportService {
    ListingReport createReport(ListingReport report);
    List<ListingReport> findAll();
    List<ListingReport> findReportByUserId(String userId);
    List<ListingReport> findReportByReportedListingId(String reportedListingId);
    Optional<ListingReport> findReportById(String id);
    ListingReport updateReport(ListingReport report);
    void deleteReport(String id);
    ListingReport updateReportStatus(String id, String status);
}