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
        report = new ListingReport("loremipsum123", "reportedListing123", "Barangnya Palsu!");
    }

    @Test
    public void testGetter(){
        assertNotNull(report);
        assertNotNull(report.getId());
        assertNotNull(report.getAlasan());
        assertNotNull(report.getDate());
        assertEquals("loremipsum123", report.getUserId());
        assertEquals("reportedListing123", report.getReportedListingId());
        assertEquals("Barangnya Palsu!", report.getAlasan());
        assertEquals(ReportStatus.PENDING, report.getStatus());
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

    @Test
    public void testApprove(){
        report.approve();
        assertEquals(ReportStatus.APPROVED, report.getStatus());
    }

    @Test
    public void testReject(){
        report.reject();
        assertEquals(ReportStatus.REJECTED, report.getStatus());
    }
}