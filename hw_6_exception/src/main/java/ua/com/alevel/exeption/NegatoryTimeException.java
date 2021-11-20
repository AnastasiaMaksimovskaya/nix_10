package ua.com.alevel.exeption;

public class NegatoryTimeException extends Throwable {

    public NegatoryTimeException(){}

    @Override
    public String toString() {
        return "ввы ввели отрицательное время";
    }
}
