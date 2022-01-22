package ua.com.alevel.view.controller.personal;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.BrandFacade;
import ua.com.alevel.facade.CubeFacade;
import ua.com.alevel.facade.PLPFacade;
import ua.com.alevel.view.dto.response.CubePLPDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.Map;

@Controller
@RequestMapping("/personal")
public class PersonaController {
    private final PLPFacade plpFacade;
    private final CubeFacade cubeFacade;
    private final BrandFacade brandFacade;

    public PersonaController(PLPFacade plpFacade, CubeFacade cubeFacade, BrandFacade brandFacade) {
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
}
