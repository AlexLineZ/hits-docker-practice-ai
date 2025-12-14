package testtask.shift.shopapi.mapper;

import org.springframework.stereotype.Component;
import testtask.shift.shopapi.dto.LaptopRequest;
import testtask.shift.shopapi.dto.LaptopResponse;
import testtask.shift.shopapi.model.laptop.Laptop;

@Component
public class LaptopMapper {
    public Laptop toEntity(LaptopRequest r) {
        return new Laptop(
                r.seriesNumber,
                r.producer,
                r.price,
                r.numberOfProductsInStock,
                r.size
        );
    }

    public LaptopResponse toResponse(Laptop e) {
        LaptopResponse r = new LaptopResponse();
        r.id = e.getId();
        r.seriesNumber = e.getSeriesNumber();
        r.producer = e.getProducer();
        r.price = e.getPrice();
        r.numberOfProductsInStock = e.getNumberOfProductsInStock();
        r.size = e.getSize();
        return r;
    }
}