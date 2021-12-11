package ua.com.alevel.persistence.entity;

public class Product extends BaseEntity {

    private String name;
    private String brand;
    private int price;
    private String shopId;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + super.getId() + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", shopId=" + getShopId() +
                '}';
    }
}