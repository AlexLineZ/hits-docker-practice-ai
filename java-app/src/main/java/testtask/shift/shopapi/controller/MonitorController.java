package testtask.shift.shopapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import testtask.shift.shopapi.dto.MonitorRequest;
import testtask.shift.shopapi.dto.MonitorResponse;
import testtask.shift.shopapi.mapper.MonitorMapper;
import testtask.shift.shopapi.model.monitor.Monitor;
import testtask.shift.shopapi.service.MonitorService;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/monitors")
public class MonitorController {
    private final MonitorService monitorService;
    private final MonitorMapper monitorMapper;

    public MonitorController(MonitorService monitorService, MonitorMapper monitorMapper) {
        this.monitorService = monitorService;
        this.monitorMapper = monitorMapper;
    }

    @Operation(summary = "Get monitors list")
    @GetMapping(value = {"", "/"}, produces = "application/json")
    public Iterable<Monitor> getMonitors() {
        return monitorService.getAllMonitors();
    }

    @Operation(summary = "Get monitor by ID")
    @GetMapping(value = "/{id}", produces = "application/json")
    public Monitor getMonitor(@PathVariable long id) {
        return monitorService.getMonitor(id);
    }

    @Operation(summary = "Create new monitor")
    @PostMapping(value = {"", "/", "/add"}, produces = "application/json")
    public MonitorResponse createMonitor(@Valid @RequestBody MonitorRequest request) {
        Monitor entity = monitorMapper.toEntity(request);
        Monitor saved = monitorService.save(entity);
        return monitorMapper.toResponse(saved);
    }

    @Operation(summary = "Edit existing monitor")
    @PutMapping(value = "/{id}", produces = "application/json")
    public MonitorResponse editMonitor(@PathVariable long id,
                                       @Valid @RequestBody MonitorRequest request) {
        monitorService.getMonitor(id);

        Monitor entity = monitorMapper.toEntity(request);
        entity.setId(id);

        Monitor saved = monitorService.save(entity);
        return monitorMapper.toResponse(saved);
    }
}