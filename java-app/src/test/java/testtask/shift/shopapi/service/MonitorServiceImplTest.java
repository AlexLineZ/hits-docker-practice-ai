package testtask.shift.shopapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import testtask.shift.shopapi.model.monitor.Monitor;
import testtask.shift.shopapi.repository.MonitorRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MonitorServiceImplTest {

    @Mock
    private MonitorRepository monitorRepository;

    @InjectMocks
    private MonitorServiceImpl monitorService;

    @Test
    void getMonitor_existingId_returnsMonitor() {
        Monitor monitor = new Monitor(
                1L, "MN1", "Samsung",
                BigDecimal.valueOf(300),
                10L,
                27.0
        );

        when(monitorRepository.findById(1L))
                .thenReturn(Optional.of(monitor));

        Monitor result = monitorService.getMonitor(1L);

        assertThat(result).isEqualTo(monitor);
        verify(monitorRepository).findById(1L);
    }

    @Test
    void getMonitor_notFound_throwsException() {
        when(monitorRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> monitorService.getMonitor(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Monitor not found");
    }

    @Test
    void saveMonitor_callsRepositorySave() {
        Monitor monitor = new Monitor(
                "MN2", "LG",
                BigDecimal.valueOf(250),
                5L,
                24.0
        );

        when(monitorRepository.save(monitor))
                .thenReturn(monitor);

        Monitor saved = monitorService.save(monitor);

        assertThat(saved).isEqualTo(monitor);
        verify(monitorRepository).save(monitor);
    }
}