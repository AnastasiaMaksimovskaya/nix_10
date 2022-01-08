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
import ua.com.alevel.facade.OperationFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.view.dto.request.UserRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.UserResponseDto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private Long idToUpdate = 0L;
    private final UserFacade userFacade;
    private final OperationFacade operationFacade;

    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("Name", "name", "name"),
            new HeaderName("Email", "email", "email"),
            new HeaderName("Amount of accounts", null, null),
            new HeaderName("details", null, null),
            new HeaderName("update", null, null),
            new HeaderName("delete", null, null)
    };

    public UserController(UserFacade userFacade, OperationFacade operationFacade) {
        this.userFacade = userFacade;
        this.operationFacade = operationFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<UserResponseDto> response = userFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/users/all");
        model.addAttribute("createNew", "/users/new");
        model.addAttribute("cardHeader", "All Users");
        return "pages/user/user_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "users");
    }

    @GetMapping("/new")
    public String redirectToNewUserPage(Model model) {
        model.addAttribute("user", new UserRequestDto());
        return "pages/user/user_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") UserRequestDto dto) {
        try {
            userFacade.create(dto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userFacade.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/update/{id}")
    public String update(@ModelAttribute("user") UserRequestDto dto, @PathVariable Long id) {
        idToUpdate = id;
        return "pages/user/user_update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") UserRequestDto dto) {
        userFacade.update(dto, idToUpdate);
        return "redirect:/users";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("user", userFacade.findById(id));
        model.addAttribute("accounts", userFacade.findAllAccountsByUserId(id));
        return "pages/user/user_details";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<InputStreamResource> getAnExtract(@PathVariable Long id) {
        operationFacade.writeOutByUserId(id);
        File file = new File("user" + id + ".csv");
        InputStreamResource resource = null;
        MediaType mediaType = MediaType.parseMediaType("application/octet-stream");
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
