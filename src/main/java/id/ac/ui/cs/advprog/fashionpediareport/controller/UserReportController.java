package id.ac.ui.cs.advprog.fashionpediareport.controller;

import id.ac.ui.cs.advprog.fashionpediareport.model.UserReport;
import id.ac.ui.cs.advprog.fashionpediareport.services.UserReportService;

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
@RequestMapping("/user-report")
public class UserReportController {
    @Autowired
    private UserReportService service;

    @Async
    @GetMapping("/user-reports/{id}")
    public CompletableFuture<ResponseEntity<List<UserReport>>> getReport(@PathVariable String id){
        List<UserReport> reports = service.findReportByUserId(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok(reports));
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<UserReport>> getReportById(@PathVariable String id){
        Optional<UserReport> userReport = service.findReportbyId(id);
        if (userReport.isPresent()) {
            UserReport report = userReport.get();
            return CompletableFuture.completedFuture(ResponseEntity.ok(report));
        } else {
            return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
        }
    }

    @Async
    @GetMapping("reported-user/{id}")
    public CompletableFuture<ResponseEntity<List<UserReport>>> getReportByReportedUserId(@PathVariable String id){
        List<UserReport> reports = service.findReportbyReportedUserId(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok(reports));
    }

    @Async
    @PostMapping("/{id}/create")
    public ResponseEntity<UserReport> createReport( @PathVariable String id, @RequestBody Map<String, Object> requestBody ){
        String userId = requestBody.get("userId").toString();
        String alasan = requestBody.get("alasan").toString();
        UserReport report = new UserReport(userId, id, alasan);
        UserReport buatReport = service.createReport(report);
        return ResponseEntity.ok(buatReport);
    }

    @Async
    @PatchMapping("/{id}/update")
    public CompletableFuture<ResponseEntity<UserReport>> updateReport(@PathVariable String id, @RequestBody Map<String, String> requestBody) {
        Optional<UserReport> userReport = service.findReportbyId(id);
        if (userReport.isPresent()) {
            UserReport report = userReport.get();
            report.setAlasan(requestBody.get("alasan")); 
            report.setDate(LocalDate.now());
            UserReport updateReport= service.updateReport(report);
            return CompletableFuture.completedFuture(ResponseEntity.ok(updateReport));
        } else {
            return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
        }
    }

    @Async
    @DeleteMapping("/{id}/delete")
    public CompletableFuture<ResponseEntity<UserReport>> deleteReport(@PathVariable String id){
        service.deleteReport(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @PatchMapping("/{id}/update-status")
    public CompletableFuture<ResponseEntity<UserReport>> updateStatus(@PathVariable String id, @RequestBody Map<String, String> requestBody) {
        String status = requestBody.get("status");
        UserReport updatedReportStatus = service.updateReportStatus(id, status);
        return CompletableFuture.completedFuture(ResponseEntity.ok(updatedReportStatus));
    }
}