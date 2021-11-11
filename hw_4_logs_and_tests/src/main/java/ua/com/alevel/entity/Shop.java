package ua.com.alevel.entity;

public class Shop extends BaseEntity {

    private String address;
    private String name;
    private Product[] products;

    public Shop() {
        products = new Product[10];
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

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public void setProducts(Product product) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                products[i] = product;
                return;
            }
        }
        changeSize();
        setProducts(product);
    }

    public void changeSize() {
        Product[] newProducts = new Product[products.length * 2];
        for (int i = 0; i < products.length; i++) {
            newProducts[i] = products[i];
        }
        products = newProducts;
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