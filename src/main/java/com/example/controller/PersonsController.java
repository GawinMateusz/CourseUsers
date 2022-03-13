package com.example.controller;


import com.example.model.Person;
import com.example.repository.PersonRepository;
import com.example.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class PersonsController {

    private final PersonService personService;
    private final PersonRepository personRepository;

    public PersonsController(PersonService personService, PersonRepository personRepository) {
        this.personService = personService;
        this.personRepository = personRepository;
    }


    @RequestMapping(value = {"/persons"}, method = RequestMethod.GET)
    public String viewPersonList(Model model){
        List<Person> personList = personService.getPersons();
        model.addAttribute("person",personList);
        return "persons/personList";
    }
    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.GET)
    public String viewAddPerson(){
        return "persons/addNewPerson";
    }

    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.POST)
    public RedirectView postAddPerson(@ModelAttribute Person newPerson){
        personService.addPerson(newPerson);
        return new RedirectView("/persons");
    }
    @RequestMapping(value = {"/editPerson/{id}"}, method = RequestMethod.GET)
    public String viewEditPerson(Model model, @PathVariable("id") Long id){
        Person person = personService.getPerson(id);
        model.addAttribute("person",person);
        return "persons/editPerson";
    }

    @RequestMapping(value = {"/addPerson/{id}"}, method = RequestMethod.POST)
    public RedirectView postEditPerson(@ModelAttribute Person newPerson, @PathVariable("id") Long id){
        personRepository.save(newPerson);
        return new RedirectView("/persons");
    }

    @RequestMapping(value = {"/editPerson/{id}"}, method = RequestMethod.POST)
    public RedirectView deletePerson(@PathVariable("id") Long id){
        personService.deletePerson(id);
        return new RedirectView("/persons");
    }


}
