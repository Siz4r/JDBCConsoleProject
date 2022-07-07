package main.java;

import main.java.person.Person;
import main.java.person.PersonUpdateInput;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseService {
    private final Connection con;

    public DatabaseService(Connection con) {
        this.con = con;
        clearDatabase();
        initializeDatabase();
    }

    public void getAll() {
        try (var statement = con.prepareStatement("SELECT * FROM Persons")) {
            var result = statement.executeQuery();

            while (result.next()) {
                System.out.println("ID: " + result.getInt("ID")
                        + ", Name: " +result.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPerson(Person person) {
        try (var statement =
                     con.prepareStatement("INSERT INTO Persons" +
                             " (ID, name, age, weight) " +
                             "values (?, ?, ?, ?)")) {
            statement.setInt(1, person.getID());
            statement.setString(2, person.getName());
            statement.setInt(3, person.getAge());
            statement.setInt(4, person.getWeight());

            if (statement.executeUpdate() == 1) {
                System.out.println("New record added to database!");
            } else {
                System.out.println("Error while adding new record to database");
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updatePerson(int ID, PersonUpdateInput personUpdateInput) {
        try (var statement = con.prepareStatement("UPDATE persons" +
                " SET age = ?, weight = ? WHERE ID = ?")) {
            findByID(ID);
            statement.setInt(1, personUpdateInput.getAge());
            statement.setInt(2, personUpdateInput.getWeight());
            statement.setInt(3, ID);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteById(int ID) {
        try (var statement = con.prepareStatement("DELETE FROM Persons WHERE ID = ?")) {
            statement.setInt(1, ID);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAll() {
        try (var statement = con.prepareStatement("DELETE FROM Persons")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void findByID(int ID) {
        try(var statement = con.prepareStatement("SELECT * FROM Persons" +
                " WHERE ID = ?")) {
            statement.setInt(1, ID);

            var result = statement.executeQuery();

            if (result.next()) {
                System.out.println("Person with id = " + ID);
                System.out.println(Person.builder()
                        .setName(result.getString("Name"))
                        .setAge(result.getInt("Age"))
                        .setWeight(result.getInt("Weight")).build());
            } else {
                System.out.println("There is no user with such Id!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void fillPersonsWithRecords() {
        try (var stmt = con.createStatement()) {
            stmt.executeUpdate("INSERT INTO Persons" +
                    "(ID, name, age, weight)" +
                    "VALUES (1, 'Janek Kowalski', 22, 75)");

            stmt.executeUpdate("INSERT INTO Persons" +
                    "(ID, name, age, weight)" +
                    "VALUES (2, 'Marek Marecki', 32, 85)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void initializeDatabase() {
        try (var stmt = con.createStatement()) {
            stmt.executeUpdate("CREATE TABLE Persons (" +
                    "ID INT NOT NULL," +
                    "name VARCHAR(45) NOT NULL ," +
                    "age INT NOT NULL ," +
                    "weight INT NOT NULL," +
                    "PRIMARY KEY (ID))");

            stmt.executeUpdate("INSERT INTO Persons" +
                    "(ID, name, age, weight)" +
                    "VALUES (1, 'Janek Kowalski', 22, 75)");

            stmt.executeUpdate("INSERT INTO Persons" +
                    "(ID, name, age, weight)" +
                    "VALUES (2, 'Marek Marecki', 32, 85)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void clearDatabase() {
        try (var stmt = con.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS Persons");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
