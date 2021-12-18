package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Product;

public class ProductResponseDto extends ResponseDto {


    private String name;
    private String brand;
    private Integer price;
    private Integer shopCount;

    public ProductResponseDto() {
    }

    public ProductResponseDto(Product product) {
        setId(product.getId());
        setCreated(product.getCreated());
        setUpdated(product.getUpdated());
        setVisible(product.getVisible());
        this.shopCount =product.getShopCount();
        this.name = product.getName();
        this.brand = product.getBrand();
        this.price = product.getPrice();
    }

    public Integer getShopCount() {
        return shopCount;
    }

    public void setShopCount(Integer shopCount) {
        this.shopCount = shopCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductResponseDto{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", shopsCount=" + shopCount +
                '}';
    }
}
