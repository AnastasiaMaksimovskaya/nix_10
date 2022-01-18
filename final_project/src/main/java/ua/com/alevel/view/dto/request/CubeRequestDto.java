package ua.com.alevel.view.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CubeRequestDto extends RequestDto {
    private String productName;
    private String brand;
    private Integer price;
    private String shopName;
    private Set<Long> shopsId;
}
