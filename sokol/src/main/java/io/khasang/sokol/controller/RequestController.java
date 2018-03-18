package io.khasang.sokol.controller;


import com.sun.org.apache.regexp.internal.RE;
import io.khasang.sokol.model.Department;
import io.khasang.sokol.model.Request;
import io.khasang.sokol.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/sokol/requestList")
public class RequestController {
    private static final String REQUEST_REDIRECT_TO_LIST = "redirect:/sokol/requestList/list";
    private static final String REQUEST_LIST_HEADER_TITLE_LIST = "Запросы";
    private static final String REQUEST__LIST_HEADER_TITLE_ADD = "Добавление запроса";
    private static final String REQUEST__LIST_HEADER_TITLE_EDIT = "Редактирование запроса";

    @Autowired
    RequestRepository requestRepository;

    @GetMapping({"/list"})
    public String requestList(Model model) {
        //List<Request> requestList = requestRepository.findAllByIsDeletedIsFalse();
        List<Request> requestList = requestRepository.findAll();
        model.addAttribute("requestList", requestList);
        model.addAttribute("headerTitle", REQUEST_LIST_HEADER_TITLE_LIST);
        return "requestList";
    }
}


