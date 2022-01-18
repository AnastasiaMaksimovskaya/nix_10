package ua.com.alevel.view.dto.response;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.store.Brand;
import ua.com.alevel.persistence.entity.store.Cube;

import java.util.Date;

@Getter
@Setter
public class CubeResponseDto extends ResponseDto {

    private String name;
    private Brand brand;
    private Integer price;
    private Integer shopCount;
    private Date created;
    private Date updated;

    public CubeResponseDto() {
    }

    public CubeResponseDto(Cube cube) {
        setId(cube.getId());
        this.created = cube.getCreated();
        this.updated = cube.getUpdated();
        this.price = cube.getPrice();
        this.brand=cube.getBrand();
        this.shopCount = cube.getShops().size();
        this.name = cube.getName();
    }
}
