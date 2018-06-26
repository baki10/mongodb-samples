package com.bakigoal.jpa.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Item {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  private int price;

  private String product;

  private int quantity;

  @ManyToOne
  @JoinColumn(name = "CUSTOMER_ID")
  private Customer customerFK;

  public Item() {
  }
  // getters & setters

  public String getId() {
    return id;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Customer getCustomerFK() {
    return customerFK;
  }

  public void setCustomerFK(Customer customerFK) {
    this.customerFK = customerFK;
  }
}
