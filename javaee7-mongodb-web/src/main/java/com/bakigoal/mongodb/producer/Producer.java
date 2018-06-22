package com.bakigoal.mongodb.producer;

import com.bakigoal.mongodb.utils.MongoDbConfig;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class Producer {

  private static final Logger LOGGER = Logger.getLogger(Producer.class.getName());

  @Produces
  public MongoClient mongoClient() {
    try {
      return new MongoClient(MongoDbConfig.HOST, MongoDbConfig.PORT);
    } catch (UnknownHostException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    return null;
  }
}
