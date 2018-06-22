package com.bakigoal.mongodb.bean;

public class Book {

  private String title;
  private String author;
  private String type;
  private double price;
  private int copies;

  public Book() {
  }

  public Book(String title, String author, String type, double price) {
    super();
    this.title = title;
    this.author = author;
    this.type = type;
    this.price = price;
    this.copies = 10;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getCopies() {
    return copies;
  }

  public void setCopies(int copies) {
    this.copies = copies;
  }
}
