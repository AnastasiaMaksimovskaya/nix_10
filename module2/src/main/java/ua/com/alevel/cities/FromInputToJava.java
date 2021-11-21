package ua.com.alevel.cities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FromInputToJava {


    public static void run() throws IOException {
        TheCheаpestPath theCheаpestPath = new TheCheаpestPath();
        Service service = new Service();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(".\\module2\\src\\main\\java\\ua\\com\\alevel\\cities\\input.txt"));
        StringBuilder stringBuilder = new StringBuilder();
        int cityCount = Integer.parseInt(bufferedReader.readLine());


        for (int i = 0; i < cityCount; i++) {
            City city = new City();
            city.setId(i + 1);
            city.setName(bufferedReader.readLine());
            int neighbourCount = Integer.parseInt(bufferedReader.readLine());
            int idOfNeighbours[] = new int[neighbourCount];
            int priceOfNeighbours[] = new int[neighbourCount];
            for (int j = 0; j < neighbourCount; j++) {
                String neighbourInfo [] = bufferedReader.readLine().split(" ");
                idOfNeighbours[j] = Integer.parseInt(neighbourInfo[0]);
                priceOfNeighbours[j] = Integer.parseInt(neighbourInfo[1]);
            }
            city.setPricesToNeighbour(priceOfNeighbours);
            city.setIdOfNeighbours(idOfNeighbours);
            service.add(city);
        }
        int methodCount = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < methodCount; i++) {
            String names[] = bufferedReader.readLine().split(" ");
            theCheаpestPath.findPath(service.findByName(names[0]),service.findByName(names[1]));
            System.out.println(service.findByName(names[1]).getPrice());

        }

    }
}
