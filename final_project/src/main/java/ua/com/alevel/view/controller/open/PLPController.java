package ua.com.alevel.view.controller.open;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.alevel.facade.BrandFacade;
import ua.com.alevel.facade.CubeFacade;
import ua.com.alevel.facade.PLPFacade;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.response.CubePLPDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;
import java.util.Map;

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
        model.addAttribute("pageData", response);
        model.addAttribute("brands", brandFacade.findAll());
        return "pages/open/plp";
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
        return new ModelAndView("redirect:/cubes", model);
    }

    @GetMapping("/suggestions")
    private @ResponseBody
    List<String> allSearchBooks(@RequestParam String query) {
        return plpFacade.searchCubeName(query);
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
