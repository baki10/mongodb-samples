package com.bakigoal.springdata.repository;

import com.bakigoal.springdata.repository.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

  Book findByTitle(String title);

  List<Book> findByType(String type);

  List<Book> findByAuthor(String author);
}
