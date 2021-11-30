package ua.com.alevel.service;

public class ProductNotFoundException extends Throwable {
    @Override
    public String toString() {
        return "Нет такого продукта, что-то пошло не так";
    }
}
