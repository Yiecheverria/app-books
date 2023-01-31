package com.distribuida.rest;


import com.distribuida.db.Books;
import com.distribuida.service.BooksService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BooksRest {

    @Inject
    private BooksService booksService;

    @GET
    public List<Books> findAll() {
        return booksService.findAll();
    }

    @GET
    @Path("/{id}")
    public Books findAll(@PathParam("id") Integer id) {
        return booksService.findById(id);
    }

    @POST
    public String saveBook(Books books) {
        booksService.create(books);
        return books.toString();
    }

    @DELETE
    @Path("/{id}")
    public String deleteBookById(@PathParam("id") Integer id) {
        booksService.deleteByID(id);
        return id.toString();
    }

}
