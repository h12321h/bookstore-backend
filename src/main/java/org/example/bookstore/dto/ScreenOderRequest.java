package org.example.bookstore.dto;

public class ScreenOderRequest {
    private String startDate;
    private String endDate;
    private String bookName;

    private Integer page;
    private Integer size;

    public ScreenOderRequest() {
    }

    public ScreenOderRequest(String startDate, String endDate, String bookName, Integer page, Integer size) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.bookName = bookName;
        this.page = page;
        this.size = size;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ScreenOderRequest{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", bookName='" + bookName + '\'' +
                ", page=" + page +
                ", size=" + size +
                '}';
    }
}
