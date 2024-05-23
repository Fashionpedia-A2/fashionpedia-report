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
        report = new UserReport("loremipsum123", "reportedUser123", "User penipu");
    }

    @Test
    public void testGetter(){
        assertNotNull(report);
        assertNotNull(report.getId());
        assertNotNull(report.getAlasan());
        assertNotNull(report.getDate());
        assertEquals("loremipsum123", report.getUserId());
        assertEquals("reportedUser123", report.getReportedUserId());
        assertEquals("User penipu", report.getAlasan());
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
