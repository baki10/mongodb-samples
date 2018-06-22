package com.bakigoal.mongodb.gson;

import com.bakigoal.mongodb.gson.adapter.CustomAdapter;
import com.bakigoal.mongodb.gson.model.Customer;
import com.bakigoal.mongodb.gson.model.CustomerInfo;
import com.bakigoal.mongodb.hello.utils.MongoUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class Insert {

  public static void main(String[] args) {
    try {
      DB db = MongoUtils.getDB("sampledb");
      DBCollection collection = db.getCollection("javastuff");

      Customer c = new Customer("123", "john", 22, "john@gmail.com", "777-666-555");
      Gson gson = new Gson();
      String json = gson.toJson(c);
      System.out.println(json);

      DBObject dbObject = (DBObject) JSON.parse(json);
      collection.insert(dbObject);

      BasicDBObject doc = new BasicDBObject("name", "owen")
          .append("info", new BasicDBObject("age", 25)
              .append("email", "owen@gmail.com")
              .append("phone", "321-456-778"));
      collection.insert(doc);
      DBObject obj = collection.findOne(doc);
      CustomerInfo customerInfo = gson.fromJson(obj.toString(),
          CustomerInfo.class);
      System.out.println("Found customer " + customerInfo);

      gson = new GsonBuilder().registerTypeAdapter(Customer.class, new CustomAdapter()).create();
      doc = new BasicDBObject("name", "owen");
      obj = collection.findOne(doc);
      c = gson.fromJson(obj.toString(), Customer.class);
      System.out.println("Found customer " + c);

    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
  }
}
