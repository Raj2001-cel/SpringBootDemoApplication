package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonAcessDataService implements  PersonDao{
   ArrayList<Person> fakeDb = new ArrayList<>();
    @Override
    public int insertPerson(UUID id, Person person) {
       fakeDb.add(new Person(id,person.getName()));
       return 1;
    }

    @Override
    public List<Person> getAllPerson() {
        return fakeDb;
    }

    @Override
    public int deletePerson(UUID id) {
       Optional<Person> person = selectPersonById(id);

       if(!person.isEmpty()){
           fakeDb.remove(person.get());
           return 1;
       }

        return 0;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return fakeDb.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();

    }

    @Override
    public int updatePersonById(UUID id,Person person) {
       return  selectPersonById(id).map(p->{

           int ind = fakeDb.indexOf(p);

           if(ind!=-1){
               fakeDb.set(ind,person);
               return 1;
           }

           return 0;

        }).orElse(0);

    }
}