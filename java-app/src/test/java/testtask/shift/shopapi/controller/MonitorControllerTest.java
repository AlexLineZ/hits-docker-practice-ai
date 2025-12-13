package testtask.shift.shopapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import testtask.shift.shopapi.model.monitor.Monitor;
import testtask.shift.shopapi.service.MonitorService;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MonitorController.class)
class MonitorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MonitorService monitorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllMonitors_returnsList() throws Exception {
        Monitor monitor = new Monitor(
                1L, "MN1", "Samsung",
                BigDecimal.valueOf(300),
                10L,
                27.0
        );

        when(monitorService.getAllMonitors())
                .thenReturn(List.of(monitor));

        mockMvc.perform(get("/api/monitors"))
                .andExpect(status().isOk());
    }

    @Test
    void getMonitorById_returnsMonitor() throws Exception {
        Monitor monitor = new Monitor(
                1L, "MN2", "LG",
                BigDecimal.valueOf(250),
                8L,
                24.0
        );

        when(monitorService.getMonitor(1L))
                .thenReturn(monitor);

        mockMvc.perform(get("/api/monitors/1"))
                .andExpect(status().isOk());
    }

    @Test
    void createMonitor_returnsSavedMonitor() throws Exception {
        Monitor monitor = new Monitor(
                "MN3", "Acer",
                BigDecimal.valueOf(200),
                6L,
                22.0
        );

        when(monitorService.save(any(Monitor.class)))
                .thenReturn(monitor);

        mockMvc.perform(post("/api/monitors/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(monitor)))
                .andExpect(status().isOk());
    }
}