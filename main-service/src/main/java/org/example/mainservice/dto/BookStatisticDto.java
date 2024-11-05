package org.example.mainservice.dto;

import java.math.BigDecimal;

public class BookStatisticDto {
    private Integer key;
    private String name;
    private Long quantity;
    private BigDecimal price;

    public BookStatisticDto() {
    }

    public BookStatisticDto(Integer key, String name, Long quantity, BigDecimal price) {
        this.key = key;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookStatisticDto{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
