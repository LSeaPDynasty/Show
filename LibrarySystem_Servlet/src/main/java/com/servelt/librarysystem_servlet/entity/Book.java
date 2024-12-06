package com.servelt.librarysystem_servlet.entity;

public class Book {
    private String ISBN;
    private String BookName;
    private String Publish;
    private String Author;
    private String information;
    private String Quantity;
    private String Publishtime;

    public Book(String ISBN, String bookName, String aPublic, String author, String information, String quantity, String publishtime) {
        this.ISBN = ISBN;
        BookName = bookName;
        Publish = aPublic;
        Author = author;
        this.information = information;
        this.Quantity = quantity;
        Publishtime = publishtime;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getPublic() {
        return Publish;
    }

    public void setPublic(String aPublic) {
        Publish = aPublic;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        this.Quantity = quantity;
    }

    public String getPublishtime() {
        return Publishtime;
    }

    public void setPublishtime(String publishtime) {
        Publishtime = publishtime;
    }

    public Book() {
    }

    public String getBookname() {
        return BookName;
    }

    public String getPublish() {
        return Publish;
    }
}
