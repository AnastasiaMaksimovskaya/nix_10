package ua.com.alevel.facade.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.OrderFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.store.Cube;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.type.OrderStatus;
import ua.com.alevel.service.OrderService;
import ua.com.alevel.service.store.CubeService;
import ua.com.alevel.service.store.ShopService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.OrderRequestDto;
import ua.com.alevel.view.dto.response.OrderResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderFacadeImpl implements OrderFacade {

    private final OrderService orderService;
    private final ShopService shopService;
    private final CubeService cubeService;

    public OrderFacadeImpl(OrderService orderService, ShopService shopService, CubeService cubeService) {
        this.orderService = orderService;
        this.shopService = shopService;
        this.cubeService = cubeService;
    }

    @Override
    public void create(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setCreated(new Date(System.currentTimeMillis()));
        order.setUpdated(new Date(System.currentTimeMillis()));
        order.setShop(shopService.findById(orderRequestDto.getShopId()).get());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        order.setUser(orderService.findUserByEmail(auth.getName()));
        order.setOrderStatus(OrderStatus.WAIT_FOR_PROCESSING);
        Set<Cube> cubes = new HashSet<>();
        order.setCubes(cubes);
        orderService.create(order);
    }

    @Override
    public void update(OrderRequestDto orderRequestDto, Long id) {
        Order order = orderService.findById(id).get();
        order.setUpdated(new Date(System.currentTimeMillis()));
        order.setOrderStatus(orderRequestDto.getStatus());
        orderService.update(order);
    }

    @Override
    public void delete(Long id) {
        orderService.delete(id);
    }

    @Override
    public OrderResponseDto findById(Long id) {
        return new OrderResponseDto(orderService.findById(id).get());
    }

    @Override
    public PageData<OrderResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Order> tableResponse = orderService.findAll(dataTableRequest);

        List<OrderResponseDto> orders = tableResponse.getItems().stream().
                map(OrderResponseDto::new).
                collect(Collectors.toList());

        PageData<OrderResponseDto> pageData = (PageData<OrderResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(orders);
        return pageData;
    }
}
