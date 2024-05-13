package org.example.bookstore.dto;

import java.util.List;

public class AddOrderRequest {

    private Integer userId;
    private List<BuyItem> items;

    private String name;

    private String address;

    private String phone;

    public AddOrderRequest() {
    }

    public AddOrderRequest(Integer userId, List<BuyItem> items, String name, String address, String phone) {
        this.userId = userId;
        this.items = items;
        this.name = name;
        this.address = address;
        this.phone = phone;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "AddOrderRequest{" +
                "userId=" + userId +
                ", items=" + items +
                ", name=" + name +
                ", address=" + address +
                ", phone=" + phone +
                '}';
    }
}
