package ua.com.alevel.view.dto.request;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.type.OrderStatus;

import java.util.List;

@Setter
@Getter
public class OrderRequestDto extends RequestDto{
    private Long shopId;
    private OrderStatus status;
}
