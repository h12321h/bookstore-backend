package org.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Table(name = "`order_item`")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`book_id`")
    private Integer bookId;

    @Column(name = "`quantity`")
    private Integer quantity;

    @Column(name = "`price`")
    private Float price;

    @Column(name = "`order_id`", insertable = false, updatable = false)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "`order_id`", referencedColumnName = "`id`", nullable = false)
    private Order order;

    public OrderItem() {
    }

    public OrderItem(Integer id, Integer bookId, Integer quantity, Float price, Integer orderId) {
        this.id = id;
        this.bookId = bookId;
        this.quantity = quantity;
        this.price = price;
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
