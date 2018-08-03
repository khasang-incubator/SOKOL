package io.khasang.sokol.controller;

import io.khasang.sokol.model.RequestType;
import io.khasang.sokol.repository.DepartmentRepository;
import io.khasang.sokol.repository.RequestTypeRepository;
import io.khasang.sokol.service.RequestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/requestType")
public class RequestTypeController {
    private static final String REDIRECT_TO_LIST = "redirect:/admin/requestType/list";
    private static final String LIST_URL = "/requestType/list";
    private static final String REQUEST_TYPE_FORM = "requestTypeForm";
    private static final String REQUEST_TYPE_LIST = "requestTypeList";
    private static final String REQUEST_TYPE_LIST_HEADER_TITLE_LIST = "Типы запросов";
    private static final String REQUEST_TYPE_LIST_HEADER_TITLE_ADD = "Добавление типа запроса";
    private static final String REQUEST_TYPE_LIST_HEADER_TITLE_EDIT = "Редактирование типа запроса";

    @Autowired
    RequestTypeRepository requestTypeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    RequestTypeService requestTypeService;


    @GetMapping({"/list"})
    public String requestTypeList(Model model) {
        //List<RequestType> requestTypeList = requestTypeRepository.findAll();
        List<RequestType> requestTypeList = requestTypeRepository.findAllByDeletedIsFalse();
        model.addAttribute("requestTypeList", requestTypeList);
        model.addAttribute("headerTitle", REQUEST_TYPE_LIST_HEADER_TITLE_LIST);
        return REQUEST_TYPE_LIST;
    }

    @GetMapping("/add")
    public String requestTypeForm(Model model) {
        //model.addAttribute("allDepartments", departmentRepository.findAll());
        model.addAttribute("allDepartments", departmentRepository.findAllByDeletedIsFalse());
        model.addAttribute("requestType", new RequestType());
        model.addAttribute("headerTitle", REQUEST_TYPE_LIST_HEADER_TITLE_ADD);
        return REQUEST_TYPE_FORM;
    }

    @PostMapping("/save")
    public String requestTypeSubmit(@ModelAttribute RequestType requestType) {
        requestTypeService.save(requestType);
        return REDIRECT_TO_LIST;
    }

    @GetMapping("/edit/{id}")
    public String requestTypeEdit(Model model, @PathVariable long id) {
        RequestType requestType = requestTypeRepository.findOne(id);
        model.addAttribute("requestType", requestType);
        model.addAttribute("allDepartments", departmentRepository.findAll());
        model.addAttribute("headerTitle", REQUEST_TYPE_LIST_HEADER_TITLE_EDIT);
        return REQUEST_TYPE_FORM;
    }


    @GetMapping("/delete/{id}")
    public String requestTypeDelete(@PathVariable long id) {
         requestTypeService.requestTypeDelete(id);
        return REDIRECT_TO_LIST;
    }

}
