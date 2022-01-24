package ua.com.alevel.view.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.store.Brand;

@Getter
@Setter
@ToString
public class BrandResponseDto extends ResponseDto{

    private String name;
    private String country;
    private Integer cubeAmount;

    public BrandResponseDto(){}

    public BrandResponseDto(Brand brand){
        this.country = brand.getCountry().getName();
        this.name = brand.getName();
        this.cubeAmount = brand.getCubes().size();
        setCreated(brand.getCreated());
        setUpdated(brand.getUpdated());
        setId(brand.getId());
        setVisible(true);
    }
}
