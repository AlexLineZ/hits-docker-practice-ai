package testtask.shift.shopapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import testtask.shift.shopapi.dto.HardDriveRequest;
import testtask.shift.shopapi.dto.HardDriveResponse;
import testtask.shift.shopapi.mapper.HardDriveMapper;
import testtask.shift.shopapi.model.hdd.HardDrive;
import testtask.shift.shopapi.service.HardDriveService;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/hdds")
public class HardDriveController {
    private final HardDriveService hardDriveService;
    private final HardDriveMapper hardDriveMapper;

    public HardDriveController(HardDriveService hardDriveService, HardDriveMapper hardDriveMapper) {
        this.hardDriveService = hardDriveService;
        this.hardDriveMapper = hardDriveMapper;
    }

    @Operation(summary = "Get HDDs list")
    @GetMapping(value = {"", "/"}, produces = "application/json")
    public Iterable<HardDrive> getHardDrives() {
        return hardDriveService.getAllHardDrives();
    }

    @Operation(summary = "Get HDD by ID")
    @GetMapping(value = "/{id}", produces = "application/json")
    public HardDrive getHardDrive(@PathVariable long id) {
        return hardDriveService.getHardDrive(id);
    }

    @Operation(summary = "Create new HDD")
    @PostMapping(value = {"", "/", "/add"}, produces = "application/json")
    public HardDriveResponse createHardDrive(@Valid @RequestBody HardDriveRequest request) {
        HardDrive entity = hardDriveMapper.toEntity(request);
        HardDrive saved = hardDriveService.save(entity);
        return hardDriveMapper.toResponse(saved);
    }

    @Operation(summary = "Edit existing HDD")
    @PutMapping(value = "/{id}", produces = "application/json")
    public HardDriveResponse editHardDrive(@PathVariable long id,
                                           @Valid @RequestBody HardDriveRequest request) {
        hardDriveService.getHardDrive(id);

        HardDrive entity = hardDriveMapper.toEntity(request);
        entity.setId(id);

        HardDrive saved = hardDriveService.save(entity);
        return hardDriveMapper.toResponse(saved);
    }
}
