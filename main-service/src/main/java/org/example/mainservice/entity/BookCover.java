package org.example.mainservice.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book")
public class BookCover {
    @Id
    private Integer id;

    private String cover_image;

    public BookCover() {
    }

    public BookCover(Integer id, String cover_image) {
        this.id = id;
        this.cover_image = cover_image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    @Override
    public String toString() {
        return "BookCover{" +
                "id=" + id +
                ", cover_image='" + cover_image + '\'' +
                '}';
    }
}
