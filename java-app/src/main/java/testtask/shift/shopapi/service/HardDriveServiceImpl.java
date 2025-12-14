package testtask.shift.shopapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testtask.shift.shopapi.model.hdd.HardDrive;
import testtask.shift.shopapi.repository.HardDriveRepository;

@Service
@Transactional
public class HardDriveServiceImpl
        extends BaseCrudService<HardDrive>
        implements HardDriveService {

    public HardDriveServiceImpl(HardDriveRepository repository) {
        super(repository, "HardDrive");
    }

    @Override
    public Iterable<HardDrive> getAllHardDrives() {
        return findAll();
    }

    @Override
    public HardDrive getHardDrive(long id) {
        return findById(id);
    }
}
