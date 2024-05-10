package id.ac.ui.cs.advprog.fashionpediareport.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.fashionpediareport.enums.ReportStatus;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class ListingReportTest {
    private ListingReport report;

    @BeforeEach
    void setUp(){
        report = new ListingReport(null, null);
        report.setReportedListingId("3cf20657-5575-442e-b102-a96021a3112b");
        report.setAlasan("Barangnya Palsu!");
        report.setStatus(ReportStatus.PENDING);
        report.setDate(LocalDate.now());
    }

    @Test
    public void testGetter(){
        assertNotNull(report);
        assertNotNull(report.id);
        assertNotNull(report.getAlasan());
        assertNotNull(report.getDate());
        assertEquals("3cf20657-5575-442e-b102-a96021a3112b", report.getReportedListingId());
        assertEquals("Barangnya Palsu!", report.getAlasan());
        assertEquals(ReportStatus.PENDING, report.status);
        assertEquals(LocalDate.now(), report.getDate());
    }

    @Test
    public void testSetReportStatus(){
        // test set report status to APPROVED
        report.setStatus(ReportStatus.APPROVED);
        assertEquals(ReportStatus.APPROVED, report.getStatus());

        // test set report status to REJECTED
        report.setStatus(ReportStatus.REJECTED);
        assertEquals(ReportStatus.REJECTED, report.getStatus());
    }

}