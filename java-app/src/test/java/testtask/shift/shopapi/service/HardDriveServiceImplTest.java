package testtask.shift.shopapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import testtask.shift.shopapi.model.hdd.HardDrive;
import testtask.shift.shopapi.repository.HardDriveRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HardDriveServiceImplTest {

    @Mock
    private HardDriveRepository hardDriveRepository;

    @InjectMocks
    private HardDriveServiceImpl hardDriveService;

    @Test
    void getHardDrive_existingId_returnsHardDrive() {
        HardDrive hdd = new HardDrive(
                1L, "HD1", "Seagate",
                BigDecimal.valueOf(120),
                20L,
                1000
        );

        when(hardDriveRepository.findById(1L))
                .thenReturn(Optional.of(hdd));

        HardDrive result = hardDriveService.getHardDrive(1L);

        assertThat(result).isEqualTo(hdd);
    }

    @Test
    void getHardDrive_notFound_throwsException() {
        when(hardDriveRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> hardDriveService.getHardDrive(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("HardDrive not found");
    }

    @Test
    void saveHardDrive_callsRepositorySave() {
        HardDrive hdd = new HardDrive(
                "HD2", "WD",
                BigDecimal.valueOf(150),
                15L,
                2000
        );

        when(hardDriveRepository.save(hdd))
                .thenReturn(hdd);

        HardDrive saved = hardDriveService.save(hdd);

        assertThat(saved).isEqualTo(hdd);
    }
}