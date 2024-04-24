package id.ac.ui.cs.advprog.fashionpediareport.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class UserReportTest {
    private UserReport report;

    @BeforeEach
    void setUp(){
        report = new UserReport(null, null);
        report.setReportedUsername("loremipsum123");
        report.setAlasan("User penipu");
        report.setStatus("PENDING");
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
        assertEquals("PENDING", report.status);
        assertEquals(LocalDate.now(), report.getDate());
    }

}