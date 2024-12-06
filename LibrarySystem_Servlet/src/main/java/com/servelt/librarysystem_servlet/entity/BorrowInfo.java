package com.servelt.librarysystem_servlet.entity;

public class BorrowInfo {
    private String BorrowUsername;
    private String BorrowDate;
    private String bookname;

    @Override
    public String toString() {
        return "BorrowInfo{" +
                "BorrowUsername='" + BorrowUsername + '\'' +
                ", BorrowDate='" + BorrowDate + '\'' +
                ", bookname='" + bookname + '\'' +
                '}';
    }

    public String getBorrowUsername() {
        return BorrowUsername;
    }

    public void setBorrowUsername(String borrowUsername) {
        BorrowUsername = borrowUsername;
    }

    public String getBorrowDate() {
        return BorrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        BorrowDate = borrowDate;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public BorrowInfo(String borrowUsername, String borrowDate, String bookname) {
        BorrowUsername = borrowUsername;
        BorrowDate = borrowDate;
        this.bookname = bookname;
    }

    public BorrowInfo() {
    }
}
