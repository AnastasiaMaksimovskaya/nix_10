package ua.com.alevel.exception;

public class CityNotFoundException extends Throwable {

    public CityNotFoundException() {
    }

    @Override
    public String toString() {
        return "Нет такого города, что-то пошло не так";
    }
}
