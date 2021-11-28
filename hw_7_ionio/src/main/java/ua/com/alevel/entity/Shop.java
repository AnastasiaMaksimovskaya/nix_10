package ua.com.alevel.entity;

import java.util.ArrayList;
import java.util.List;

public class Shop extends BaseEntity {

    private String address;
    private String name;
    private List<Integer> productsIds;

    public Shop() {
        productsIds = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<Integer> productsIds) {
        this.productsIds = productsIds;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id='" + super.getId() + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}