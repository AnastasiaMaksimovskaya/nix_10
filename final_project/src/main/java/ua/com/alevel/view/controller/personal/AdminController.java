package ua.com.alevel.view.controller.personal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.service.OrderService;

@Controller
@RequestMapping("/admin/dashboard")
public class AdminController {

    private final OrderService orderService;

    public AdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String details (Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user =orderService.findUserByEmail(auth.getName());
        model.addAttribute("admin",user);
        return "pages/admin/dashboard";
    }

}
