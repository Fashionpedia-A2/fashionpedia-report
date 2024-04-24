package id.ac.ui.cs.advprog.fashionpediareport.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class UserReportTest {
    private UserReport report;

    @BeforeEach
    void setUp(){
        report = new UserReport();
        report.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        report.setReportedUsername("loremipsum123");
        report.setAlasan("User penipu");
        report.setStatus("PENDING");
        report.setDate(LocalDate.now());
    }

    @Test
    public void testGetter(){
        assertNotNull(report);
        assertNotNull(report.getAlasan());
        assertNotNull(report.getDate());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", report.getId());
        assertEquals("loremipsum123", report.getReportedUsername());
        assertEquals("User penipu", report.getAlasan());
        assertEquals("PENDING", report.status);
        assertEquals(LocalDate.now(), report.getDate());
    }

}
