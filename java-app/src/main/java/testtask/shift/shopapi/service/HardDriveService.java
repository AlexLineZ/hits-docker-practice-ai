package testtask.shift.shopapi.service;

import org.springframework.validation.annotation.Validated;
import testtask.shift.shopapi.model.hdd.HardDrive;

import javax.validation.constraints.NotNull;

@Validated
public interface HardDriveService {
    @NotNull
    Iterable<HardDrive> getAllHardDrives();

    HardDrive getHardDrive(long id);

    HardDrive save(HardDrive hardDrive);
}
