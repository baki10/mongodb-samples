package com.bakigoal.mongodb.hello;

import com.bakigoal.mongodb.hello.utils.MongoUtils;
import com.mongodb.DB;

import java.net.UnknownHostException;

public class Hello {

  public static void main(String[] args) {
    try {
      DB db = MongoUtils.getDB("sampledb");
      for (String s : db.getCollectionNames()) {
        System.out.println(s);
      }
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
  }
}
