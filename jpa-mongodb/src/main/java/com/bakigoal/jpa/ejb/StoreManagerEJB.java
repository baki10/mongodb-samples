package com.bakigoal.jpa.ejb;

import com.bakigoal.jpa.model.Customer;
import com.bakigoal.jpa.model.Item;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class StoreManagerEJB {

  @PersistenceContext(unitName = "mongo-ogm")
  private EntityManager em;

  public void createCustomer(String country, String name) {
    Customer customer = new Customer();
    customer.setCountry(country);
    customer.setName(name);
    em.persist(customer);
  }

  public void saveOrder(String customerId,
                        int price, int quantity, String product) {
    Customer customer = findCustomerById(customerId);
    Item order = new Item();
    order.setCustomerFK(customer);
    order.setPrice(price);
    order.setQuantity(quantity);
    order.setProduct(product);
    em.persist(order);
  }

  public List<Item> findAllItems(String customerId) {
    Customer customer = findCustomerById(customerId);
    return customer.getItems();
  }

  public Customer findCustomerByName(String customerName) {
    return em.createQuery("FROM Customer where name=:name ", Customer.class)
        .setParameter("name", customerName).getSingleResult();
  }

  public Customer findCustomerById(String id) {
    return em.createQuery("FROM Customer where id=:id", Customer.class)
        .setParameter("id", id).getSingleResult();
  }

  public List<Customer> findAllCustomers() {
    return em.createQuery("FROM Customer", Customer.class).getResultList();
  }

  public List<Customer> queryNative() {
    Query nativeQuery = em.createNativeQuery("db.Customer.find({'country': 'RU'})", Customer.class);
    return nativeQuery.getResultList();
  }
}
