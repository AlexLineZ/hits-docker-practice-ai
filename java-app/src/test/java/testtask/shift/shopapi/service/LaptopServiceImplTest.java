package testtask.shift.shopapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import testtask.shift.shopapi.model.laptop.Laptop;
import testtask.shift.shopapi.model.laptop.LaptopSize;
import testtask.shift.shopapi.repository.LaptopRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LaptopServiceImplTest {

    @Mock
    private LaptopRepository laptopRepository;

    @InjectMocks
    private LaptopServiceImpl laptopService;

    @Test
    void getLaptop_existingId_returnsLaptop() {
        Laptop laptop = new Laptop(
                1L, "SN1", "Dell",
                BigDecimal.valueOf(1000),
                10L,
                LaptopSize.Inch15
        );

        when(laptopRepository.findById(1L))
                .thenReturn(Optional.of(laptop));

        Laptop result = laptopService.getLaptop(1L);

        assertThat(result).isEqualTo(laptop);
        verify(laptopRepository).findById(1L);
    }

    @Test
    void getLaptop_notExistingId_throwsException() {
        when(laptopRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> laptopService.getLaptop(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Laptop not found");
    }

    @Test
    void saveLaptop_callsRepositorySave() {
        Laptop laptop = new Laptop(
                "SN2", "HP",
                BigDecimal.valueOf(900),
                5L,
                LaptopSize.Inch14
        );

        when(laptopRepository.save(laptop)).thenReturn(laptop);

        Laptop saved = laptopService.save(laptop);

        assertThat(saved).isEqualTo(laptop);
        verify(laptopRepository).save(laptop);
    }
}
