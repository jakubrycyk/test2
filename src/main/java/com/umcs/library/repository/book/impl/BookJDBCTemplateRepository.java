package com.umcs.library.repository.book.impl;


import com.umcs.library.model.Book;
import com.umcs.library.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class BookJDBCTemplateRepository implements BookRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Book findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM Book WHERE Id=?"
                , new Object[]{id}
                , new BookRawMapper());
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookRawMapper());
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM Book WHERE Id=?", id);
    }

    @Override
    public int insert(Book book) {
        return jdbcTemplate.update("INSERT INTO Book (Title, Author, IsLost, AddDate) VALUES  (?, ?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getIsLost(), LocalDate.now());
    }

    @Override
    public int update(Book book) {
        return jdbcTemplate.update("UPDATE Book SET Title = ?, Author = ?, IsLost = ? WHERE Id = ?",
                book.getTitle(), book.getAuthor(), book.getIsLost(), book.getId());
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("SELECT count(1) FROM Book", Integer.class);
    }

    class BookRawMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.fillFieldsFromResultSet(rs);
            return book;
        }
    }
}
