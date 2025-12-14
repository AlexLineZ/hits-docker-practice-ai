package testtask.shift.shopapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import testtask.shift.shopapi.model.pc.FormFactor;
import testtask.shift.shopapi.model.pc.PersonalComputer;
import testtask.shift.shopapi.repository.PersonalComputerRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonalComputerServiceImplTest {

    @Mock
    private PersonalComputerRepository pcRepository;

    @InjectMocks
    private PersonalComputerServiceImpl pcService;

    @Test
    void getPersonalComputer_existingId_returnsPC() {
        PersonalComputer pc = new PersonalComputer(
                1L, "PC1", "HP",
                BigDecimal.valueOf(900),
                5L,
                FormFactor.Desktop
        );

        when(pcRepository.findById(1L))
                .thenReturn(Optional.of(pc));

        PersonalComputer result = pcService.getPersonalComputer(1L);

        assertThat(result).isEqualTo(pc);
    }

    @Test
    void getPersonalComputer_notFound_throwsException() {
        when(pcRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> pcService.getPersonalComputer(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("PersonalComputer not found");
    }

    @Test
    void savePersonalComputer_callsRepositorySave() {
        PersonalComputer pc = new PersonalComputer(
                "PC2", "Dell",
                BigDecimal.valueOf(1100),
                3L,
                FormFactor.Monoblock
        );

        when(pcRepository.save(pc))
                .thenReturn(pc);

        PersonalComputer saved = pcService.save(pc);

        assertThat(saved).isEqualTo(pc);
    }
}