package ua.com.alevel.view.dto.request;

import java.util.Set;

public class ProductRequestDto extends RequestDto {
    private String productName;
    private String brand;
    private Integer price;
    private String shopName;
    private Set<Long> shopsId;

    public Set<Long> getShopsId() {
        return shopsId;
    }

    public void setShopsId(Set<Long> shopsId) {
        this.shopsId = shopsId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
