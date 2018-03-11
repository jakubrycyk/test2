package com.umcs.library.repository.person;

import com.umcs.library.model.Person;

import java.util.List;

public interface PersonRepository {

    Person findById(int id);

    List<Person> findAll();

    int deleteById(int id);

    int insert(Person person);

    int update(Person person);

    int count();

}
