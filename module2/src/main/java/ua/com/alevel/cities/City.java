package ua.com.alevel.cities;

public class City {
    private int id;
    private int [] idOfNeighbours;
    private int [] pricesToNeighbour;
    private int price;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
}
