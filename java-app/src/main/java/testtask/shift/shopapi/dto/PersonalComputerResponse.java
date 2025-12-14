package testtask.shift.shopapi.dto;

import testtask.shift.shopapi.model.pc.FormFactor;

import java.math.BigDecimal;

public class PersonalComputerResponse {
    public Long id;
    public String seriesNumber;
    public String producer;
    public BigDecimal price;
    public Long numberOfProductsInStock;
    public FormFactor formFactor;
}
