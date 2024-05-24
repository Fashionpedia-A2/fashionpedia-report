package id.ac.ui.cs.advprog.fashionpediareport.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.fashionpediareport.enums.ReportStatus;
import id.ac.ui.cs.advprog.fashionpediareport.model.ListingReport;
import id.ac.ui.cs.advprog.fashionpediareport.repository.ListingReportRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ListingReportServiceImpl implements ListingReportService{
    @Autowired 
    private ListingReportRepository listingReportRepository;

    @Override
    public ListingReport createReport(ListingReport report){
        return listingReportRepository.save(report);
    }

    @Override
    public List<ListingReport> findAll(){
        return listingReportRepository.findAll();
    }

    @Override
    public List<ListingReport> findReportByUserId(String userId){
        return listingReportRepository.findByUserId(userId);
    }
    
    @Override
    public List<ListingReport> findReportByReportedListingId(String reportedListingId){
        return listingReportRepository.findByReportedListingId(reportedListingId);
    }

    @Override
    public Optional<ListingReport> findReportById(String id){
        return listingReportRepository.findById(id);
    }

    @Override
    public ListingReport updateReport(ListingReport report){
        return listingReportRepository.save(report);
    }

    @Override
    public void deleteReport(String id){
        listingReportRepository.deleteById(id);
    }

    @Override
    public ListingReport updateReportStatus(String id, String status) {
        ListingReport report = listingReportRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Couldn't find the Report"));
        if (status.equalsIgnoreCase(ReportStatus.APPROVED.toString())) {
            report.approve();
        } else if (status.equalsIgnoreCase(ReportStatus.REJECTED.toString())) {
            report.reject();
        } else {
            throw new IllegalArgumentException("Invalid status");
        }

        listingReportRepository.save(report);
        return report;
    }
}
