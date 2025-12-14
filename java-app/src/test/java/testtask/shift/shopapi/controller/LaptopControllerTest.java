package testtask.shift.shopapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import testtask.shift.shopapi.mapper.LaptopMapper;
import testtask.shift.shopapi.model.laptop.Laptop;
import testtask.shift.shopapi.model.laptop.LaptopSize;
import testtask.shift.shopapi.service.LaptopService;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LaptopController.class)
class LaptopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LaptopService laptopService;

    @MockBean
    private LaptopMapper laptopMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllLaptops_returnsList() throws Exception {
        Laptop laptop = new Laptop(
                1L, "SN1", "Lenovo",
                BigDecimal.valueOf(1200),
                3L,
                LaptopSize.Inch15
        );

        when(laptopService.getAllLaptops())
                .thenReturn(List.of(laptop));

        mockMvc.perform(get("/api/laptops"))
                .andExpect(status().isOk());
    }

    @Test
    void getLaptopById_returnsLaptop() throws Exception {
        Laptop laptop = new Laptop(
                1L, "SN1", "Apple",
                BigDecimal.valueOf(2000),
                2L,
                LaptopSize.Inch13
        );

        when(laptopService.getLaptop(1L)).thenReturn(laptop);

        mockMvc.perform(get("/api/laptops/1"))
                .andExpect(status().isOk());
    }

    @Test
    void createLaptop_returnsSavedLaptop() throws Exception {
        Laptop laptop = new Laptop(
                "SN3", "Asus",
                BigDecimal.valueOf(800),
                7L,
                LaptopSize.Inch14
        );

        when(laptopService.save(any()))
                .thenReturn(laptop);

        mockMvc.perform(post("/api/laptops/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(laptop)))
                .andExpect(status().isOk());
    }
}

