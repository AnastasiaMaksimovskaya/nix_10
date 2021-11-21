package ua.com.alevel;

import ua.com.alevel.cities.FromInputToJava;
import ua.com.alevel.datas.UniqueDatas;
import ua.com.alevel.names.UniqueNames;

import java.io.IOException;

public class ModuleMain {
    public static void main(String[] args) {
        try {
            //UniqueDatas.run();
           // UniqueNames.run();
            FromInputToJava.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
