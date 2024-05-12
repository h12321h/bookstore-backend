package org.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "`cart`")
@Access(value = AccessType.FIELD)
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Integer id;

    @Column(name = "`book_id`", insertable = false, updatable = false)
    private Integer bookId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "`book_id`", referencedColumnName = "`id`", nullable = false)
    private Book book;

    @Column(name = "`quantity`")
    private Integer quantity;

    @Column(name = "`user_id`")
    private Integer userId;

    public Cart() {
    }

    public Cart(int id, int book_id, int quantity, int user_id) {
        this.id = id;
        this.bookId = book_id;
        this.quantity = quantity;
        this.userId = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int book_id) {
        this.bookId = book_id;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user_id) {
        this.userId = user_id;
    }
}
