package com.example.service;

import com.example.model.Person;
import com.example.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void addPerson(Person person){
        personRepository.save(person);
        System.out.println("Dodanie nowej osoby o id: " + person.getId());
    }

    public List<Person> getPersons(){
        return personRepository.findAll();
    }

    public Person getPerson(Long id){
        return personRepository.findById(id).orElse(null);
    }

//    public void editPerson(Person person, Long id){
//        personRepository.save(person);
//    }

    public void deletePerson(Long id){
        personRepository.deleteById(id);
    }





}
