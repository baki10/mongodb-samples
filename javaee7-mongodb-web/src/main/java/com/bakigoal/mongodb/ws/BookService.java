package com.bakigoal.mongodb.ws;

import com.bakigoal.mongodb.bean.Book;
import com.bakigoal.mongodb.controller.BookStore;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/bookstore")
public class BookService {
  @Inject
  private BookStore bookstore;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Book> query() {
    return bookstore.getListBooks();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response buyBook(Book b) {
    Book book = bookstore.checkAvailability(b);
    if (book == null) {
      return Response.ok("Book not found sorry!").build();
    }
    if (book.getCopies() > 0) {
      bookstore.buy(book);
      return Response.ok("Book purchased!").build();
    } else {
      return Response.ok("No more copies available, sorry !").build();
    }
  }
}
