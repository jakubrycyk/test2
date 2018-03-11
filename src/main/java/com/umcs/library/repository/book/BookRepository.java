package com.umcs.library.repository.book;


import com.umcs.library.model.Book;

import java.util.List;

public interface BookRepository {

    Book findById(int id);

    List<Book> findAll();

    int deleteById(int id);

    int insert(Book book);

    int update(Book book);

    int count();
}
