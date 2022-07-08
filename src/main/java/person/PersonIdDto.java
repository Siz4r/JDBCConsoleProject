package main.java.person;

public class PersonIdDto {
    private final String name;
    private final int age;
    private final int weight;

    public PersonIdDto(String name, int age, int weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "PersonIdDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }
}
