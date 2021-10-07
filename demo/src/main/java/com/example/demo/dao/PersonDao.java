package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    int insertPerson(UUID id, Person person);
    default  int addPerson(Person person){
        UUID id  = UUID.randomUUID();
        return insertPerson(id,person);
    }

    List<Person> getAllPerson();

    int deletePerson(UUID id);

    Optional<Person> selectPersonById(UUID id);

    int updatePersonById(UUID id,Person person);
}
