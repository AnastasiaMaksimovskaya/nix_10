package ua.com.alevel.service;

public class ShopNotFoundException extends Throwable {
    @Override
    public String toString() {
        return "Нет такого магазина, что-то пошло не так";
    }
}
