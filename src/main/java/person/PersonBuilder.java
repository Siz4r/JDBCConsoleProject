package main.java.person;

public class PersonBuilder {
    private final Person person = new Person();

    public PersonBuilder setName(String name) {
        person.setName(name);
        return this;
    }

    public PersonBuilder setAge(int age) {
        person.setAge(age);
        return this;
    }

    public PersonBuilder setWeight(int weight) {
        person.setWeight(weight);
        return this;
    }

    public PersonBuilder setID(int ID) {
        person.setID(ID);
        return this;
    }

    public Person build() {
        return this.person;
    }
}
