package io.khasang.sokol.controller;

import io.khasang.sokol.model.Department;
import io.khasang.sokol.model.RequestType;
import io.khasang.sokol.repository.RequestTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/sokol/requestType")
public class RequestTypeController {
    private static final String REDIRECT_TO_LIST = "redirect:/requestType/list";
    private static final String LIST_URL = "/requestType/list";
    private static final String REQUEST_TYPE_FORM = "requestTypeForm";
    private static final String REQUEST_TYPE_LIST = "requestTypeList";

    @Autowired
    RequestTypeRepository requestTypeRepository;

    @GetMapping({"/list"})
    public String requestTypeList(Model model) {
        List<RequestType> requestTypeList = requestTypeRepository.findAll();
        model.addAttribute("requestTypeList", requestTypeList);
        return REQUEST_TYPE_LIST;
    }

    @GetMapping("add")
    public String requestTypeForm(Model model) {
        model.addAttribute("requestType", new RequestType());
        return REQUEST_TYPE_FORM;
    }


}
