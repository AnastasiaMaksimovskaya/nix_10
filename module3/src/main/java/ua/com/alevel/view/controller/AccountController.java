package ua.com.alevel.view.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.facade.OperationFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.view.dto.request.AccountRequestDto;
import ua.com.alevel.view.dto.response.AccountResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

@Controller
@RequestMapping("/accounts")
public class AccountController extends BaseController {
    private Long idToUpdate = 0L;
    private Long idToCreate = 0L;
    private final UserFacade userFacade;
    private final AccountFacade accountFacade;
    private final OperationFacade operationFacade;

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

    public AccountController(UserFacade userFacade, AccountFacade accountFacade, OperationFacade operationFacade) {
        this.userFacade = userFacade;
        this.accountFacade = accountFacade;
        this.operationFacade = operationFacade;
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

    @GetMapping("/download/{id}")
    public ResponseEntity<InputStreamResource> getAnExtract(@PathVariable Long id)
    {
        operationFacade.writeOutByAccId(id);
        File file = new File("acc"+id+".csv");
        MediaType mediaType = MediaType.parseMediaType("application/octet-stream");

        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(mediaType)
                .contentLength(file.length())
                .body(resource);
    }
}
