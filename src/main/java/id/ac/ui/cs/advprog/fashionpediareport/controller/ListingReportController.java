package id.ac.ui.cs.advprog.fashionpediareport.controller;

import id.ac.ui.cs.advprog.fashionpediareport.model.ListingReport;
import id.ac.ui.cs.advprog.fashionpediareport.services.ListingReportService;

import java.util.concurrent.CompletableFuture;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAsync
@RequestMapping("/listing-report")
public class ListingReportController {
    @Autowired
    private ListingReportService service;

    @Async
    @GetMapping("/listing-reports/{id}")
    public CompletableFuture<ResponseEntity<List<ListingReport>>> getReport(@PathVariable String id){
        List<ListingReport> reports = service.findReportByUserId(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok(reports));
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<ListingReport>> getReportById(@PathVariable String id){
        Optional<ListingReport> listingReport = service.findReportById(id);
        if (listingReport.isPresent()) {
            ListingReport report = listingReport.get();
            return CompletableFuture.completedFuture(ResponseEntity.ok(report));
        } else {
            return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
        }
    }

    @Async
    @GetMapping("reported-listing/{id}")
    public CompletableFuture<ResponseEntity<List<ListingReport>>> getReportByReportedListingId(@PathVariable String id){
        List<ListingReport> reports = service.findReportByReportedListingId(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok(reports));
    }

    @Async
    @PostMapping("/{id}/create")
    public CompletableFuture<ResponseEntity<ListingReport>> createReport( @PathVariable String id, @RequestBody Map<String, Object> requestBody ){
        String userId = requestBody.get("userId").toString();
        String alasan = requestBody.get("alasan").toString();
        ListingReport report = new ListingReport(userId, id, alasan);
        ListingReport buatReport = service.createReport(report);
        return CompletableFuture.completedFuture(ResponseEntity.ok(buatReport));
    }

    @Async
    @PatchMapping("/{id}/update")
    public CompletableFuture<ResponseEntity<ListingReport>> updateReport(@PathVariable String id, @RequestBody Map<String, String> requestBody) {
        Optional<ListingReport> listingReport = service.findReportById(id);
        if (listingReport.isPresent()) {
            ListingReport report = listingReport.get();
            report.setAlasan(requestBody.get("alasan")); 
            report.setDate(LocalDate.now());
            ListingReport updateReport= service.updateReport(report);
            return CompletableFuture.completedFuture(ResponseEntity.ok(updateReport));
        } else {
            return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
        }
    }

    @Async
    @DeleteMapping("/{id}/delete")
    public CompletableFuture<ResponseEntity<ListingReport>> deleteReport(@PathVariable String id){
        service.deleteReport(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @PatchMapping("/{id}/update-status")
    public CompletableFuture<ResponseEntity<ListingReport>> updateStatus(@PathVariable String id, @RequestBody Map<String, String> requestBody) {
        String status = requestBody.get("status");
        ListingReport updatedReportStatus = service.updateReportStatus(id, status);
        return CompletableFuture.completedFuture(ResponseEntity.ok(updatedReportStatus));
    }
}