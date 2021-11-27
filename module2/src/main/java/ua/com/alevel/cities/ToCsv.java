package ua.com.alevel.cities;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToCsv {

    static List<City> cities;

    ToCsv(List<City> c) {
        cities = c;
    }

    List<Integer> visitedCities = new ArrayList<>();
    List<String[]> tableLines = new ArrayList<>();

    public void fillTable() {
        for (int j = 0; j < cities.size(); j++) {
            for (int i = 0; i < cities.get(j).getIdOfNeighbours().length; i++) {
                String[] tableString = new String[3];
                tableString[0] = String.valueOf(cities.get(j).getId());
                tableString[1] = String.valueOf(cities.get(j).getIdOfNeighbours()[i]);
                tableString[2] = String.valueOf(cities.get(j).getPricesToNeighbour()[i]);
                if (!tableString[1].equals(tableString[0])) {
                    tableLines.add(tableString);
                }
                try (CSVWriter csvWriter = new CSVWriter(new FileWriter("cities.csv"))) {
                    csvWriter.writeAll(tableLines);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            visitedCities.add(cities.get(j).getId());
        }
    }
}
