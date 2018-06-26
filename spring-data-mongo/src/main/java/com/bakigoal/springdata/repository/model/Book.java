package com.bakigoal.springdata.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "mybooks")
public class Book {

  @Id
  private String id;
  @Field("booktitle")
  private String title;
  private String author;
  private String type;
  private int price;

  public Book(String title, String author, String type, int price) {
    this.title = title;
    this.author = author;
    this.price = price;
    this.type = type;
  }

  @Override
  public String toString() {
    return "Book{" + "id=" + id + ", title=" + title + ", author="
        + author + ", price=" + price + "}";
  }

  public String getId() {
    return id;
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

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }
}
