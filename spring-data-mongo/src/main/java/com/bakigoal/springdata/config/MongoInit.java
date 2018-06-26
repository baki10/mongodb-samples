package com.bakigoal.springdata.config;

import com.bakigoal.springdata.repository.BookRepository;
import com.bakigoal.springdata.repository.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Configuration
public class MongoInit {

  public final static Book[] BOOKS = {
      new Book("A Tale Of Two Cities", "Charles Dickens", "Novel", 10),
      new Book("The Da Vinci Code", "Dan Brown", "thriller", 12),
      new Book("Think and Grow Rich", "Napoleon Hill", "Motivational", 10),
      new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", 8),
      new Book("Le Petit Prince", "Antoine de Saint - Exupery", "Novel", 8)
  };

  @Autowired
  private BookRepository repository;

  @PostConstruct
  public void init() {
    repository.deleteAll();
    Arrays.stream(BOOKS).forEach(book -> repository.save(book));
  }

}
