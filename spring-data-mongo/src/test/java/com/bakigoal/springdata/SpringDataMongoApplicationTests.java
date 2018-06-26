package com.bakigoal.springdata;

import com.bakigoal.springdata.repository.BookRepository;
import com.bakigoal.springdata.repository.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataMongoApplicationTests {

  private final static Book[] BOOKS = {
      new Book("A Tale Of Two Cities", "Charles Dickens", "Novel", 10),
      new Book("The Da Vinci Code", "Dan Brown", "thriller", 12),
      new Book("Think and Grow Rich", "Napoleon Hill", "Motivational", 10),
      new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", 8),
      new Book("Le Petit Prince", "Antoine de Saint - Exupery", "Novel", 8)
  };

  @Autowired
  private BookRepository repository;

  @Test
  public void test() {
    repository.deleteAll();
    assertTrue(repository.findAll().isEmpty());

    Arrays.stream(BOOKS).forEach(book -> repository.save(book));

    assertFalse(repository.findAll().isEmpty());
    assertEquals(repository.findAll().size(), 5);

    Book book = repository.findByTitle("The Da Vinci Code");
    assertEquals(book.getPrice(), 12);
    assertEquals(book.getType(), "thriller");
  }
}
