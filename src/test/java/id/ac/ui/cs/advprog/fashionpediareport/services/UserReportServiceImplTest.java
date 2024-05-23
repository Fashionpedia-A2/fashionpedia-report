package id.ac.ui.cs.advprog.fashionpediareport.services;

import id.ac.ui.cs.advprog.fashionpediareport.model.UserReport;
import id.ac.ui.cs.advprog.fashionpediareport.repository.UserReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserReportServiceImplTest {

    @InjectMocks
    private UserReportServiceImpl userReportService;

    @Mock
    private UserReportRepository userReportRepository;

    private UserReport userReport1;
    private UserReport userReport2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userReport1 = new UserReport();
        userReport1.setId(UUID.randomUUID().toString());
        userReport1.setUserId("user1");
        userReport1.setReportedUserId("ruser1");
        userReport1.setAlasan("Alasan 1");
        userReport1.setDate(LocalDate.now());

        userReport2 = new UserReport();
        userReport2.setId(UUID.randomUUID().toString());
        userReport2.setUserId("user2");
        userReport2.setReportedUserId("ruser2");
        userReport2.setAlasan("Alasan 2");
        userReport2.setDate(LocalDate.now());
    }

    @Test
    public void testCreateReport() {
        when(userReportRepository.save(any(UserReport.class))).thenReturn(userReport1);
        UserReport createdReport = userReportService.createReport(userReport1);
        assertNotNull(createdReport);
        assertEquals(userReport1.getId(), createdReport.getId());
        verify(userReportRepository, times(1)).save(userReport1);
    }

    @Test
    public void testFindAll() {
        when(userReportRepository.findAll()).thenReturn(Arrays.asList(userReport1, userReport2));
        List<UserReport> reports = userReportService.findAll();
        assertEquals(2, reports.size());
        verify(userReportRepository, times(1)).findAll();
    }

    @Test
    public void testFindReportByUserId() {
        when(userReportRepository.findByUserId("user1")).thenReturn(Arrays.asList(userReport1));
        List<UserReport> reports = userReportService.findReportByUserId("user1");
        assertEquals(1, reports.size());
        assertEquals("user1", reports.get(0).getUserId());
        verify(userReportRepository, times(1)).findByUserId("user1");
    }

    @Test
    public void testFindReportByReportedUserId() {
        when(userReportRepository.findByReportedUserId("ruser1")).thenReturn(Arrays.asList(userReport1));
        List<UserReport> reports = userReportService.findReportbyReportedUserId("ruser1");
        assertEquals(1, reports.size());
        assertEquals("ruser1", reports.get(0).getReportedUserId());
        verify(userReportRepository, times(1)).findByReportedUserId("ruser1");
    }

    @Test
    public void testFindReportById() {
        when(userReportRepository.findById(userReport1.getId())).thenReturn(Optional.of(userReport1));
        Optional<UserReport> report = userReportService.findReportbyId(userReport1.getId());
        assertTrue(report.isPresent());
        assertEquals(userReport1.getId(), report.get().getId());
        verify(userReportRepository, times(1)).findById(userReport1.getId());
    }

    @Test
    public void testUpdateReport() {
        when(userReportRepository.save(any(UserReport.class))).thenReturn(userReport1);
        UserReport updatedReport = userReportService.updateReport(userReport1);
        assertNotNull(updatedReport);
        assertEquals(userReport1.getId(), updatedReport.getId());
        verify(userReportRepository, times(1)).save(userReport1);
    }

    @Test
    public void testDeleteReport() {
        doNothing().when(userReportRepository).deleteById(userReport1.getId());
        userReportService.deleteReport(userReport1.getId());
        verify(userReportRepository, times(1)).deleteById(userReport1.getId());
    }

    @Test
    public void testUpdateReportStatusInvalid() {
        when(userReportRepository.findById(userReport1.getId())).thenReturn(Optional.of(userReport1));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userReportService.updateReportStatus(userReport1.getId(), "INVALID_STATUS");
        });

        String expectedMessage = "Invalid status";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        verify(userReportRepository, times(1)).findById(userReport1.getId());
        verify(userReportRepository, never()).save(any(UserReport.class));
    }
}
