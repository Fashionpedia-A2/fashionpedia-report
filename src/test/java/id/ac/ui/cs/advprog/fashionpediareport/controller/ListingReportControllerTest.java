package id.ac.ui.cs.advprog.fashionpediareport.controller;

import id.ac.ui.cs.advprog.fashionpediareport.model.ListingReport;
import id.ac.ui.cs.advprog.fashionpediareport.services.ListingReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ListingReportControllerTest {

    @InjectMocks
    private ListingReportController listingReportController;

    @Mock
    private ListingReportService listingReportService;

    private MockMvc mockMvc;

    private ListingReport listingReport1;
    private ListingReport listingReport2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(listingReportController).build();

        listingReport1 = new ListingReport();
        listingReport1.setId(UUID.randomUUID().toString());
        listingReport1.setUserId("user1");
        listingReport1.setReportedListingId("reported-listing1");
        listingReport1.setAlasan("Alasan 1");
        listingReport1.setDate(LocalDate.now());

        listingReport2 = new ListingReport();
        listingReport2.setId(UUID.randomUUID().toString());
        listingReport2.setUserId("user2");
        listingReport2.setReportedListingId("reported-listing2");
        listingReport2.setAlasan("Alasan 2");
        listingReport2.setDate(LocalDate.now());
    }

    @Test
    public void testGetReport() throws Exception {
        when(listingReportService.findReportByUserId("user1")).thenReturn(Arrays.asList(listingReport1));

        mockMvc.perform(get("/listing-report/listing-reports/{id}", "user1")
                .contentType(MediaType.APPLICATION_JSON));

        verify(listingReportService, times(1)).findReportByUserId("user1");
    }

    @Test
    public void testGetReportById() throws Exception {
        when(listingReportService.findReportById(listingReport1.getId())).thenReturn(Optional.of(listingReport1));

        mockMvc.perform(get("/listing-report/{id}", listingReport1.getId())
                .contentType(MediaType.APPLICATION_JSON));

        verify(listingReportService, times(1)).findReportById(listingReport1.getId());
    }

    @Test
    public void testGetReportByReportedListingId() throws Exception {
        when(listingReportService.findReportByReportedListingId("reported-listing1")).thenReturn(Arrays.asList(listingReport1));

        mockMvc.perform(get("/listing-report/reported-listing/{id}", "reported-listing1")
                .contentType(MediaType.APPLICATION_JSON));

        verify(listingReportService, times(1)).findReportByReportedListingId("reported-listing1");
    }

    @Test
    public void testCreateReport() throws Exception {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", "user1");
        requestBody.put("alasan", "Alasan 1");

        when(listingReportService.createReport(any(ListingReport.class))).thenReturn(listingReport1);
        
        mockMvc.perform(post("/listing-report/{id}/create", "reported-listing1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":\"user1\",\"alasan\":\"Alasan 1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("user1"))
                .andExpect(jsonPath("$.reportedListingId").value("reported-listing1"));

        verify(listingReportService, times(1)).createReport(any(ListingReport.class));
    }

    @Test
    public void testUpdateReport() throws Exception {
        when(listingReportService.findReportById(listingReport1.getId())).thenReturn(Optional.of(listingReport1));
        when(listingReportService.updateReport(any(ListingReport.class))).thenReturn(listingReport1);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("alasan", "Updated Alasan");

        mockMvc.perform(patch("/listing-report/{id}/update", listingReport1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestBody)));

        verify(listingReportService, times(1)).findReportById(listingReport1.getId());
        verify(listingReportService, times(1)).updateReport(any(ListingReport.class));
    }

    @Test
    public void testDeleteReport() throws Exception {
        doNothing().when(listingReportService).deleteReport(listingReport1.getId());

        mockMvc.perform(delete("/listing-report/{id}/delete", listingReport1.getId()))
                .andExpect(status().isOk());

        verify(listingReportService, times(1)).deleteReport(listingReport1.getId());
    }

    @Test
    public void testUpdateStatus() throws Exception {
        when(listingReportService.updateReportStatus(eq(listingReport1.getId()), any(String.class))).thenReturn(listingReport1);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("status", "APPROVED");

        mockMvc.perform(patch("/listing-report/{id}/update-status", listingReport1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestBody)));

        verify(listingReportService, times(1)).updateReportStatus(listingReport1.getId(), "APPROVED");
    }
}
