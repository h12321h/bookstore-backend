package org.example.bookstore.dto;

public class OrderItemDto {
    private String title;
    private Float price;
    private Integer quantity;

    public OrderItemDto() {
    }

    public OrderItemDto(String title, Float price, Integer quantity) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItemDto{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
