package io.khasang.sokol.controller;


import io.khasang.sokol.model.Request;
import io.khasang.sokol.model.RequestStatus;
import io.khasang.sokol.repository.RequestRepository;
import io.khasang.sokol.repository.RequestStatusRepository;
import io.khasang.sokol.repository.RequestTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
        //List<Request> requestList = requestRepository.findAllByDeletedIsFalse();
        List<Request> requestList = requestRepository.findAll();
        model.addAttribute("requestList", requestList);
        model.addAttribute("headerTitle", REQUEST_LIST_HEADER_TITLE_LIST);
        return "requestList";
    }

    @GetMapping("/add")
    public String requestForm(Model model) {
        model.addAttribute("allRequestTypes", requestTypeRepository.findAllByDeletedIsFalse());
        model.addAttribute("request", new Request());
        model.addAttribute("headerTitle", REQUEST_LIST_HEADER_TITLE_ADD);
        return "requestForm";
    }

    @PostMapping("/add")
    public String requestSubmit(@ModelAttribute Request request) {
        RequestStatus status = requestStatusRepository.findOne(1L);
        request.setStatus(status);
        request.setVersion(1);
        requestRepository.save(request);
        return REQUEST_REDIRECT_TO_LIST;
    }

    @GetMapping("/edit/{id}")
    public String requestEdit(Model model, @PathVariable long id) {
        model.addAttribute("allRequestTypes", requestTypeRepository.findAllByDeletedIsFalse());
        Request request = requestRepository.findOne(id);
        model.addAttribute("request", request);
        model.addAttribute("headerTitle", REQUEST_LIST_HEADER_TITLE_EDIT);
        return "requestForm";
    }

    @PostMapping("/edit/{id}")
    public String requestPost(@PathVariable long id, Request requestDetails) {
        Request request = requestRepository.getOne(id);
   //     request.setCreatedDate(requestDetails.getCreatedDate());
        request.setTitle(requestDetails.getTitle());
        request.setDescription(requestDetails.getDescription());
        request.setRequestType(requestDetails.getRequestType());
        request.setStatus(requestDetails.getStatus());
        request.setUpdatedDate(new Date());




   //     requestRepository.save(request);
        return REQUEST_REDIRECT_TO_LIST;
    }
}

/*    @PostMapping("/edit/{id}")
    public String departmentEdit(@PathVariable long id, RequestType requestTypeDetails) {
        RequestType requestType = requestTypeRepository.getOne(id);
        requestType.setTitle(requestTypeDetails.getTitle());
        requestType.setDescription(requestTypeDetails.getDescription());
        requestType.setDepartment(requestTypeDetails.getDepartment());
        requestType.setUpdatedDate(new Date());
        return REQUEST_TYPE_FORM;
    }*/
