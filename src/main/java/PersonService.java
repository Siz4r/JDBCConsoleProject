package main.java;

import main.java.person.Person;
import main.java.person.PersonIdDto;
import main.java.person.PersonListDto;
import main.java.person.PersonUpdateInput;

import java.util.Set;
import java.util.stream.Collectors;

public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public int addPerson(Person person) {
        return personRepository.addPerson(person);
    }

    public void deleteAll() {
        personRepository.deleteAll();
    }

    public void deleteByID(int ID) {
        personRepository.deleteById(ID);
    }

    public int updatePerson(int ID, PersonUpdateInput input) {
        var person = personRepository.findByID(ID).orElseThrow(IncorrectIdInputException::new);

        if (input.getWeight() == -1) input.setWeight(person.getWeight());
        if (input.getAge() == -1) input.setAge(person.getAge());

        return personRepository.updatePerson(ID, input);
    }

    public Set<PersonListDto> findAll() {
        return personRepository.findAll().stream()
                .map(person -> new PersonListDto(person.getName(), person.getID()))
                .collect(Collectors.toSet());
    }

    public PersonIdDto findById(int ID) {
        var person = personRepository.findByID(ID).orElseThrow(IncorrectIdInputException::new);

        return new PersonIdDto(person.getName(), person.getAge(), person.getWeight());
    }

    public void fillDatabase() {
        personRepository.fillPersonsWithRecords();
    }
}
