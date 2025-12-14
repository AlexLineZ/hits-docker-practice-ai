package testtask.shift.shopapi.dto;

import testtask.shift.shopapi.model.laptop.LaptopSize;

import java.math.BigDecimal;

public class LaptopResponse {
    public Long id;
    public String seriesNumber;
    public String producer;
    public BigDecimal price;
    public Long numberOfProductsInStock;
    public LaptopSize size;
}