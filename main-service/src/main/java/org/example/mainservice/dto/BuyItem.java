package org.example.mainservice.dto;

import java.math.BigDecimal;

public class BuyItem {
    private Integer bookId;
    private BigDecimal price;
    private Integer quantity;

    public BuyItem() {
    }

    public BuyItem(Integer bookId, BigDecimal price, Integer quantity) {
        this.bookId = bookId;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
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
        return "BuyItem{" +
                "bookId=" + bookId +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
