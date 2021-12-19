package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.ProductFacade;
import ua.com.alevel.facade.ShopFacade;
import ua.com.alevel.view.dto.request.ShopRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.ShopResponseDto;

@Controller
@RequestMapping("/shops")
public class ShopController extends BaseController {

    private Long idToUpdate = 0L;
    private final ProductFacade productFacade;
    private final ShopFacade shopFacade;
    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("Shop Name", "shop_name", "shop_name"),
            new HeaderName("Shop address", "address", "address"),
            new HeaderName("Amount of products", "productCount", "productCount"),
            new HeaderName("details", null, null),
            new HeaderName("update", null, null),
            new HeaderName("delete", null, null)
    };

    public ShopController(ProductFacade productFacade, ShopFacade shopFacade) {
        this.productFacade = productFacade;
        this.shopFacade = shopFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<ShopResponseDto> response = shopFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/shops/all");
        model.addAttribute("createNew", "/shops/new");
        model.addAttribute("cardHeader", "All Shops");
        return "pages/shop/shop_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "shops");
    }

    @GetMapping("/new")
    public String redirectToNewAuthorPage(Model model) {
        model.addAttribute("shop", new ShopRequestDto());
        return "pages/shop/shop_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("shop") ShopRequestDto dto) {
        shopFacade.create(dto);
        return "redirect:/shops";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        System.out.println("ShopController.delete");
        shopFacade.delete(id);
        return "redirect:/shops";
    }

    @GetMapping("/update/{id}")
    public String update(@ModelAttribute("shop") ShopRequestDto dto, @PathVariable Long id) {
        idToUpdate = id;
        return "pages/shop/shop_update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("shop") ShopRequestDto dto) {
        shopFacade.update(dto, idToUpdate);
        return "redirect:/shops";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("shop", shopFacade.findById(id));
        model.addAttribute("products", productFacade.findAllByShopId(id));
        return "pages/shop/shop_details";
    }
}
