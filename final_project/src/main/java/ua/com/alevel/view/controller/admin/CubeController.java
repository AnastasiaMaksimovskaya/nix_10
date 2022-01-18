package ua.com.alevel.view.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.CubeFacade;
import ua.com.alevel.facade.ShopFacade;
import ua.com.alevel.view.controller.BaseController;
import ua.com.alevel.view.dto.request.CubeRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.CubeResponseDto;

@Controller
@RequestMapping("/admin/cubes")
public class CubeController extends BaseController {
    private Long idToUpdate = 0L;
    private final CubeFacade cubeFacade;
    private final ShopFacade shopFacade;

    public CubeController(CubeFacade cubeFacade, ShopFacade shopFacade) {
        this.cubeFacade = cubeFacade;
        this.shopFacade = shopFacade;
    }
    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("Brand", "brand", "brand"),
            new HeaderName("Product name", "name", "name"),
            new HeaderName("Price", "price", "price"),
            new HeaderName("Amount of shops", null, null),
            new HeaderName("details", null, null),
            new HeaderName("update", null, null),
            new HeaderName("delete", null, null)
    };

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<CubeResponseDto> response = cubeFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/cubes/all");
        model.addAttribute("createNew", "/cubes/new");
        model.addAttribute("cardHeader", "All Cubes");
        return "pages/cube/admin/cube_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "admin/cubes");
    }

    @GetMapping("/new")
    public String redirectToNewAuthorPage(Model model, WebRequest request) {
        model.addAttribute("cube", new CubeRequestDto());
        model.addAttribute("shops", shopFacade.findAll(request));
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
    public String update(@ModelAttribute("cube") CubeRequestDto dto, @PathVariable Long id) {
        idToUpdate = id;
        return "pages/cube/admin/cube_update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("cube") CubeRequestDto dto) {
        cubeFacade.update(dto, idToUpdate);
        return "redirect:/admin/cubes";
    }

    @GetMapping("/details/{id}")
    public String redirectToNewAuthorPage(@PathVariable Long id, Model model) {
        model.addAttribute("cube", cubeFacade.findById(id));
        model.addAttribute("shops", cubeFacade.findAllShopsByProductId(id));
        return "pages/cube/admin/cube_details";
    }
}
