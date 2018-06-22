package com.bakigoal.mongodb.controller;

import com.bakigoal.mongodb.bean.Book;
import com.bakigoal.mongodb.utils.MongoDbConfig;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class BookStore {

  @Inject
  private MongoClient mongoClient;

  private List<Book> listBooks;
  private String filter;

  @PostConstruct
  private void init() {
    updateBooks();
  }

  public List<Book> getListBooks() {
    return listBooks;
  }

  public String getFilter() {
    return filter;
  }

  public void setFilter(String filter) {
    this.filter = filter;
  }

  public void updateBooks() {
    listBooks = query();
  }

  public List<Book> query() {
    Gson gson = new Gson();
    DBCollection collection = getDbCollection();

    List<Book> list = new ArrayList<>();
    try (DBCursor cursor = getCursor(collection)) {
      while (cursor.hasNext()) {
        DBObject obj = cursor.next();
        list.add(gson.fromJson(obj.toString(), Book.class));
      }
    }
    return list;
  }

  public void buy(Book book) {
    System.out.println("Buy book!!!!!" + book);
    Gson gson = new Gson();
    int copiesLeft = book.getCopies() - 1;
    DBCollection collection = getDbCollection();
    BasicDBObject newDocument = new BasicDBObject();
    newDocument.append("$set", new BasicDBObject().append("copies", copiesLeft));
    DBObject searchQuery = (DBObject) JSON.parse(gson.toJson(book));
    collection.update(searchQuery, newDocument);
    updateBooks();
  }

  private DBCollection getDbCollection() {
    DB db = mongoClient.getDB(MongoDbConfig.DB_NAME);
    return db.getCollection(MongoDbConfig.COLLECTION_NAME);
  }

  private DBCursor getCursor(DBCollection collection) {
    if (filter == null || filter.trim().length() == 0) {
      return collection.find();
    } else {
      DBObject q = new BasicDBObject();
      q.put("title", java.util.regex.Pattern.compile(filter));
      return collection.find(q);
    }
  }
}
