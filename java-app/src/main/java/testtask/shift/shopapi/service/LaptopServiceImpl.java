package testtask.shift.shopapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testtask.shift.shopapi.model.laptop.Laptop;
import testtask.shift.shopapi.repository.LaptopRepository;

@Service
@Transactional
public class LaptopServiceImpl
        extends BaseCrudService<Laptop>
        implements LaptopService {

    public LaptopServiceImpl(LaptopRepository repository) {
        super(repository, "Laptop");
    }

    @Override
    public Iterable<Laptop> getAllLaptops() {
        return findAll();
    }

    @Override
    public Laptop getLaptop(long id) {
        return findById(id);
    }
}