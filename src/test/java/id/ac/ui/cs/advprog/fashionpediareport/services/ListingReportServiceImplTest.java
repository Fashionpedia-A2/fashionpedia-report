package id.ac.ui.cs.advprog.fashionpediareport.services;

import id.ac.ui.cs.advprog.fashionpediareport.model.ListingReport;
import id.ac.ui.cs.advprog.fashionpediareport.repository.ListingReportRepository;
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

public class ListingReportServiceImplTest {

    @InjectMocks
    private ListingReportServiceImpl listingReportService;

    @Mock
    private ListingReportRepository listingReportRepository;

    private ListingReport listingReport1;
    private ListingReport listingReport2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        listingReport1 = new ListingReport();
        listingReport1.setId(UUID.randomUUID().toString());
        listingReport1.setUserId("user1");
        listingReport1.setReportedListingId("listing1");
        listingReport1.setAlasan("Alasan 1");
        listingReport1.setDate(LocalDate.now());

        listingReport2 = new ListingReport();
        listingReport2.setId(UUID.randomUUID().toString());
        listingReport2.setUserId("user2");
        listingReport2.setReportedListingId("listing2");
        listingReport2.setAlasan("Alasan 2");
        listingReport2.setDate(LocalDate.now());
    }

    @Test
    public void testCreateReport() {
        when(listingReportRepository.save(any(ListingReport.class))).thenReturn(listingReport1);
        ListingReport createdReport = listingReportService.createReport(listingReport1);
        assertNotNull(createdReport);
        assertEquals(listingReport1.getId(), createdReport.getId());
        verify(listingReportRepository, times(1)).save(listingReport1);
    }

    @Test
    public void testFindAll() {
        when(listingReportRepository.findAll()).thenReturn(Arrays.asList(listingReport1, listingReport2));
        List<ListingReport> reports = listingReportService.findAll();
        assertEquals(2, reports.size());
        verify(listingReportRepository, times(1)).findAll();
    }

    @Test
    public void testFindReportByUserId() {
        when(listingReportRepository.findByUserId("user1")).thenReturn(Arrays.asList(listingReport1));
        List<ListingReport> reports = listingReportService.findReportByUserId("user1");
        assertEquals(1, reports.size());
        assertEquals("user1", reports.get(0).getUserId());
        verify(listingReportRepository, times(1)).findByUserId("user1");
    }

    @Test
    public void testFindReportByReportedListingId() {
        when(listingReportRepository.findByReportedListingId("listing1")).thenReturn(Arrays.asList(listingReport1));
        List<ListingReport> reports = listingReportService.findReportByReportedListingId("listing1");
        assertEquals(1, reports.size());
        assertEquals("listing1", reports.get(0).getReportedListingId());
        verify(listingReportRepository, times(1)).findByReportedListingId("listing1");
    }

    @Test
    public void testFindReportById() {
        when(listingReportRepository.findById(listingReport1.getId())).thenReturn(Optional.of(listingReport1));
        Optional<ListingReport> report = listingReportService.findReportById(listingReport1.getId());
        assertTrue(report.isPresent());
        assertEquals(listingReport1.getId(), report.get().getId());
        verify(listingReportRepository, times(1)).findById(listingReport1.getId());
    }

    @Test
    public void testUpdateReport() {
        when(listingReportRepository.save(any(ListingReport.class))).thenReturn(listingReport1);
        ListingReport updatedReport = listingReportService.updateReport(listingReport1);
        assertNotNull(updatedReport);
        assertEquals(listingReport1.getId(), updatedReport.getId());
        verify(listingReportRepository, times(1)).save(listingReport1);
    }

    @Test
    public void testDeleteReport() {
        doNothing().when(listingReportRepository).deleteById(listingReport1.getId());
        listingReportService.deleteReport(listingReport1.getId());
        verify(listingReportRepository, times(1)).deleteById(listingReport1.getId());
    }

    @Test
    public void testUpdateReportStatusInvalid() {
        when(listingReportRepository.findById(listingReport1.getId())).thenReturn(Optional.of(listingReport1));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            listingReportService.updateReportStatus(listingReport1.getId(), "INVALID_STATUS");
        });

        String expectedMessage = "Invalid status";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        verify(listingReportRepository, times(1)).findById(listingReport1.getId());
        verify(listingReportRepository, never()).save(any(ListingReport.class));
    }
}
