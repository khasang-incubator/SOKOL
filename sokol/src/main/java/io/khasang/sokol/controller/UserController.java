package io.khasang.sokol.controller;

import io.khasang.sokol.model.RequestType;
import io.khasang.sokol.model.User;
import io.khasang.sokol.repository.DepartmentRepository;
import io.khasang.sokol.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/user")
public class UserController {
    private static final String REDIRECT_TO_LIST = "redirect:/admin/user/list";
    private static final String USER_LIST = "userList";
    private static final String USER_LIST_HEADER_TITLE_LIST = "Пользователи";

    @Autowired
    UserRepository userRepository;

    @GetMapping({"/list"})
    public String requestTypeList(Model model) {
        List<User> userList = userRepository.findAll();
       // List<RequestType> requestTypeList = requestTypeRepository.findAllByDeletedIsFalse();
        model.addAttribute("userList", userList);
        model.addAttribute("headerTitle", USER_LIST_HEADER_TITLE_LIST);
        return USER_LIST;
    }

    @GetMapping("/add")
    public String userForm(Model model) {
        User user = new User();
        user.setFio("Ivanov");
        user.setUsername("iva");
        user.setPassword("123");
        userRepository.save(user);
        //model.addAttribute("allDepartments", departmentRepository.findAll());
        //model.addAttribute("allDepartments", departmentRepository.findAllByDeletedIsFalse());
      //  model.addAttribute("requestType", new RequestType());
       // model.addAttribute("headerTitle", REQUEST_TYPE_LIST_HEADER_TITLE_ADD);
        return REDIRECT_TO_LIST;
    }

    @GetMapping("/edit/{id}")
    public String userEdit(Model model, @PathVariable long id) {
/*        U requestType = requestTypeRepository.findOne(id);
        model.addAttribute("requestType", requestType);
        model.addAttribute("allDepartments", departmentRepository.findAll());
        model.addAttribute("headerTitle", REQUEST_TYPE_LIST_HEADER_TITLE_EDIT);
        return REQUEST_TYPE_FORM;*/
        return REDIRECT_TO_LIST;
    }


    @GetMapping("/delete/{id}")
    public String userDelete(@PathVariable long id) {
/*        requestTypeService.requestTypeDelete(id);
        return REDIRECT_TO_LIST;*/
        return REDIRECT_TO_LIST;
    }
}
