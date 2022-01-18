package ua.com.alevel.view.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.alevel.facade.PLPFacade;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.response.CubePLPDto;

import java.util.List;

@Controller
@RequestMapping("/cubes")
public class PLPController {

    private final PLPFacade plpFacade;

    public PLPController(PLPFacade plpFacade) {
        this.plpFacade = plpFacade;
    }

    @GetMapping
    private String allCubes(Model model, WebRequest webRequest) {
        System.out.println("PLPController.allCubes");
        for (CubePLPDto cubePLPDto : plpFacade.search(webRequest)
        ) {
            System.out.println("cubePLPDto = " + cubePLPDto);
        }
        model.addAttribute("cubeList", plpFacade.search(webRequest));
        return "pages/open/plp";
    }

    @GetMapping("/suggestions")
    private @ResponseBody
    List<String> allSearchBooks(@RequestParam String query) {
        return plpFacade.searchCubeName(query);
    }

    @PostMapping("/search")
    private String searchBooks(@RequestParam String query, RedirectAttributes ra) {
        ra.addAttribute(WebRequestUtil.SEARCH_CUBE_PARAM, query);
        return "redirect:/cubes";
    }
}
