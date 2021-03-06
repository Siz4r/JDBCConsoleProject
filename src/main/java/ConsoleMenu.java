package main.java;

import main.java.person.Person;
import main.java.person.PersonUpdateInput;

import java.util.Scanner;

public class ConsoleMenu {
    private final Scanner scanner;

    public ConsoleMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getNumber(boolean isID) {
        int option = -1;
        try {
            while (option < 0) {

                if (isID) System.out.println("Type id: ");
                option = scanner.nextInt();
                if (option < 0) System.out.println("Wrong input, type again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return option;
    }

    public void showAvailableOptions() {
        System.out.println("Choose your option: ");
        System.out.println("1. Show all records");
        System.out.println("2. Find record by ID");
        System.out.println("3. Add new record");
        System.out.println("4. Update existing record");
        System.out.println("5. Delete record by ID");
        System.out.println("6. Delete all records");
        System.out.println("7. Fill database with basic records");
        System.out.println("8. Exit");
    }

    public void welcomeMessage() {
        System.out.println("Welcome to fancy JDBC project! You have got here CRUD " +
                "and some other operations!");
    }

    public Person getPerson() {
        System.out.println("Type person details: ");
        System.out.print("ID: ");
        var id = scanner.nextInt();

        if (scanner.hasNextLine()) scanner.nextLine();

        System.out.print("Name: ");
        var name = scanner.nextLine();

        System.out.print("Age: ");
        var age= scanner.nextInt();

        System.out.print("Weight: ");
        var weight= scanner.nextInt();

        return Person.builder().setID(id).setName(name).setWeight(weight).setAge(age).build();
    }

    public PersonUpdateInput getPersonUpdateInput() {
        System.out.println("Type new data (If you don't want to change record type -1): ");
        System.out.println("Age: ");
        var age= scanner.nextInt();

        System.out.print("Weight: ");
        var weight= scanner.nextInt();

        return PersonUpdateInput.builder().setAge(age).setWeight(weight).build();
    }
}
