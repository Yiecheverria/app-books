package com.distribuida.dao;


import com.distribuida.db.Books;
import io.helidon.dbclient.DbClient;
import io.helidon.dbclient.DbRow;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class BooksDaoImpl implements BooksDao {

    @Inject
    private DbClient dbClient;

    @Override
    public List<Books> findAll() {
        return dbClient.execute(dbExecute -> dbExecute
                        .createQuery("SELECT * FROM books")
                        .execute())
                .collectList()
                .await()
                .stream()
                .map(dbRow -> {
                    Integer id = dbRow.column(1).as(Integer.class);
                    String isbn = dbRow.column(2).as(String.class);
                    String title = dbRow.column(3).as(String.class);
                    Integer author_id = dbRow.column(4).as(Integer.class);
                    BigDecimal price = dbRow.column(5).as(BigDecimal.class);
                    return new Books(id, isbn, title, author_id, price);
                })
                .toList();
    }

    @SneakyThrows
    @Override
    public Books findById(Integer id) {
        return dbClient.execute(dbExecute -> dbExecute
                        .createGet("SELECT * FROM books WHERE id = ?")
                        .addParam(id)
                        .execute())
                .map(dbRow -> {
                    if (dbRow.isPresent()) {
                        DbRow row = dbRow.get();
                        return new Books(
                                row.column(1).as(Integer.class),
                                row.column(2).as(String.class),
                                row.column(3).as(String.class),
                                row.column(5).as(Integer.class),
                                row.column(4).as(BigDecimal.class)
                        );
                    } else {
                        return new Books();
                    }
                })
                .get();
    }

    @Override
    public void create(Books books) {
        dbClient.execute(dbExecute -> dbExecute
                .createInsert("INSERT INTO books (isbn, title, price, author_id) VALUES (?, ?, ?, ?)")
                .addParam(books.getIsbn())
                .addParam(books.getTitle())
                .addParam(books.getPrice())
                .addParam(books.getAuthor_id())
                .execute());
    }

    @Override
    public void deleteById(Integer id) {
        dbClient.execute(dbExecute -> dbExecute
                .createDelete("DELETE FROM books WHERE id = :id")
                .addParam("id", id)
                .execute());
    }

    @Override
    public void update(Integer id, Books books) {
        dbClient.execute(dbExecute -> dbExecute
                .createUpdate("UPDATE books SET isbn = :isbn, title = :title, price = :price, author_id = :author_id WHERE id = :id")
                .addParam("isbn", books.getIsbn())
                .addParam("title", books.getTitle())
                .addParam("price", books.getPrice())
                .addParam("author_id", books.getAuthor_id())
                .addParam("id", id)
                .execute());
    }

}