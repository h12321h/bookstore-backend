package org.example.mainservice.dto;

import org.example.mainservice.entity.Book;
import org.example.mainservice.entity.Tag;

import java.util.List;

public class TagAndBookDto {
    private List<Tag> tags;
    private List<Book> books;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "TagAndBookDto{" +
                "tags=" + tags +
                ", books=" + books +
                '}';
    }
}
