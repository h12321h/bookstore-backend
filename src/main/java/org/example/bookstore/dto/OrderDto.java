package org.example.bookstore.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
public class OrderDto {
    private Integer id;
    private Date date;
    private String status;
    private BigDecimal totalPrice;

    private String username;

    private String name;
    private String address;
    private String phone;
    private List<OrderItemDto> items;

    public OrderDto() {
    }

public OrderDto(Integer id, Date date, String status, BigDecimal totalPrice, String name, String address, String phone, List<OrderItemDto> items) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.totalPrice = totalPrice;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.items = items;
    }


    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", totalPrice=" + totalPrice +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", items=" + items +
                '}';
    }
}
