package ua.com.alevel.view.dto.request;

import com.neovisionaries.i18n.CountryCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandRequestDto extends RequestDto {

    private CountryCode country;
    private String name;

}
