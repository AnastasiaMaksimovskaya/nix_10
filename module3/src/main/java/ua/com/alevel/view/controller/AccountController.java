package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.view.dto.request.AccountRequestDto;
import ua.com.alevel.view.dto.response.AccountResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.UserResponseDto;

import java.sql.SQLException;

@Controller
@RequestMapping("/accounts")
public class AccountController extends BaseController {
    private Long idToUpdate = 0L;
    private Long idToCreate = 0L;
    private final UserFacade userFacade;
    private final AccountFacade accountFacade;

    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("Id", "id", "id"),
            new HeaderName("Name", "name", "name"),
            new HeaderName("Balance", "balance", "balance"),
            new HeaderName("User email", null, null),
            new HeaderName("details", null, null),
            new HeaderName("update", null, null),
            new HeaderName("delete", null, null)
    };

    public AccountController(UserFacade userFacade, AccountFacade accountFacade) {
        this.userFacade = userFacade;
        this.accountFacade = accountFacade;
    }


    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<AccountResponseDto> response = accountFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/accounts/all");
        model.addAttribute("createNew", "/accounts/new");
        model.addAttribute("cardHeader", "All Accounts");
        return "pages/account/account_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "accounts");
    }

    @GetMapping("/new/{id}")
    public String redirectToNewAccountPage(@PathVariable Long id, Model model, WebRequest request) {
        idToCreate = id;
        model.addAttribute("account", new AccountRequestDto());
        model.addAttribute("users", userFacade.findAll(request));
        return "pages/account/account_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("account") AccountRequestDto dto) {
        dto.setUserId(idToCreate);
        System.out.println("dto = " + dto);
        try {
            accountFacade.create(dto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "redirect:/accounts";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        accountFacade.delete(id);
        return "redirect:/accounts";
    }

    @GetMapping("/update/{id}")
    public String update(@ModelAttribute("account") AccountRequestDto dto, @PathVariable Long id) {
        idToUpdate = id;
        return "pages/account/account_update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("account") AccountRequestDto dto) {
        accountFacade.update(dto, idToUpdate);
        return "redirect:/accounts";
    }

    @GetMapping("/details/{id}")
    public String redirectToNewAuthorPage(@PathVariable Long id, Model model) {
        model.addAttribute("account", accountFacade.findById(id));
        model.addAttribute("user", accountFacade.findUserByAccountId(id));
        return "pages/account/account_details";
    }
}
