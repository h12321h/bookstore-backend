package org.example.mainservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "`cart`")
@Access(value = AccessType.FIELD)
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "`book_id`", referencedColumnName = "`id`", nullable = true)
    private Book book;

    @Column(name = "`quantity`")
    private Integer quantity;

//    @Column(name = "`user_id`")
//    private Integer userId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "`user_id`", referencedColumnName = "`id`", nullable = true)
    private User user;

    public Cart() {
    }

    public Cart(Integer id, Book book, Integer quantity, User user) {
        this.id = id;
        this.book = book;
        this.quantity = quantity;
        this.user = user;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int user_id) {
//        this.userId = user_id;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
