package com.bakigoal.springdata.repository;

import com.bakigoal.springdata.repository.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "book", path = "book")
public interface BookRepository extends MongoRepository<Book, String> {

  Book findByTitle(@Param("title") String title);

  List<Book> findByType(@Param("type") String type);

  List<Book> findByAuthor(@Param("author") String author);
}
