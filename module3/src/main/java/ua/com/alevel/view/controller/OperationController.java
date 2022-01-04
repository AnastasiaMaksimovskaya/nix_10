package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.OperationFacade;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.view.dto.request.OperationRequestDto;
import ua.com.alevel.view.dto.response.OperationResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.sql.SQLException;

@Controller
@RequestMapping("/operations")
public class OperationController extends BaseController {

    private Long accId=0L;

    private final OperationFacade operationFacade;
    private final AccountService accountService;

    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("Category Name", "name", "name"),
            new HeaderName("User Name", null, null),
            new HeaderName("Account Name", null, null),
            new HeaderName("Sum", "sum", "sum"),
            new HeaderName("details", null, null)
    };

    public OperationController(OperationFacade operationFacade, AccountService accountService) {
        this.operationFacade = operationFacade;
        this.accountService = accountService;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<OperationResponseDto> response = operationFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/operations/all");
        model.addAttribute("createNew", "/operations/new");
        model.addAttribute("cardHeader", "All Operations");
        return "pages/operation/operation_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "operations");
    }

    @GetMapping("/new/{id}")
    public String redirectToNewOperationPage(@PathVariable Long id, Model model) {
        accId= id;
        System.out.println("accId = " + accId);
        model.addAttribute("operation", new OperationRequestDto());
        return "pages/operation/operation_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("operation") OperationRequestDto dto) {
        dto.setAccount(accountService.findById(accId));
        System.out.println("OperationController.create");
        System.out.println("acc = " + accountService.findById(accId));
        try {
            operationFacade.create(dto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "redirect:/operations";
    }
}
