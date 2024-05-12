package org.example.bookstore.dto;

import java.util.List;

public class AddOrderRequest {

    private Integer userId;
    private List<BuyItem> items;

    public AddOrderRequest() {
    }

    public AddOrderRequest(Integer userId, List<BuyItem> items) {
        this.userId = userId;
        this.items = items;
    }

    // Getters and Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<BuyItem> getItems() {
        return items;
    }

    public void setItems(List<BuyItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "AddOrderRequest{" +
                "userId=" + userId +
                ", items=" + items +
                '}';
    }

}
