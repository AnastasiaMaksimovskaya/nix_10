package ua.com.alevel.view.controller.admin;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.ShopFacade;
import ua.com.alevel.persistence.type.WorkTimes;
import ua.com.alevel.view.controller.BaseController;
import ua.com.alevel.view.dto.request.ShopRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.ShopResponseDto;

import java.util.Map;

@Controller
@RequestMapping("/admin/shops")
public class ShopController extends BaseController {

    private final ShopFacade shopFacade;
    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("Название", "name", "name"),
            new HeaderName("Адрес", "address", "address"),
            new HeaderName("Количество кубов", null, null),
            new HeaderName("детали", null, null),
            new HeaderName("обновить", null, null),
            new HeaderName("удалить", null, null)
    };

    public ShopController(ShopFacade shopFacade) {
        this.shopFacade = shopFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<ShopResponseDto> response = shopFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/shops/all");
        model.addAttribute("createNew", "/admin/shops/new");
        model.addAttribute("cardHeader", "Все магазины");
        return "pages/shop/shop_all";
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
        return new ModelAndView("redirect:/admin/shops", model);
    }

    @GetMapping("/new")
    public String redirectToNewAuthorPage(Model model) {
        model.addAttribute("times", WorkTimes.values());
        model.addAttribute("shop", new ShopRequestDto());
        return "pages/shop/shop_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("shop") ShopRequestDto dto) {
        shopFacade.create(dto);
        return "redirect:/admin/shops";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        shopFacade.delete(id);
        return "redirect:/admin/shops";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id) {
        ShopResponseDto shopResponseDto = shopFacade.findById(id);
        ShopRequestDto shopRequestDto = new ShopRequestDto();
        shopRequestDto.setAddress(shopResponseDto.getAddress());
        shopRequestDto.setName(shopResponseDto.getName());
        shopRequestDto.setOpenTime(shopResponseDto.getOpenTime());
        shopRequestDto.setClosedTime(shopResponseDto.getClosedTime());
        model.addAttribute("id", id);
        model.addAttribute("shop", shopRequestDto);
        model.addAttribute("times", WorkTimes.values());
        return "pages/shop/shop_update";
    }

    @PostMapping("/update")
    public String update(@RequestParam Long id, @ModelAttribute("shop") ShopRequestDto dto) {
        shopFacade.update(dto, id);
        return "redirect:/admin/shops";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("shop", shopFacade.findById(id));
        model.addAttribute("cubes", shopFacade.findAllProductsByShopId(id));
        return "pages/shop/shop_details";
    }
}
