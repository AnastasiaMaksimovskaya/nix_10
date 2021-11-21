package ua.com.alevel.cities;


import java.util.ArrayList;
import java.util.List;

public class Service {
    private static List<City> cities= new ArrayList<>();


    public List<City> getCities() {
        return this.cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public void add(City city) {
                cities.add(city);
    }

    public City findById(int id) {
        return cities.stream()
                .filter(c -> c.getId()==id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("city not found"));
    }
    public City findByName(String name) {
        return cities.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("city not found"));
    }
}
