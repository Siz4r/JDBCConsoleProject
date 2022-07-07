package main.java.person;

public class PersonUpdateBuilder {
    private final PersonUpdateInput personUpdateInput = new PersonUpdateInput();


    public PersonUpdateBuilder setAge(int age) {
        personUpdateInput.setAge(age);
        return this;
    }

    public PersonUpdateBuilder setWeight(int weight) {
        personUpdateInput.setWeight(weight);
        return this;
    }

    public PersonUpdateInput build() {
        return personUpdateInput;
    }
}
