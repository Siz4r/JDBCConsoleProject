package main.java.person;

public class PersonUpdateInput {
    private int weight;
    private int age;

    public static PersonUpdateBuilder builder() {
        return new PersonUpdateBuilder();
    }
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
