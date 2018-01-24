package com.arief.spring.daos;

import com.arief.spring.models.Person;

import java.util.List;

public interface PersonDAO {
    void save(Person p);
    List<Person> getAll();
    Person getOne(int personId);
    void update(Person p);
}
