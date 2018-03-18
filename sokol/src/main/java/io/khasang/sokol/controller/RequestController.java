package io.khasang.sokol.controller;


import com.sun.org.apache.regexp.internal.RE;
import io.khasang.sokol.model.Department;
import io.khasang.sokol.model.Request;
import io.khasang.sokol.model.RequestStatus;
import io.khasang.sokol.model.RequestType;
import io.khasang.sokol.repository.RequestRepository;
import io.khasang.sokol.repository.RequestStatusRepository;
import io.khasang.sokol.repository.RequestTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/sokol/request")
public class RequestController {
    private static final String REQUEST_REDIRECT_TO_LIST = "redirect:/sokol/request/list";
    private static final String REQUEST_LIST_HEADER_TITLE_LIST = "Запросы";
    private static final String REQUEST_LIST_HEADER_TITLE_ADD = "Добавление запроса";
    private static final String REQUEST_LIST_HEADER_TITLE_EDIT = "Редактирование запроса";

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    RequestTypeRepository requestTypeRepository;

    @Autowired
    RequestStatusRepository requestStatusRepository;

    @GetMapping({"/list"})
    public String requestList(Model model) {
        //List<Request> requestList = requestRepository.findAllByIsDeletedIsFalse();
        List<Request> requestList = requestRepository.findAll();
        model.addAttribute("requestList", requestList);
        model.addAttribute("headerTitle", REQUEST_LIST_HEADER_TITLE_LIST);
        return "requestList";
    }

    @GetMapping("/add")
    public String requestForm(Model model) {
        model.addAttribute("allRequestTypes", requestTypeRepository.findAllByIsDeletedIsFalse());
        model.addAttribute("request", new Request());
        model.addAttribute("headerTitle", REQUEST_LIST_HEADER_TITLE_ADD);
        return "requestForm";
    }

    @PostMapping("/add")
    public String requestSubmit(@ModelAttribute Request request) {
        RequestStatus status = requestStatusRepository.findOne(1L);
        //RequestType requestType = requestTypeDao.getById(requestTypeId);
        request.setStatus(status);
        request.setVersion(1);
        requestRepository.save(request);
        return REQUEST_REDIRECT_TO_LIST;
    }


}


