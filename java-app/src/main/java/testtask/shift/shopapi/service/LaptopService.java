package testtask.shift.shopapi.service;

import org.springframework.validation.annotation.Validated;
import testtask.shift.shopapi.model.laptop.Laptop;

import javax.validation.constraints.NotNull;

@Validated
public interface LaptopService {
    @NotNull
    Iterable<Laptop> getAllLaptops();

    Laptop getLaptop(long id);

    Laptop save(Laptop laptop);
}
