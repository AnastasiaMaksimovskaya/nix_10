package ua.com.alevel.view.controller.admin;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.OrderFacade;
import ua.com.alevel.persistence.type.CubeCategory;
import ua.com.alevel.persistence.type.OrderStatus;
import ua.com.alevel.view.controller.BaseController;
import ua.com.alevel.view.dto.request.CubeRequestDto;
import ua.com.alevel.view.dto.request.OrderRequestDto;
import ua.com.alevel.view.dto.response.CubeResponseDto;
import ua.com.alevel.view.dto.response.OrderResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.Map;

@Controller
@RequestMapping("/admin/orders")
public class OrderController extends BaseController {

    private final OrderFacade orderFacade;

    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("Id", "id", "id"),
            new HeaderName("Почта покупателя", null, null),
            new HeaderName("Детали", null, null),
            new HeaderName("Удалить", null, null)
    };

    public OrderController(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<OrderResponseDto> response = orderFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/order/all");
//        model.addAttribute("createNew", "/admin/cubes/new");
        model.addAttribute("cardHeader", "Все заказы");
        return "pages/order/order_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach((key, value) -> {
                if (!key.equals("_csrf")) {
                    model.addAttribute(key, value);
                }
            });
        }
        return new ModelAndView("redirect:/admin/orders", model);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        orderFacade.delete(id);
        return "redirect:/admin/orders";
    }

    @PostMapping("/update")
    public String update(@RequestParam Long id, @ModelAttribute("order") OrderRequestDto dto) {
        orderFacade.update(dto, id);
        return "redirect:/admin/cubes";
    }

    @GetMapping("/details/{id}")
    public String redirectToNewOrderPage(@PathVariable Long id, Model model) {
        OrderResponseDto orderResponseDto = orderFacade.findById(id);
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setStatus(orderResponseDto.getStatus());
        model.addAttribute("id", id);
        model.addAttribute("order", orderResponseDto);
        model.addAttribute("newOrder", new OrderRequestDto());
        model.addAttribute("status", OrderStatus.values());
        return "pages/order/order_details";
    }
}
