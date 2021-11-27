package ua.com.alevel.cities;

import ua.com.alevel.exception.CityNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheCheаpestPath {
    static List<City> cities;

    TheCheаpestPath(List<City> c) {
        cities = c;
    }

    List<Integer> visitedCities = new ArrayList<>();

    public static City findById(int id) throws CityNotFoundException {
        return cities.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new CityNotFoundException());
    }

    public static City findByName(String name) throws CityNotFoundException {
        return cities.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new CityNotFoundException());
    }

    Map<Integer, Integer> idAndCost = new HashMap<>();

    public void findPath(City city1, City city2) throws CityNotFoundException {
        for (int i = 0; i < city1.getIdOfNeighbours().length; i++) {
            if (!isPresent(visitedCities, city1.getId())) {
                if (!idAndCost.containsKey(city1.getIdOfNeighbours()[i])) {
                    if (idAndCost.get(city1.getId()) != null) {
                        idAndCost.put(city1.getIdOfNeighbours()[i], city1.getPricesToNeighbour()[i] + idAndCost.get(city1.getId()));
                    } else {
                        idAndCost.put(city1.getIdOfNeighbours()[i], city1.getPricesToNeighbour()[i]);
                    }
                } else {
                    if (idAndCost.get(city1.getIdOfNeighbours()[i]) > city1.getPricesToNeighbour()[i] + idAndCost.get(city1.getId())) {
                        idAndCost.put(city1.getIdOfNeighbours()[i], city1.getPricesToNeighbour()[i] + idAndCost.get(city1.getId()));
                    }
                }
            }
        }
        visitedCities.add(city1.getId());
        for (int i = 0; i < city1.getIdOfNeighbours().length; i++) {
            if (!isPresent(visitedCities, city1.getIdOfNeighbours()[i])) {
                findPath(findById(city1.getIdOfNeighbours()[i]), city2);
            }
        }
    }

    private boolean isPresent(List<Integer> list, int value) {
        for (int id : list) {
            if (id == value) {
                return true;
            }
        }
        return false;
    }

    protected static boolean isCorrectNeighbour(List<City> cities) throws CityNotFoundException {
        List<Integer> ids = new ArrayList<>();
        for (City city : cities) {
            for (int i = 0; i < city.getIdOfNeighbours().length; i++) {
                ids.add(city.getIdOfNeighbours()[i]);
            }
        }
        for (int i = 0; i < cities.size(); i++) {
            if (findById(i + 1).getIdOfNeighbours().length != countOfValues(i + 1, ids)) {
                return false;
            }
        }
        return true;
    }

    private static long countOfValues(int i, List<Integer> list) {
        return list.stream().filter(count -> count == i).count();
    }
}
