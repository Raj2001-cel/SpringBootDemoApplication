package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
/*
* this controller is api with talks with service
* now service talks with a class which has a databases work and has  already implemented
* a interface which as all the functionalities
* */

@RequestMapping("/api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@RequestBody @NotNull Person person){
        personService.addPerson(person);
        System.out.println("printing person json");
        System.out.println(person.getName());
    }

    @GetMapping
    public List<Person> getPeople(){
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
//        personService.deletePerson(id);
        return personService.getPersonById(id).orElse(null);
    }


    @DeleteMapping(path="{id}")
    public int deletePersonById(@PathVariable("id") UUID id){
        return personService.deletePerson(id) ;
    }

    @PutMapping(path = "{id}")
    public int updatePersonById(@PathVariable("id") UUID id, @NotNull @RequestBody Person newPerson){
       return  personService.updatePersonById(id,newPerson);
    }


}
