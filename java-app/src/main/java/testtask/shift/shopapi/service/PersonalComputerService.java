package testtask.shift.shopapi.service;

import org.springframework.validation.annotation.Validated;
import testtask.shift.shopapi.model.pc.PersonalComputer;

import javax.validation.constraints.NotNull;

@Validated
public interface PersonalComputerService {
    @NotNull
    Iterable<PersonalComputer> getAllPersonalComputers();

    PersonalComputer getPersonalComputer(long id);

    PersonalComputer save(PersonalComputer personalComputer);
}
