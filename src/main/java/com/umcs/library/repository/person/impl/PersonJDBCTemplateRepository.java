package com.umcs.library.repository.person.impl;


import com.umcs.library.model.Person;
import com.umcs.library.repository.person.PersonRepository;
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
public class PersonJDBCTemplateRepository implements PersonRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Person findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM Person WHERE id=?"
                , new Object[]{id}
                , new PersonRowMapper());
    }

    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM Person", new PersonRowMapper());
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    @Override
    public int insert(Person person) {
        return jdbcTemplate.update("INSERT INTO Person (FirstName, LastName, DOB, CreateDate) VALUES(?, ?, ?, ?, ?)",
                person.getId(), person.getFirstName(), person.getLastName(), Date.valueOf(person.getDob()), LocalDate.now());
    }

    @Override
    public int update(Person person) {
        return jdbcTemplate.update("UPDATE Person SET FirstName = ?, LastName = ?, DOB = ? WHERE id = ?",
                person.getFirstName(), person.getLastName(), Date.valueOf(person.getDob()), person.getId());
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("SELECT count(1) FROM Person", Integer.class);
    }

    class PersonRowMapper implements RowMapper<Person> {
        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.fillFieldsFromResultSet(rs);
            return person;
        }
    }
}
