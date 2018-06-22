package com.bakigoal.mongodb.hello;

import com.bakigoal.mongodb.hello.utils.MongoUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;

public class Insert {

  public static void main(String args[]) {
    try {
      DB db = MongoUtils.getDB("sampledb");
      DBCollection collection = db.getCollection("javastuff");

      // insert object
      DBObject doc = new BasicDBObject("name", "owen")
          .append("age", 47)
          .append("email", "owen@mail.com")
          .append("phone", "111-222-333");
      collection.insert(doc);

      // insert embedded object
      DBObject doc2 = new BasicDBObject("name", "frank")
          .append("age", 31)
          .append("info",
              new BasicDBObject("email", "frank@mail.com")
                  .append("phone", "222-111-444")
          );
      collection.insert(doc2);

      // insert array
      List<DBObject> kids = new ArrayList<>();
      kids.add(new BasicDBObject("name", "mike"));
      kids.add(new BasicDBObject("name", "faye"));
      DBObject doc3 = new BasicDBObject("name", "john")
          .append("age", 35)
          .append("kids", kids)
          .append("info",
              new BasicDBObject("email", "john@mail.com")
                  .append("phone", "876-134-667"));
      collection.insert(doc3);

      // own _id
      DBObject doc4 = new BasicDBObject("_id", "12345678")
          .append("name", "jim")
          .append("age", 47)
          .append("info", new BasicDBObject("email",
              "owen@mail.com").
              append("phone", "111-222-333"));
      collection.insert(doc4);
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
  }
}