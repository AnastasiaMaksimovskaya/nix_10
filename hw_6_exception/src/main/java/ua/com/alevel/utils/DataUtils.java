package ua.com.alevel.utils;

import java.util.HashMap;
import java.util.Map;

public class DataUtils {

    static Map<String, Integer> monthNames = new HashMap<>();
    static Map<Integer, Integer> monthAndDays = new HashMap<>();

    public static Map<String, Integer> getMonthNames() {
        return monthNames;
    }

    public static Map<Integer, Integer> getMonthAndDays() {
        return monthAndDays;
    }

    public static void fillMonthMap() {
        monthNames.put("Январь", 1);
        monthNames.put("Февраль", 2);
        monthNames.put("Март", 3);
        monthNames.put("Апрель", 4);
        monthNames.put("Май", 5);
        monthNames.put("Июнь", 6);
        monthNames.put("Июль", 7);
        monthNames.put("Август", 8);
        monthNames.put("Сентябрь", 9);
        monthNames.put("Октябрь", 10);
        monthNames.put("Ноябрь", 11);
        monthNames.put("Декабрь", 12);
    }

    public static boolean isBissextile(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 4 == 0 && year % 100 != 0) {
            return true;
        }
        return false;
    }

    public static void fillMonthDayMap() {
        monthAndDays.put(1, 31);
        monthAndDays.put(2, 28);
        monthAndDays.put(3, 31);
        monthAndDays.put(4, 30);
        monthAndDays.put(5, 31);
        monthAndDays.put(6, 30);
        monthAndDays.put(7, 31);
        monthAndDays.put(8, 31);
        monthAndDays.put(9, 30);
        monthAndDays.put(10, 31);
        monthAndDays.put(11, 30);
        monthAndDays.put(12, 31);
    }
}
