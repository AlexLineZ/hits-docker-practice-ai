package testtask.shift.shopapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import testtask.shift.shopapi.mapper.PersonalComputerMapper;
import testtask.shift.shopapi.model.pc.FormFactor;
import testtask.shift.shopapi.model.pc.PersonalComputer;
import testtask.shift.shopapi.service.PersonalComputerService;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonalComputerController.class)
class PersonalComputerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonalComputerService pcService;

    @MockBean
    private PersonalComputerMapper personalComputerMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllPersonalComputers_returnsList() throws Exception {
        PersonalComputer pc = new PersonalComputer(
                1L, "PC1", "HP",
                BigDecimal.valueOf(900),
                5L,
                FormFactor.Desktop
        );

        when(pcService.getAllPersonalComputers())
                .thenReturn(List.of(pc));

        mockMvc.perform(get("/api/pcs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].formFactor").value("Desktop"));
    }

    @Test
    void createPersonalComputer_returnsSavedPC() throws Exception {
        PersonalComputer pc = new PersonalComputer(
                "PC2", "Dell",
                BigDecimal.valueOf(1100),
                3L,
                FormFactor.Monoblock
        );

        when(pcService.save(any(PersonalComputer.class)))
                .thenReturn(pc);

        mockMvc.perform(post("/api/pcs/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pc)))
                .andExpect(status().isOk());
    }
}