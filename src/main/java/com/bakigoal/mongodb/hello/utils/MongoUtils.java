package com.bakigoal.mongodb.hello.utils;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

public class MongoUtils {

  private final static String HOST = "192.168.56.110";
  private final static int PORT = 27017;


  public static DB getDB(String dbName) throws UnknownHostException {
      // Connect to mongodb server on localhost
      MongoClient mongoClient = new MongoClient(HOST, PORT);
      return mongoClient.getDB(dbName);
  }
}
