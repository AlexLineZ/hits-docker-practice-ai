package testtask.shift.shopapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import testtask.shift.shopapi.mapper.HardDriveMapper;
import testtask.shift.shopapi.model.hdd.HardDrive;
import testtask.shift.shopapi.service.HardDriveService;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HardDriveController.class)
class HardDriveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HardDriveService hardDriveService;

    @MockBean
    private HardDriveMapper hardDriveMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllHardDrives_returnsList() throws Exception {
        HardDrive hdd = new HardDrive(
                1L, "HD1", "Seagate",
                BigDecimal.valueOf(120),
                20L,
                1000
        );

        when(hardDriveService.getAllHardDrives())
                .thenReturn(List.of(hdd));

        mockMvc.perform(get("/api/hdds"))
                .andExpect(status().isOk());
    }

    @Test
    void createHardDrive_returnsSavedHardDrive() throws Exception {
        HardDrive hdd = new HardDrive(
                "HD2", "WD",
                BigDecimal.valueOf(150),
                10L,
                2000
        );

        when(hardDriveService.save(any(HardDrive.class)))
                .thenReturn(hdd);

        mockMvc.perform(post("/api/hdds/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hdd)))
                .andExpect(status().isOk());
    }
}