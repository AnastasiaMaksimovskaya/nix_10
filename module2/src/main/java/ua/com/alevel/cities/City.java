package ua.com.alevel.cities;

import java.util.Arrays;

public class City {
    private int id;
    private int[] idOfNeighbours;
    private int[] pricesToNeighbour;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getIdOfNeighbours() {
        return idOfNeighbours;
    }

    public void setIdOfNeighbours(int[] idOfNeighbours) {
        this.idOfNeighbours = idOfNeighbours;
    }

    public int[] getPricesToNeighbour() {
        return pricesToNeighbour;
    }

    public void setPricesToNeighbour(int[] pricesToNeighbour) {
        this.pricesToNeighbour = pricesToNeighbour;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", idOfNeighbours=" + Arrays.toString(idOfNeighbours) +
                ", pricesToNeighbour=" + Arrays.toString(pricesToNeighbour) +
                ", name='" + name + '\'' +
                '}';
    }
}
