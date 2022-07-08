package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class SomeFancyJDBCProject {
    private final Connection con;
    private final PersonService personService;
    private final Scanner scanner = new Scanner(System.in);
    private final ConsoleMenu consoleMenu = new ConsoleMenu(scanner);

    public SomeFancyJDBCProject() {
        try {
            this.con = DriverManager.getConnection("jdbc:postgresql://localhost:8080/db",
                    "username", "password");
            this.personService = new PersonService(new PersonRepository(this.con));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        try(con) {
            var run = true;
            consoleMenu.welcomeMessage();
            while (run) {
                consoleMenu.showAvailableOptions();
                switch (consoleMenu.getNumber(false)) {
                    case 1 -> personService.findAll().forEach(System.out::println);
                    case 2 -> {
                        try {
                            System.out.println(personService.findById(consoleMenu.getNumber(true)));
                        } catch (IncorrectIdInputException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 3 -> personService.addPerson(consoleMenu.getPerson());
                    case 4 -> {
                        var id = consoleMenu.getNumber(true);
                        var input = consoleMenu.getPersonUpdateInput();
                        try {
                            personService.updatePerson(id, input);
                        } catch (IncorrectIdInputException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 5 -> personService.deleteByID(consoleMenu.getNumber(true));
                    case 6 -> personService.deleteAll();
                    case 7 -> personService.fillDatabase();
                    case 8 -> run = false;
                    default -> System.out.println("Choose the right option!");
                }
            }
        } catch (Exception e) {
            System.out.println("Something gone wrong, closing project!");
            e.printStackTrace();
        }
    }
}
