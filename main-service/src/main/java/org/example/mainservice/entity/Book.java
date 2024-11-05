package org.example.mainservice.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "`book`")
@Access(value = AccessType.FIELD)
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Integer id;

    @Column(name = "`title`")
    private String title;

    @Column(name = "`author`")
    private String author;

    @Column(name = "`publisher`")
    private String publisher;

    @Column(name = "`introduction`")
    private String introduction;

    @Column(name = "`price`")
    private BigDecimal price;//bigdecimal

    @Column(name = "`stock`")
    private Integer stock;

    @Column(name = "`cover_image`")
    private String cover_image;

    @Column(name = "`isbn`")
    private String isbn;

    @Column(name="`deleted`")
    private Boolean deleted;

    public Book() {
    }

    public Book(Integer id, String title, String author, String publisher, String introduction, BigDecimal price, Integer stock, String cover_image, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.introduction = introduction;
        this.price = price;
        this.stock = stock;
        this.cover_image = cover_image;
        this.isbn = isbn;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", introduction='" + introduction + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", cover_image='" + cover_image + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
