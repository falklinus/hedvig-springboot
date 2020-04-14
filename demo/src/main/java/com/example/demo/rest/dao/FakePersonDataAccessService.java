package com.example.demo.rest.dao;

import com.example.demo.rest.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> DB = new ArrayList<>(List.of(
            new Person(UUID.randomUUID(), "James Bond"),
            new Person(UUID.randomUUID(), "Nelson Mandela"),
            new Person(UUID.randomUUID(), "Ana Jones"),
            new Person(UUID.randomUUID(), "Lillbabs")));

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if(personMaybe.isEmpty()) {
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person updatedPerson) {
        return selectPersonById(id)
                .map(person -> {
                    int indexOfPerson = DB.indexOf(person);
                    if (indexOfPerson >= 0) {
                        DB.set(indexOfPerson, new Person(id, updatedPerson.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
