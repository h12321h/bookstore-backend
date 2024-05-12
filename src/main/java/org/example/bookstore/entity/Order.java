package org.example.bookstore.entity;

import org.example.bookstore.entity.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.example.bookstore.entity.OrderItem;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "`order`")
@Access(value = AccessType.FIELD)
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`order_date`")
    private Date orderDate;

    @Column(name = "`total_price`")
    private Float totalPrice;

    @Column(name = "`status`")
    private String status;

    @Column(name = "`user_id`", insertable = false, updatable = false)
    private Integer userId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "`user_id`", referencedColumnName = "`id`", nullable = false)
    private User user;

    public Order() {
    }

    public Order(Integer id, Date order_date, Float total_price, String status, Integer user_id) {
        this.id = id;
        this.orderDate = order_date;
        this.totalPrice = total_price;
        this.status = status;
        this.userId = user_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date order_date) {
        this.orderDate = order_date;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float total_price) {
        this.totalPrice = total_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer user_id) {
        this.userId = user_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
