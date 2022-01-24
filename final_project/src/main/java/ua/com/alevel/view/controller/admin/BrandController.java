package ua.com.alevel.view.controller.admin;

import com.neovisionaries.i18n.CountryCode;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.BrandFacade;
import ua.com.alevel.facade.CubeFacade;
import ua.com.alevel.facade.ShopFacade;
import ua.com.alevel.persistence.type.CubeCategory;
import ua.com.alevel.view.controller.BaseController;
import ua.com.alevel.view.dto.request.BrandRequestDto;
import ua.com.alevel.view.dto.request.CubeRequestDto;
import ua.com.alevel.view.dto.response.BrandResponseDto;
import ua.com.alevel.view.dto.response.CubeResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.Map;

@Controller
@RequestMapping("/admin/brands")
public class BrandController extends BaseController {
    private Long idToUpdate = 0L;
    private final CubeFacade cubeFacade;
    private final ShopFacade shopFacade;
    private final BrandFacade brandFacade;

    public BrandController(CubeFacade cubeFacade, ShopFacade shopFacade, BrandFacade brandFacade) {
        this.cubeFacade = cubeFacade;
        this.shopFacade = shopFacade;
        this.brandFacade = brandFacade;
    }
    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("Название", "name", "name"),
            new HeaderName("Всего кубов", null, null),
            new HeaderName("Страна изготовитель", null, null),
            new HeaderName("Детали", null, null),
            new HeaderName("Редактировать", null, null),
            new HeaderName("Удалить", null, null)
    };
    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<BrandResponseDto> response = brandFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/brands/all");
        model.addAttribute("createNew", "/admin/brands/new");
        model.addAttribute("cardHeader", "All Brands");
        return "pages/brand/brand_all";
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
        return new ModelAndView("redirect:/admin/brands",model);
    }

    @GetMapping("/new")
    public String redirectToNewCubePage(Model model) {
        model.addAttribute("brand", new BrandRequestDto());
        model.addAttribute("countries", CountryCode.values());
        return "pages/brand/brand_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("brand") BrandRequestDto dto) {
        brandFacade.create(dto);
        return "redirect:/admin/brands";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        brandFacade.delete(id);
        return "redirect:/admin/brands";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id,@ModelAttribute("brand") BrandRequestDto dto) {
//        model.addAttribute("shops", shopFacade.findAll());
//        model.addAttribute("brands", brandFacade.findAll());
//        model.addAttribute("brandOfCube",cubeFacade.findById(id).getBrand());
//        model.addAttribute("categoryOfCube",cubeFacade.findById(id).getCubeCategory());
//        model.addAttribute("categories", CubeCategory.values());
        idToUpdate = id;
        return "pages/brand/brand_update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("brand") BrandRequestDto dto) {
        brandFacade.update(dto, idToUpdate);
        return "redirect:/admin/brands";
    }

    @GetMapping("/details/{id}")
    public String redirectToNewCubePage(@PathVariable Long id, Model model) {
        model.addAttribute("brand",brandFacade.findById(id));
//        model.addAttribute("shops", cubeFacade.findAllShopsByProductId(id));
        return "pages/brand/brand_details";
    }
}
