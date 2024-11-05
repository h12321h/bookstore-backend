package org.example.mainservice.dto;

import java.math.BigDecimal;

public class UserRangeDto {
    private Long range;
    private String username;
    private String contact;

    private BigDecimal price;

    public UserRangeDto() {
    }

    public UserRangeDto(Long range, String username, String contact, BigDecimal price) {
        this.range = range;
        this.username = username;
        this.contact = contact;
        this.price = price;
    }

    public Long getRange() {
        return range;
    }

    public void setRange(Long range) {
        this.range = range;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "UserRangeDto{" +
                "range=" + range +
                ", username='" + username + '\'' +
                ", contact='" + contact + '\'' +
                ", price=" + price +
                '}';
    }

}
