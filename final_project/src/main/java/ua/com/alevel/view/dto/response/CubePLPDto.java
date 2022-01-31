package ua.com.alevel.view.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.store.Brand;
import ua.com.alevel.persistence.entity.store.Cube;

@Getter
@Setter
@ToString
public class CubePLPDto extends ResponseDto {

    private String name;
    private Brand brand;
    private Integer price;
    private String image;
    private String description;

    public CubePLPDto() {
    }

    public CubePLPDto(Cube cube) {
        setCreated(cube.getCreated());
        setUpdated(cube.getUpdated());
        this.description = cube.getDescription();
        setId(cube.getId());
        this.price = cube.getPrice();
        setVisible(cube.getVisible());
        this.image = cube.getImage();
        this.brand = cube.getBrand();
        this.name = cube.getName();
    }
}
