package testtask.shift.shopapi.mapper;

import org.springframework.stereotype.Component;
import testtask.shift.shopapi.dto.PersonalComputerRequest;
import testtask.shift.shopapi.dto.PersonalComputerResponse;
import testtask.shift.shopapi.model.pc.PersonalComputer;

@Component
public class PersonalComputerMapper {
    public PersonalComputer toEntity(PersonalComputerRequest r) {
        return new PersonalComputer(
                r.seriesNumber,
                r.producer,
                r.price,
                r.numberOfProductsInStock,
                r.formFactor
        );
    }

    public PersonalComputerResponse toResponse(PersonalComputer e) {
        PersonalComputerResponse r = new PersonalComputerResponse();
        r.id = e.getId();
        r.seriesNumber = e.getSeriesNumber();
        r.producer = e.getProducer();
        r.price = e.getPrice();
        r.numberOfProductsInStock = e.getNumberOfProductsInStock();
        r.formFactor = e.getFormFactor();
        return r;
    }
}