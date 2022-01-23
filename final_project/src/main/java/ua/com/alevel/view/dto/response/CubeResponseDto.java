package ua.com.alevel.view.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.store.Brand;
import ua.com.alevel.persistence.entity.store.Cube;
import ua.com.alevel.persistence.entity.store.Shop;
import ua.com.alevel.persistence.type.CubeCategory;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
public class CubeResponseDto extends ResponseDto {

    private String name;
    private Brand brand;
    private Integer price;
    private Integer amount;
    private Integer shopCount;
    private Date created;
    private Date updated;
    private String image;
    private String description;
    private CubeCategory cubeCategory;
    private Set<Shop> shops;

    public CubeResponseDto() {
    }

    public CubeResponseDto(Cube cube) {
        setVisible(cube.getVisible());
        setId(cube.getId());
        this.shops =cube.getShops();
        this.cubeCategory =cube.getCubeCategory();
        this.description = cube.getDescription();
        this.image = cube.getImage();
        this.created = cube.getCreated();
        this.updated = cube.getUpdated();
        this.price = cube.getPrice();
        this.brand=cube.getBrand();
        this.shopCount = cube.getShops().size();
        this.name = cube.getName();
        this.amount = cube.getAmount();
    }
}
