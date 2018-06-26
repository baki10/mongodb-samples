package com.bakigoal.springdata;

import com.bakigoal.springdata.config.MongoInit;
import com.bakigoal.springdata.repository.BookRepository;
import com.bakigoal.springdata.repository.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataMongoApplicationTests {

  @Autowired
  private BookRepository repository;

  @Test
  public void test() {
    assertFalse(repository.findAll().isEmpty());
    assertEquals(repository.findAll().size(), 5);

    Book actualBook = MongoInit.BOOKS[1];

    Book book = repository.findByTitle(actualBook.getTitle());
    assertEquals(book.getPrice(), actualBook.getPrice());
    assertEquals(book.getType(), actualBook.getType());
  }
}
