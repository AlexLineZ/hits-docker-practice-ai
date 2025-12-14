package testtask.shift.shopapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import testtask.shift.shopapi.dto.LaptopRequest;
import testtask.shift.shopapi.dto.LaptopResponse;
import testtask.shift.shopapi.mapper.LaptopMapper;
import testtask.shift.shopapi.model.laptop.Laptop;
import testtask.shift.shopapi.service.LaptopService;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/laptops")
public class LaptopController {
    private final LaptopService laptopService;
    private final LaptopMapper laptopMapper;

    public LaptopController(LaptopService laptopService, LaptopMapper laptopMapper) {
        this.laptopService = laptopService;
        this.laptopMapper = laptopMapper;
    }

    @Operation(summary = "Get laptops list")
    @GetMapping(value = {"", "/"}, produces = "application/json")
    public Iterable<Laptop> getLaptops() {
        return laptopService.getAllLaptops();
    }

    @Operation(summary = "Get laptop by ID")
    @GetMapping(value = "/{id}", produces = "application/json")
    public Laptop getLaptop(@PathVariable long id) {
        return laptopService.getLaptop(id);
    }

    // Основной REST endpoint: POST /api/laptops
    @Operation(summary = "Create new laptop")
    @PostMapping(value = {"", "/", "/add"}, produces = "application/json")
    public LaptopResponse createLaptop(@Valid @RequestBody LaptopRequest request) {
        Laptop entity = laptopMapper.toEntity(request);
        Laptop saved = laptopService.save(entity);
        return laptopMapper.toResponse(saved);
    }

    @Operation(summary = "Edit existing laptop")
    @PutMapping(value = "/{id}", produces = "application/json")
    public LaptopResponse editLaptop(@PathVariable long id,
                                     @Valid @RequestBody LaptopRequest request) {
        // 404 if not found
        laptopService.getLaptop(id);

        Laptop entity = laptopMapper.toEntity(request);
        entity.setId(id);

        Laptop saved = laptopService.save(entity);
        return laptopMapper.toResponse(saved);
    }
}
