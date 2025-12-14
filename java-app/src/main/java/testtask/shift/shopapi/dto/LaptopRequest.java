package testtask.shift.shopapi.dto;

import testtask.shift.shopapi.model.laptop.LaptopSize;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class LaptopRequest {
    @NotBlank
    public String seriesNumber;

    @NotBlank
    public String producer;

    @NotNull
    @Positive
    public BigDecimal price;

    @NotNull @PositiveOrZero
    public Long numberOfProductsInStock;

    @NotNull
    public LaptopSize size;
}

