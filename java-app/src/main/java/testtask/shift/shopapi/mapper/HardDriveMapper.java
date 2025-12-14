package testtask.shift.shopapi.mapper;

import org.springframework.stereotype.Component;
import testtask.shift.shopapi.dto.HardDriveRequest;
import testtask.shift.shopapi.dto.HardDriveResponse;
import testtask.shift.shopapi.model.hdd.HardDrive;

@Component
public class HardDriveMapper {
    public HardDrive toEntity(HardDriveRequest r) {
        return new HardDrive(
                r.seriesNumber,
                r.producer,
                r.price,
                r.numberOfProductsInStock,
                r.capacity
        );
    }

    public HardDriveResponse toResponse(HardDrive e) {
        HardDriveResponse r = new HardDriveResponse();
        r.id = e.getId();
        r.seriesNumber = e.getSeriesNumber();
        r.producer = e.getProducer();
        r.price = e.getPrice();
        r.numberOfProductsInStock = e.getNumberOfProductsInStock();
        r.capacity = e.getCapacity();
        return r;
    }
}
