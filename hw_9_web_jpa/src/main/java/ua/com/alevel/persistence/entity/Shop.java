package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shops")
public class Shop extends BaseEntity {

    private String address;
    @Column(name = "shop_name")
    private String name;

    @ManyToMany(cascade= {
            CascadeType.PERSIST,
            CascadeType.REMOVE
    })
    @JoinTable(
            name = "shop_product",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))

    private Set<Product> products=new HashSet<>();

    public Shop(){
        super();
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void removeProduct(Product product) {
        product.getShops().remove(this);
        products.remove(product);
    }

    public void addProduct(Product product) {
        System.out.println(Shop.this);
        System.out.println("Shop.addProduct");
        products.add(product);
        product.getShops().add(this);
        System.out.println("product = " + product);
    }

    @Override
    public String toString() {
        return "Shop{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}