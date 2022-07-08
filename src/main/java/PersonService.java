package main.java;

import main.java.person.Person;

public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public int addPerson(Person person) {

    }
}
