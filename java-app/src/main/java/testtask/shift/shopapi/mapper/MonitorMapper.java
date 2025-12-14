package testtask.shift.shopapi.mapper;

import org.springframework.stereotype.Component;
import testtask.shift.shopapi.dto.MonitorRequest;
import testtask.shift.shopapi.dto.MonitorResponse;
import testtask.shift.shopapi.model.monitor.Monitor;

@Component
public class MonitorMapper {
    public Monitor toEntity(MonitorRequest r) {
        return new Monitor(
                r.seriesNumber,
                r.producer,
                r.price,
                r.numberOfProductsInStock,
                r.diagonal
        );
    }

    public MonitorResponse toResponse(Monitor e) {
        MonitorResponse r = new MonitorResponse();
        r.id = e.getId();
        r.seriesNumber = e.getSeriesNumber();
        r.producer = e.getProducer();
        r.price = e.getPrice();
        r.numberOfProductsInStock = e.getNumberOfProductsInStock();
        r.diagonal = e.getDiagonal();
        return r;
    }
}
