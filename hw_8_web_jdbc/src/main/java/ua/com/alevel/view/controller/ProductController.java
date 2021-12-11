package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.ProductFacade;
import ua.com.alevel.facade.ShopFacade;
import ua.com.alevel.view.dto.request.ProductRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.ProductResponseDto;

@Controller
@RequestMapping("/products")
public class ProductController extends BaseController{
    private final ProductFacade productFacade;
    private final ShopFacade shopFacade;
    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#", null, null),
            new HeaderName("Brand", null, null),
            new HeaderName("Product name", "productName", "product_name"),
            new HeaderName("Price", null, null),
            new HeaderName("details", null, null),
            new HeaderName("delete", null, null)
    };

    public ProductController(ProductFacade productFacade, ShopFacade shopFacade) {
        this.productFacade = productFacade;
        this.shopFacade = shopFacade;
    }


    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<ProductResponseDto> response = productFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/products/all");
        model.addAttribute("createNew", "/products/new");
        model.addAttribute("cardHeader", "All Products");
        return "pages/product/product_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "products");
    }

    @GetMapping("/new")
    public String redirectToNewAuthorPage(Model model) {
        model.addAttribute("product", new ProductRequestDto());
        return "pages/product/product_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("product") ProductRequestDto dto) {
        System.out.println("ProductController.create");
        productFacade.create(dto);
        return "redirect:/products";
    }

    @GetMapping("/details/{id}")
    public String redirectToNewAuthorPage(@PathVariable Long id, Model model) {
        model.addAttribute("product", productFacade.findById(id));
        model.addAttribute("shops", shopFacade.findAllByProductId(id));
        return "pages/product/product_details";
    }
}
