package main.java.person;

public class Person {
    private int ID;
    private String name;
    private int age;
    private int weight;

    public String getName() {
        return name;
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public int getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Person{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }
}
