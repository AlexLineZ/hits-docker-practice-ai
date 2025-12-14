package testtask.shift.shopapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testtask.shift.shopapi.model.pc.PersonalComputer;
import testtask.shift.shopapi.repository.PersonalComputerRepository;

@Service
@Transactional
public class PersonalComputerServiceImpl
        extends BaseCrudService<PersonalComputer>
        implements PersonalComputerService {

    public PersonalComputerServiceImpl(PersonalComputerRepository repository) {
        super(repository, "PersonalComputer");
    }

    @Override
    public Iterable<PersonalComputer> getAllPersonalComputers() {
        return findAll();
    }

    @Override
    public PersonalComputer getPersonalComputer(long id) {
        return findById(id);
    }
}

