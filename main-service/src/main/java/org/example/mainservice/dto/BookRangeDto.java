package org.example.mainservice.dto;

public class BookRangeDto {
    private Long range;
    private String title;
    private Long quantity;

    public BookRangeDto() {
    }

    public BookRangeDto(Long range, String title, Long quantity) {
        this.range = range;
        this.title = title;
        this.quantity = quantity;
    }

    public Long getRange() {
        return range;
    }

    public void setRange(Long range) {
        this.range = range;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "BookRangeDto{" +
                "range=" + range +
                ", title='" + title + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
