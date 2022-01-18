package ua.com.alevel.view.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopRequestDto extends RequestDto {
    private String name;

    private String address;
}
