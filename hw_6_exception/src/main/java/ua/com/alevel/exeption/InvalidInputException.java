package ua.com.alevel.exeption;

public class InvalidInputException extends Throwable {

    public InvalidInputException() {}

    @Override
    public String toString() {
        return "Invalid input";
    }
}
