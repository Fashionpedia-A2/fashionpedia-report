package id.ac.ui.cs.advprog.fashionpediareport.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.fashionpediareport.enums.ReportStatus;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class UserReportTest {
    private UserReport report;

    @BeforeEach
    void setUp(){
        report = new UserReport(null, null);
        report.setReportedUsername("loremipsum123");
        report.setAlasan("User penipu");
        report.setStatus(ReportStatus.PENDING);
        report.setDate(LocalDate.now());
    }

    @Test
    public void testGetter(){
        assertNotNull(report);
        assertNotNull(report.id);
        assertNotNull(report.getAlasan());
        assertNotNull(report.getDate());
        assertEquals("loremipsum123", report.getReportedUsername());
        assertEquals("User penipu", report.getAlasan());
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
