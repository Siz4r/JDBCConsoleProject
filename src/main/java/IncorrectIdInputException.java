package main.java;

public class IncorrectIdInputException extends RuntimeException{
    public IncorrectIdInputException() {
        super("There is no user with such ID!");
    }
}
