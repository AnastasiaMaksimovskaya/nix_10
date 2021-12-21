package ua.com.alevel.view.dto.request;

import java.util.List;

public class ProductRequestDto extends RequestDto {
    private String productName;
    private String brand;
    private Integer price;
    private String shopName;
    private List<Integer> shopsId;

    public List<Integer> getShopsId() {
        return shopsId;
    }

    public void setShopsId(List<Integer> shopsId) {
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
