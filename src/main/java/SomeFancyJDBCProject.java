package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class SomeFancyJDBCProject {
    private final Connection con;
    private final DatabaseService databaseService;
    private final Scanner scanner = new Scanner(System.in);
    private final ConsoleMenu consoleMenu = new ConsoleMenu(scanner);

    public SomeFancyJDBCProject() {
        try {
            this.con = DriverManager.getConnection("jdbc:postgresql://localhost:8080/db",
                    "username", "password");
            this.databaseService = new DatabaseService(this.con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        try(con) {
//            loadPostgresDriver();
            var run = true;
            System.out.println("Welcome to fancy JDBC project! You have got here CRUD " +
                    "and some other operations!");
            while (run) {
                consoleMenu.showAvailableOptions();
                switch (consoleMenu.getNumber()) {
                    case 1 -> databaseService.getAll();
                    case 2 -> {
                        System.out.println("Type id: ");
                        databaseService.findByID(consoleMenu.getNumber());
                    }
                    case 3 -> databaseService.addPerson(consoleMenu.getPerson());
                    case 8 -> run = false;
                    default -> System.out.println("Choose the right option!");
                }
            }
        } catch (Exception e) {
            System.out.println("Something gone wrong, closing project!");
            e.printStackTrace();
        }
    }
//
//    private void loadPostgresDriver() {
//        try {
//            Class.forName("org.postgresql.Driver");
//
//        } catch (ClassNotFoundException e) {
//            System.out.println("Driver hasn't been found!");
//            e.printStackTrace();
//        }
//    }
}
