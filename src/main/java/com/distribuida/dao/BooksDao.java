package com.distribuida.dao;


import com.distribuida.db.Books;

import java.util.List;

public interface BooksDao {

    List<Books> findAll();
    Books findById(Integer id);
    void create(Books books);
    void deleteById(Integer id);
    void update(Integer id, Books books);


}
