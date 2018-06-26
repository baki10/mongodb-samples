package com.bakigoal.jpa.controller;

import com.bakigoal.jpa.ejb.StoreManagerEJB;
import com.bakigoal.jpa.model.Customer;
import com.bakigoal.jpa.model.Item;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model
public class Manager {

  @Inject
  private StoreManagerEJB storeManager;

  private String customerId;
  private int orderQuantity;
  private int orderPrice;
  private String customerName;
  private String customerCountry;
  private String orderProduct;
  private List<Item> listOrders;
  private List<SelectItem> listCustomers;

  @PostConstruct
  public void init() {
    getListCustomers();
    if (!listCustomers.isEmpty()) {
      customerId = listCustomers.get(0).getValue().toString();
      listOrders = storeManager.findAllItems(customerId);
    }
  }

  public void findAllCustomers() {
    List<Customer> listCustomersEJB = storeManager.findAllCustomers();
    listCustomersEJB.forEach(customer ->
        listCustomers.add(new SelectItem(customer.getId(), customer.getName()))
    );
  }

  public void createCustomer() {
    storeManager.createCustomer(customerCountry, customerName);
    addMessage("Created Customer" + customerName + " from " + customerCountry);
    customerName = null;
    customerCountry = null;
    listCustomers = null;
  }

  private void addMessage(String message) {
    FacesMessage fm = new FacesMessage(message);
    FacesContext.getCurrentInstance().addMessage("Message", fm);
  }

  public void saveOrder() {
    storeManager.saveOrder(customerId, orderPrice, orderQuantity, orderProduct);
    addMessage("Saved order for" + orderQuantity + " of " + orderProduct + " and customer " + customerId);
    orderPrice = 0;
    orderQuantity = 0;
    orderProduct = null;
  }

  public void changeListener(ValueChangeEvent e) {
    customerId = e.getNewValue().toString();
    listOrders = storeManager.findAllItems(customerId);
  }

  public List<SelectItem> getListCustomers() {
    if (listCustomers == null) {
      listCustomers = new ArrayList<>();
      findAllCustomers();
    }
    return listCustomers;
  }

  // Getter-Setters
  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public int getOrderQuantity() {
    return orderQuantity;
  }

  public void setOrderQuantity(int orderQuantity) {
    this.orderQuantity = orderQuantity;
  }

  public int getOrderPrice() {
    return orderPrice;
  }

  public void setOrderPrice(int orderPrice) {
    this.orderPrice = orderPrice;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getCustomerCountry() {
    return customerCountry;
  }

  public void setCustomerCountry(String customerCountry) {
    this.customerCountry = customerCountry;
  }

  public String getOrderProduct() {
    return orderProduct;
  }

  public void setOrderProduct(String orderProduct) {
    this.orderProduct = orderProduct;
  }

  public List<Item> getListOrders() {
    return listOrders;
  }

  public void setListOrders(List<Item> listOrders) {
    this.listOrders = listOrders;
  }

  public void setListCustomers(List<SelectItem> listCustomers) {
    this.listCustomers = listCustomers;
  }
}
