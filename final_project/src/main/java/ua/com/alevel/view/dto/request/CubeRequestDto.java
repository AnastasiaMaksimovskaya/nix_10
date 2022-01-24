package ua.com.alevel.view.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.type.CubeCategory;

import java.util.Set;

@Getter
@Setter
@ToString
public class CubeRequestDto extends RequestDto {
    private String productName;
    private Integer price;
    private Integer amount;
    private String description;
    private String image;
    private String shopName;
    private Set<Long> shopsId;
    private Long brandId;
    private CubeCategory category;
}
