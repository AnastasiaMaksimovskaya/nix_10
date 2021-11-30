package ua.com.alevel.exception;

public class ShopNotFoundException extends Throwable {
    @Override
    public String toString() {
        return "Нет такого магазина, что-то пошло не так";
    }
}
