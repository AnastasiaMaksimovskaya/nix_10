package ua.com.alevel.view.controller.admin;

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
import ua.com.alevel.view.dto.request.CubeRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.CubeResponseDto;

import java.util.Map;

@Controller
@RequestMapping("/admin/cubes")
public class CubeController extends BaseController {
    private final CubeFacade cubeFacade;
    private final ShopFacade shopFacade;
    private final BrandFacade brandFacade;

    public CubeController(CubeFacade cubeFacade, ShopFacade shopFacade, BrandFacade brandFacade) {
        this.cubeFacade = cubeFacade;
        this.shopFacade = shopFacade;
        this.brandFacade = brandFacade;
    }
    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("Марка", "brand", "brand"),
            new HeaderName("Название", "name", "name"),
            new HeaderName("Цена", "price", "price"),
            new HeaderName("Всего в наличии", "amount", "amount"),
            new HeaderName("Количество магазинов", null, null),
            new HeaderName("Детали", null, null),
            new HeaderName("Редактировать", null, null),
            new HeaderName("Удалить", null, null)
    };

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<CubeResponseDto> response = cubeFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/cubes/all");
        model.addAttribute("createNew", "/admin/cubes/new");
        model.addAttribute("cardHeader", "All Cubes");
        return "pages/cube/admin/cube_all";
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
        return new ModelAndView("redirect:/admin/cubes",model);
    }

    @GetMapping("/new")
    public String redirectToNewCubePage(Model model) {
        model.addAttribute("cube", new CubeRequestDto());
        model.addAttribute("shops", shopFacade.findAll());
        model.addAttribute("brands", brandFacade.findAll());
        model.addAttribute("categories", CubeCategory.values());
        return "pages/cube/admin/cube_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("cube") CubeRequestDto dto) {
        cubeFacade.create(dto);
        return "redirect:/admin/cubes";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        cubeFacade.delete(id);
        return "redirect:/admin/cubes";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id) {
        CubeResponseDto cubeResponseDto = cubeFacade.findById(id);
        CubeRequestDto cubeRequestDto = new CubeRequestDto();
        cubeRequestDto.setAmount(cubeResponseDto.getAmount());
        cubeRequestDto.setBrandId(cubeResponseDto.getBrand().getId());
        cubeRequestDto.setCategory(cubeResponseDto.getCubeCategory());
        cubeRequestDto.setImage(cubeResponseDto.getImage());
        cubeRequestDto.setDescription(cubeResponseDto.getDescription());
        cubeRequestDto.setPrice(cubeResponseDto.getPrice());
        cubeRequestDto.setProductName(cubeRequestDto.getProductName());
        cubeRequestDto.setShopsId(cubeRequestDto.getShopsId());
        model.addAttribute("id",id);
        model.addAttribute("cube", cubeRequestDto);
        model.addAttribute("shops", shopFacade.findAll());
        model.addAttribute("brands", brandFacade.findAll());
//        model.addAttribute("brandOfCube",cubeFacade.findById(id).getBrand());
//        model.addAttribute("categoryOfCube",cubeFacade.findById(id).getCubeCategory());
        model.addAttribute("categories", CubeCategory.values());
        return "pages/cube/admin/cube_update";
    }

    @PostMapping("/update")
    public String update(@RequestParam String id ,@ModelAttribute("cube") CubeRequestDto dto) {
        cubeFacade.update(dto, Long.parseLong(id));
        return "redirect:/admin/cubes";
    }

    @GetMapping("/details/{id}")
    public String redirectToNewCubePage(@PathVariable Long id, Model model) {
        model.addAttribute("cube", cubeFacade.findById(id));
        model.addAttribute("shops", cubeFacade.findAllShopsByProductId(id));
        return "pages/cube/admin/cube_details";
    }
}
