package org.example.bookstore.entity;

import org.example.bookstore.entity.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.example.bookstore.entity.OrderItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "`order`")
@Access(value = AccessType.FIELD)
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`order_date`")
    private Date orderDate;

    @Column(name = "`total_price`")
    private BigDecimal totalPrice;

    @Column(name = "`status`")
    private String status;

    @Column(name="`name`")
    private String name;

    @Column(name="`address`")
    private String address;

    @Column(name="`phone`")
    private String phone;

//    @Column(name = "`user_id`", insertable = false, updatable = false)
//    private Integer userId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "`user_id`", referencedColumnName = "`id`", nullable = false)
    private User user;

    public Order() {
    }

    public Order(Integer id, Date order_date, BigDecimal total_price, String status, String name, String address, String phone) {
        this.id = id;
        this.orderDate = order_date;
        this.totalPrice = total_price;
        this.status = status;
        this.name = name;
        this.address = address;
        this.phone = phone;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal total_price) {
        this.totalPrice = total_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer user_id) {
//        this.userId = user_id;
//    }

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
