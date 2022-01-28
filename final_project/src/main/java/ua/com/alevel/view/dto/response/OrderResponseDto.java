package ua.com.alevel.view.dto.response;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.type.OrderStatus;

@Setter
@Getter
public class OrderResponseDto extends ResponseDto{
    private OrderStatus status;
    private String name;
    private String shopName;
    private String userEmail;

    public OrderResponseDto (Order order){
        setVisible(true);
        setCreated(order.getCreated());
        setUpdated(order.getUpdated());
        setId(order.getId());
        this.status = order.getOrderStatus();
        this.name = order.getName();
        this.userEmail = order.getUser().getEmail();
        this.shopName = order.getShop().getName();
    }

}
