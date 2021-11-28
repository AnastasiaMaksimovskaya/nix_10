package ua.com.alevel;

public class ShopNotFoundException extends Throwable {
    @Override
    public String toString() {
        return "Нет такого магазиа, что-то пошло не так";
    }
}
