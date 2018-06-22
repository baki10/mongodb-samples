package com.bakigoal.mongodb.hello;

import com.bakigoal.mongodb.hello.utils.MongoUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.util.List;

public class Query {

  public static void main(String args[]) {
    try {
      DB db = MongoUtils.getDB("sampledb");
      DBCollection collection = db.getCollection("javastuff");

      // count
      System.out.println("Count: " + collection.count());

      // find all lazy
      try (DBCursor cursor = collection.find()) {
        while (cursor.hasNext()) {
          DBObject object = cursor.next();
          System.out.println(object);
        }
      }

      // find first
      System.out.println();
      DBObject one = collection.findOne();
      System.out.println(one);

      // eagerly fetch to the list
      System.out.println();
      List<DBObject> list = collection.find().toArray();
      System.out.println(list);

      System.out.println();
      // filtering (SELECT * FROM javastuff WHERE name='owen')
      DBObject query = new BasicDBObject("name", "owen");
      try (DBCursor cursor = collection.find(query)) {
        while (cursor.hasNext()) {
          System.out.println(cursor.next());
        }
      }

      System.out.println();
      query = new BasicDBObject("name", new BasicDBObject("$ne", "frank"))
          .append("age", new BasicDBObject("$gt", 10));
      try (DBCursor cursor = collection.find(query)) {
        while (cursor.hasNext()) {
          System.out.println(cursor.next());
        }
      }
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
  }
}