package ua.com.alevel.view.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.alevel.facade.BrandFacade;
import ua.com.alevel.facade.CubeFacade;
import ua.com.alevel.facade.PLPFacade;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.response.CubePLPDto;
import ua.com.alevel.view.dto.response.CubeResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;

@Controller
@RequestMapping("/cubes")
public class PLPController {

    private final PLPFacade plpFacade;
    private final CubeFacade cubeFacade;
    private final BrandFacade brandFacade;

    public PLPController(PLPFacade plpFacade, CubeFacade cubeFacade, BrandFacade brandFacade) {
        this.plpFacade = plpFacade;
        this.cubeFacade = cubeFacade;
        this.brandFacade = brandFacade;
    }

    @GetMapping
    private String allCubes(Model model, WebRequest webRequest) {
        PageData<CubePLPDto> response = plpFacade.search(webRequest);
        model.addAttribute("cubeList", response.getItems());
        model.addAttribute("pageData", response);
        model.addAttribute("brands", brandFacade.findAll());
        return "pages/open/plp";
    }

    @GetMapping("/suggestions")
    private @ResponseBody
    List<String> allSearchBooks(@RequestParam String query) {
        System.out.println("PLPController.allSearchBooks");
        return plpFacade.searchCubeName(query);
    }

    @GetMapping("/brand/{id}")
    String brandDetails(@PathVariable Long id) {
        return " ";
    }

    @GetMapping("/{id}")
    public String redirectToNewCubePage(@PathVariable Long id, Model model) {
        model.addAttribute("cube", cubeFacade.findById(id));
        model.addAttribute("shops", cubeFacade.findAllShopsByProductId(id));
        return "pages/open/cube_details";
    }

    @PostMapping("/search")
    private String searchBooks(@RequestParam String query, RedirectAttributes ra) {
        ra.addAttribute(WebRequestUtil.SEARCH_CUBE_PARAM, query);
        return "redirect:/cubes";
    }
}
