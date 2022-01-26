package ua.com.alevel.view.dto.response;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.store.Shop;

@Getter
@Setter
public class ShopResponseDto extends ResponseDto {

    private String name;
    private String address;
    private Integer productCount;
    private String openTime;
    private String closedTime;

    public ShopResponseDto() {
    }

    public ShopResponseDto(Shop shop) {
        setId(shop.getId());
        setCreated(shop.getCreated());
        setUpdated(shop.getUpdated());
        this.closedTime= shop.getClosedTime();
        this.openTime= shop.getOpenTime();
        this.productCount = shop.getCubes().size();
        this.name = shop.getName();
        this.address = shop.getAddress();
    }
}
