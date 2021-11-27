package ua.com.alevel;

import ua.com.alevel.cities.FromInputToJava;
import ua.com.alevel.datas.UniqueDatas;
import ua.com.alevel.exception.CityNotFoundException;
import ua.com.alevel.names.UniqueNames;

import java.io.IOException;

public class ModuleMain {
    public static void main(String[] args) {
        try {
            System.out.println("задание 1");
            UniqueDatas.run();
            System.out.println("выполнено");
            System.out.println("задание 2");
            UniqueNames.run();
            System.out.println("_________________________________");
            System.out.println("задание 3");
            FromInputToJava.run();
            System.out.println("_________________________________");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("не можем конвертировать стрроку в число: \n" +
                    "количество соседей должно совпадать с указанным значением" +
                    "количество всех городов должно совпадать с указанным значением");

        } catch (CityNotFoundException e) {
            System.out.println(e);
        }
    }
}
