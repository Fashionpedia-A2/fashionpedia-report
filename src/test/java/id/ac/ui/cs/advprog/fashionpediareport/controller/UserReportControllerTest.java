package id.ac.ui.cs.advprog.fashionpediareport.controller;

import id.ac.ui.cs.advprog.fashionpediareport.model.UserReport;
import id.ac.ui.cs.advprog.fashionpediareport.services.UserReportService;
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

public class UserReportControllerTest {

    @InjectMocks
    private UserReportController userReportController;

    @Mock
    private UserReportService userReportService;

    private MockMvc mockMvc;

    private UserReport userReport1;
    private UserReport userReport2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userReportController).build();

        userReport1 = new UserReport();
        userReport1.setId(UUID.randomUUID().toString());
        userReport1.setUserId("user1");
        userReport1.setReportedUserId("reported-user1");
        userReport1.setAlasan("Alasan 1");
        userReport1.setDate(LocalDate.now());

        userReport2 = new UserReport();
        userReport2.setId(UUID.randomUUID().toString());
        userReport2.setUserId("user2");
        userReport2.setReportedUserId("reported-user2");
        userReport2.setAlasan("Alasan 2");
        userReport2.setDate(LocalDate.now());
    }

    @Test
    public void testGetReport() throws Exception {
        when(userReportService.findReportByUserId("user1")).thenReturn(Arrays.asList(userReport1));

        mockMvc.perform(get("/user-report/user-reports/{id}", "user1")
                .contentType(MediaType.APPLICATION_JSON));

        verify(userReportService, times(1)).findReportByUserId("user1");
    }

    @Test
    public void testGetReportById() throws Exception {
        when(userReportService.findReportbyId(userReport1.getId())).thenReturn(Optional.of(userReport1));

        mockMvc.perform(get("/user-report/{id}", userReport1.getId())
                .contentType(MediaType.APPLICATION_JSON));

        verify(userReportService, times(1)).findReportbyId(userReport1.getId());
    }

    @Test
    public void testGetReportByReportedUserId() throws Exception {
        when(userReportService.findReportbyReportedUserId("reported-user1")).thenReturn(Arrays.asList(userReport1));

        mockMvc.perform(get("/user-report/reported-user/{id}", "reported-user1")
                .contentType(MediaType.APPLICATION_JSON));

        verify(userReportService, times(1)).findReportbyReportedUserId("reported-user1");
    }

    @Test
    public void testCreateReport() throws Exception {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", "user1");
        requestBody.put("alasan", "Alasan 1");

        when(userReportService.createReport(any(UserReport.class))).thenReturn(userReport1);

        mockMvc.perform(post("/user-report/{id}/create", "reported-user1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestBody)));

        verify(userReportService, times(1)).createReport(any(UserReport.class));
    }

    @Test
    public void testUpdateReport() throws Exception {
        when(userReportService.findReportbyId(userReport1.getId())).thenReturn(Optional.of(userReport1));
        when(userReportService.updateReport(any(UserReport.class))).thenReturn(userReport1);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("alasan", "Updated Alasan");

        mockMvc.perform(patch("/user-report/{id}/update", userReport1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestBody)));

        verify(userReportService, times(1)).findReportbyId(userReport1.getId());
        verify(userReportService, times(1)).updateReport(any(UserReport.class));
    }

    @Test
    public void testDeleteReport() throws Exception {
        doNothing().when(userReportService).deleteReport(userReport1.getId());

        mockMvc.perform(delete("/user-report/{id}/delete", userReport1.getId()))
                .andExpect(status().isOk());

        verify(userReportService, times(1)).deleteReport(userReport1.getId());
    }

    @Test
    public void testUpdateStatus() throws Exception {
        when(userReportService.updateReportStatus(eq(userReport1.getId()), any(String.class))).thenReturn(userReport1);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("status", "APPROVED");

        mockMvc.perform(patch("/user-report/{id}/update-status", userReport1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestBody)));

        verify(userReportService, times(1)).updateReportStatus(userReport1.getId(), "APPROVED");
    }
}
