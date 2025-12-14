package testtask.shift.shopapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import testtask.shift.shopapi.dto.PersonalComputerRequest;
import testtask.shift.shopapi.dto.PersonalComputerResponse;
import testtask.shift.shopapi.mapper.PersonalComputerMapper;
import testtask.shift.shopapi.model.pc.PersonalComputer;
import testtask.shift.shopapi.service.PersonalComputerService;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/pcs")
public class PersonalComputerController {
    private final PersonalComputerService personalComputerService;
    private final PersonalComputerMapper personalComputerMapper;

    public PersonalComputerController(PersonalComputerService personalComputerService,
                                      PersonalComputerMapper personalComputerMapper) {
        this.personalComputerService = personalComputerService;
        this.personalComputerMapper = personalComputerMapper;
    }

    @Operation(summary = "Get a personal computers list")
    @GetMapping(value = {"", "/"}, produces = "application/json")
    public Iterable<PersonalComputer> getPersonalComputers() {
        return personalComputerService.getAllPersonalComputers();
    }

    @Operation(summary = "Get PC by ID")
    @GetMapping(value = "/{id}", produces = "application/json")
    public PersonalComputer getPersonalComputer(@PathVariable long id) {
        return personalComputerService.getPersonalComputer(id);
    }

    @Operation(summary = "Create new PC")
    @PostMapping(value = {"", "/", "/add"}, produces = "application/json")
    public PersonalComputerResponse createPersonalComputer(@Valid @RequestBody PersonalComputerRequest request) {
        PersonalComputer entity = personalComputerMapper.toEntity(request);
        PersonalComputer saved = personalComputerService.save(entity);
        return personalComputerMapper.toResponse(saved);
    }

    @Operation(summary = "Edit existing PC")
    @PutMapping(value = "/{id}", produces = "application/json")
    public PersonalComputerResponse editPersonalComputer(@PathVariable long id,
                                                         @Valid @RequestBody PersonalComputerRequest request) {
        personalComputerService.getPersonalComputer(id);

        PersonalComputer entity = personalComputerMapper.toEntity(request);
        entity.setId(id);

        PersonalComputer saved = personalComputerService.save(entity);
        return personalComputerMapper.toResponse(saved);
    }
}