package com.example.demo.rest.api;

import com.example.demo.rest.exception.PersonNotFoundException;
import com.example.demo.rest.model.Person;
import com.example.demo.rest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public void addPerson(@Valid @NonNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "{id}")
    public Optional<Person> getPersonById(@PathVariable("id") UUID id) {
        Optional<Person> person = personService.getPersonById(id);

        if (person.isEmpty()) {
            throw new PersonNotFoundException("Person not found");
        }
        return person;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id) {
        personService.deletePerson(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Person personToUpdate) {
        personService.updatePerson(id, personToUpdate);
    }
}
