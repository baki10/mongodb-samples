package com.bakigoal.mongodb.hello;

import com.bakigoal.mongodb.hello.utils.MongoUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;

public class Delete {

  public static void main(String args[]) {
    try {
      DB db = MongoUtils.getDB("sampledb");
      DBCollection collection = db.getCollection("javastuff");

      DBObject doc = collection.findOne();
      collection.remove(doc);

      BasicDBObject deleteQuery = new BasicDBObject();
      List<Integer> list = new ArrayList<>();
      for (int i = 0; i < 37; i++)
        list.add(i);
      deleteQuery.put("age", new BasicDBObject("$in", list));
      collection.remove(deleteQuery);

    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
  }
}