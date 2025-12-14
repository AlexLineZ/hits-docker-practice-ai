package testtask.shift.shopapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@SuppressWarnings("PMD")
@MappedSuperclass
public abstract class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "seriesNumber must not be blank")
    @Column(nullable = false)
    private String seriesNumber;

    @NotBlank(message = "producer must not be blank")
    @Column(nullable = false)
    private String producer;

    @NotNull(message = "price must not be null")
    @Positive(message = "price must be positive")
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull(message = "numberOfProductsInStock must not be null")
    @PositiveOrZero(message = "numberOfProductsInStock must be >= 0")
    @Column(nullable = false)
    private Long numberOfProductsInStock;

    public Product() {
    }

    public Product(Long id, String seriesNumber, String producer, BigDecimal price, Long numberOfProductsInStock) {
        this.id = id;
        this.seriesNumber = seriesNumber;
        this.producer = producer;
        this.price = price;
        this.numberOfProductsInStock = numberOfProductsInStock;
    }

    public Product(String seriesNumber, String producer, BigDecimal price, Long numberOfProductsInStock) {
        this.seriesNumber = seriesNumber;
        this.producer = producer;
        this.price = price;
        this.numberOfProductsInStock = numberOfProductsInStock;
    }
}