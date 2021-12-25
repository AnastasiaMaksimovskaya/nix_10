package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Shop;

public class ShopResponseDto extends ResponseDto {

    private String name;
    private String address;
    private Integer productCount;

    public ShopResponseDto() {
    }

    public ShopResponseDto(Shop shop) {
        setId(shop.getId());
        setCreated(shop.getCreated());
        setUpdated(shop.getUpdated());
        this.productCount=shop.getProducts().size();
        this.name = shop.getName();
        this.address = shop.getAddress();
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
