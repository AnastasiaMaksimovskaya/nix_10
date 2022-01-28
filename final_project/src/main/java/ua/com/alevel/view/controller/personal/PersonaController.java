package ua.com.alevel.view.controller.personal;

import org.apache.commons.collections4.MapUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.*;
import ua.com.alevel.persistence.entity.store.Cube;
import ua.com.alevel.persistence.entity.store.Shop;
import ua.com.alevel.view.dto.request.OrderRequestDto;
import ua.com.alevel.view.dto.response.CubePLPDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/personal")
public class PersonaController {
    private final PLPFacade plpFacade;
    private final CubeFacade cubeFacade;
    private final ShopFacade shopFacade;
    private final BrandFacade brandFacade;
    private final OrderFacade orderFacade;


    public PersonaController(PLPFacade plpFacade, CubeFacade cubeFacade, ShopFacade shopFacade, BrandFacade brandFacade, OrderFacade orderFacade) {
        this.plpFacade = plpFacade;
        this.cubeFacade = cubeFacade;
        this.shopFacade = shopFacade;
        this.brandFacade = brandFacade;
        this.orderFacade = orderFacade;
    }

    @GetMapping
    private String allCubes(Model model, WebRequest webRequest) {
        PageData<CubePLPDto> response = plpFacade.search(webRequest);
        model.addAttribute("cubeList", response.getItems());
        model.addAttribute("pageData", response);
        model.addAttribute("brands", brandFacade.findAll());
        return "pages/cube/personal/cubes_all";
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
        return new ModelAndView("redirect:/personal", model);
    }

    @GetMapping("/buy/{id}")
    public ModelAndView addToCart(WebRequest request, ModelMap model, @PathVariable Long id) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach((key, value) -> {
                if (!key.equals("_csrf")) {
                    model.addAttribute(key, value);
                }
            });
        }
        if (plpFacade.getCart().stream().filter(cube -> Objects.equals(cube.getId(), id)).count() >0){
            return new ModelAndView("redirect:/personal/cart", model);
        }
        plpFacade.addToCart(id);
        return new ModelAndView("redirect:/personal", model);
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("order") OrderRequestDto dto) {
        orderFacade.create(dto);
        return "redirect:/personal";
    }

    @GetMapping("/cart")
    public String redirectToCartPage(Model model) {

        if (plpFacade.getCart().size()==0){
            return "pages/empty_cart";
        }

        shopFacade.findAll().stream().forEach(s-> System.out.println("s.getCubes() = " + s.getCubes()));

        List<Shop> shopList = shopFacade.findAll().stream().filter(shop ->  contain(plpFacade.getCart(),shop.getCubes())).collect(Collectors.toList());
        System.out.println("shopList = " + shopList);
        model.addAttribute("shops", shopList);
        model.addAttribute("cubes", plpFacade.getCart());
        model.addAttribute("order", new OrderRequestDto());
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication());
        return "pages/order/order_new";
    }
    private boolean contain(List<Cube> list, Set<Cube> set){
        List<Cube> listFromSet = new ArrayList<>(set);
        List<Long> idList = new ArrayList<>();
        List<Long> idListFromSet = new ArrayList<>();
        list.stream().forEach(cube -> idList.add(cube.getId()));
        listFromSet.stream().forEach(cube -> idListFromSet.add(cube.getId()));

        for (Long id:idList) {
            if (!idListFromSet.contains(id)){
                return false;
            }
        }
        return true;
    }
}
