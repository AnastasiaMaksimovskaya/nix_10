package ua.com.alevel.exeption;

public class OutOfTenThousandException extends Throwable {
    public OutOfTenThousandException() {
    }

    @Override
    public String toString() {
        return "Вы вышли за пределы 10000 лет";
    }
}
