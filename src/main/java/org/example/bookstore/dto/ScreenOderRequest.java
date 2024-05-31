package org.example.bookstore.dto;

public class ScreenOderRequest {
    private String startDate;
    private String endDate;
    private String bookName;

    public ScreenOderRequest() {
    }

    public ScreenOderRequest(String startDate, String endDate, String bookName) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.bookName = bookName;
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

    @Override
    public String toString() {
        return "ScreenOderRequest{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
