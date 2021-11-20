package ua.com.alevel.exeption;

public class OutOfEraException extends Throwable {

    public OutOfEraException() {
    }

    @Override
    public String toString() {
        return "Вы вышли за пределы эры";
    }
}
