package com.distribuida.service;

import com.distribuida.db.Books;


import com.distribuida.dao.BooksDao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class BooksServiceImpl implements BooksService {

    @Inject
    private BooksDao repository;

    @Override
    public List<Books> findAll() {
        return repository.findAll();
    }

    @Override
    public Books findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void create(Books books) {

        repository.create(books);
    }

    @Override
    public void deleteByID(Integer id) {

        repository.deleteById(id);
    }


    @Override
    public void update(Integer id, Books books) {
        repository.update(id, books);
    }
}