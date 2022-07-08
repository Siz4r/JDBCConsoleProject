package main.java.person;

public class PersonListDto {
    private final String name;
    private final int ID;

    public PersonListDto(String name, int ID) {
        this.name = name;
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "PersonListDto{" +
                "name='" + name + '\'' +
                ", ID=" + ID +
                '}';
    }
}
