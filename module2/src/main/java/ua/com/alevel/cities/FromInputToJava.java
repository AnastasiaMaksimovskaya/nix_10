package ua.com.alevel.cities;

import ua.com.alevel.exception.CityNotFoundException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FromInputToJava {

    public static void run() throws IOException, CityNotFoundException {
        List<City> citiesTest = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(".\\src\\main\\java\\ua\\com\\alevel\\cities\\input.txt"));
        int cityCount = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < cityCount; i++) {
            City city = new City();
            city.setId(i + 1);
            city.setName(bufferedReader.readLine());
            int neighbourCount = Integer.parseInt(bufferedReader.readLine());
            int[] idOfNeighbours = new int[neighbourCount];
            int[] priceOfNeighbours = new int[neighbourCount];
            for (int j = 0; j < neighbourCount; j++) {
                String[] neighbourInfo = bufferedReader.readLine().split(" ");
                idOfNeighbours[j] = Integer.parseInt(neighbourInfo[0]);
                priceOfNeighbours[j] = Integer.parseInt(neighbourInfo[1]);
            }
            city.setPricesToNeighbour(priceOfNeighbours);
            city.setIdOfNeighbours(idOfNeighbours);
            citiesTest.add(city);
        }
        TheCheаpestPath theCheаpestPath = new TheCheаpestPath(citiesTest);

        if (theCheаpestPath.isCorrectNeighbour(citiesTest)) {
            ToCsv toCsv = new ToCsv(citiesTest);
            toCsv.fillTable();
            IsCorrect isCorrect = new IsCorrect();
            if (!isCorrect.check()) {
                System.out.println("цена из пункта 1 в пункт 2 должна быть одинаковой");
                return;
            }
            int methodCount = Integer.parseInt(bufferedReader.readLine());
            for (int i = 0; i < methodCount; i++) {
                TheCheаpestPath theCheаpestPath1 = new TheCheаpestPath(citiesTest);
                String names[] = bufferedReader.readLine().split(" ");
                theCheаpestPath1.findPath(theCheаpestPath1.findByName(names[0]), theCheаpestPath1.findByName(names[1]));
                if (theCheаpestPath1.idAndCost.get(theCheаpestPath1.findByName(names[1]).getId()) != null) {
                    System.out.println("путь от " + names[0] + " до " + names[1] + " стоит минимум");
                    System.out.println(theCheаpestPath1.idAndCost.get(theCheаpestPath1.findByName(names[1]).getId()) + " шекеля");
                    System.out.println();
                } else {
                    System.out.println("нельзя проехать ;(");
                    System.out.println("от " + names[0] + " до " + names[1]);
                    System.out.println();
                }
            }
        } else {
            System.out.println("Неправильный ввод соседей");
        }
    }
}
