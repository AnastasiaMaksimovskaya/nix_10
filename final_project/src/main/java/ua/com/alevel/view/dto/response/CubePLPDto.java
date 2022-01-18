package ua.com.alevel.view.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.store.Brand;
import ua.com.alevel.persistence.entity.store.Cube;

@Getter
@Setter
@ToString
public class CubePLPDto {

    private String name;
    private Brand brand;
    private Integer price;
    private String image;
    private Boolean visible;

    public CubePLPDto() {
    }

    public CubePLPDto(Cube cube) {
        this.price = cube.getPrice();
        this.visible = cube.getVisible();
        this.image = cube.getImage();
       this.brand = cube.getBrand();
        this.name = cube.getName();
    }
}
