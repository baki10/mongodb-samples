package com.bakigoal.mongodb.hello;

import com.bakigoal.mongodb.hello.utils.MongoUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public class Update {

  public static void main(String args[]) {
    try {
      DB db = MongoUtils.getDB("sampledb");
      DBCollection collection = db.getCollection("javastuff");

      BasicDBObject newDocument = new BasicDBObject();
      newDocument.append("$set", new BasicDBObject().append("age", 23));
      BasicDBObject searchQuery = new BasicDBObject().append("name", "frank");
      collection.update(searchQuery, newDocument);

    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
  }
}