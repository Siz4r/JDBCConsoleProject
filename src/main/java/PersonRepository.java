package main.java;

import main.java.person.Person;
import main.java.person.PersonIdDto;
import main.java.person.PersonUpdateInput;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PersonRepository {
    private final Connection con;

    public PersonRepository(Connection con) {
        this.con = con;
        clearDatabase();
        initializeDatabase();
    }

    public Set<Person> findAll() {
        var persons = new HashSet<Person>();
        try (var statement = con.prepareStatement("SELECT * FROM Persons")) {
            var result = statement.executeQuery();

            while (result.next()) {
                persons.add(Person.builder()
                        .setID(result.getInt("ID"))
                        .setName(result.getString("Name"))
                        .setAge(result.getInt("Age"))
                        .setWeight(result.getInt("Weight")).build());
            }

            return persons;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int addPerson(Person person) {
        try (var statement =
                     con.prepareStatement("INSERT INTO Persons" +
                             " (ID, name, age, weight) " +
                             "values (?, ?, ?, ?)")) {
            statement.setInt(1, person.getID());
            statement.setString(2, person.getName());
            statement.setInt(3, person.getAge());
            statement.setInt(4, person.getWeight());

            statement.executeUpdate();

            return person.getID();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updatePerson(int ID, PersonUpdateInput personUpdateInput) {
        try (var statement = con.prepareStatement("UPDATE persons" +
                " SET age = ?, weight = ? WHERE ID = ?")) {
            statement.setInt(1, personUpdateInput.getAge());
            statement.setInt(2, personUpdateInput.getWeight());
            statement.setInt(3, ID);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int ID) {
        try (var statement = con.prepareStatement("DELETE FROM Persons WHERE ID = ?")) {
            statement.setInt(1, ID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        try (var statement = con.prepareStatement("DELETE FROM Persons")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Person> findByID(int ID) {
        try(var statement = con.prepareStatement("SELECT * FROM Persons" +
                " WHERE ID = ?")) {
            statement.setInt(1, ID);

            var result = statement.executeQuery();

            if (result.next()) {
                return Optional.of(Person.builder()
                        .setID(result.getInt("ID"))
                        .setAge(result.getInt("Age"))
                        .setWeight(result.getInt("Weight"))
                        .setName(result.getString("Name")).build());
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
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
