package ua.com.alevel.cities;

import java.util.ArrayList;
import java.util.List;

public class TheCheаpestPath {

    List<Integer> visitedCities = new ArrayList<>();

    public void findPath(City city1, City city2) {
        Service service = new Service();
        for (int i = 0; i < city1.getIdOfNeighbours().length; i++) {
            if (!isPresent(visitedCities, city1.getIdOfNeighbours()[i])) {
                if (service.findById(city1.getIdOfNeighbours()[i]).getPrice() == 0) {
                    service.findById(city1.getIdOfNeighbours()[i]).setPrice(city1.getPrice() + city1.getPricesToNeighbour()[i]);
                } else if (service.findById(city1.getIdOfNeighbours()[i]).getPrice() > city1.getPrice() + city1.getPricesToNeighbour()[i]) {
                    service.findById(city1.getIdOfNeighbours()[i]).setPrice(city1.getPrice() + city1.getPricesToNeighbour()[i]);
                }
            }
        }
        visitedCities.add(city1.getId());
        for (int i = 0; i < city1.getIdOfNeighbours().length; i++) {
            if (!isPresent(visitedCities, city1.getIdOfNeighbours()[i])) {
                if ((city1).equals(city2)) {
                    break;
                } else {
                    findPath(service.findById(city1.getIdOfNeighbours()[i]), city2);
                }
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
}
