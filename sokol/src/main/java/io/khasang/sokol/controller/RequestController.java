package io.khasang.sokol.controller;


import io.khasang.sokol.model.Request;
import io.khasang.sokol.model.RequestStatus;
import io.khasang.sokol.repository.RequestRepository;
import io.khasang.sokol.repository.RequestStatusRepository;
import io.khasang.sokol.repository.RequestTypeRepository;
import io.khasang.sokol.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/request")
public class RequestController {
    private static final String REQUEST_REDIRECT_TO_LIST = "redirect:/admin/request/list";
    private static final String REQUEST_LIST_HEADER_TITLE_LIST = "Запросы";
    private static final String REQUEST_LIST_HEADER_TITLE_ADD = "Добавление запроса";
    private static final String REQUEST_LIST_HEADER_TITLE_EDIT = "Редактирование запроса";

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    RequestTypeRepository requestTypeRepository;

    @Autowired
    RequestStatusRepository requestStatusRepository;

    @Autowired
    RequestService requestService;

    @GetMapping("/list")
    public String requestList(Model model) {
        List<Request> requestList = requestRepository.findAll();
        model.addAttribute("requestList", requestList);
        String imgBy = "sort-up";
        model.addAttribute("imgBy", imgBy);
        model.addAttribute("headerTitle", REQUEST_LIST_HEADER_TITLE_LIST);
        return "requestList";
    }

    @GetMapping("/list2")
    public String requestList2(Model model) {
        Page<Request> pageRequestList;
        pageRequestList = requestRepository.findAll(new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "id")));

        model.addAttribute("requestList", pageRequestList);
        //String imgBy = "sort-up";
      //  model.addAttribute("imgBy", imgBy);
        model.addAttribute("headerTitle", REQUEST_LIST_HEADER_TITLE_LIST);
        return "requestList";
    }


    @GetMapping("/listSort/{imgBy}")
    //public String requestListSort(Model model, @PathVariable String imgBy, @PathVariable String sortBy) {
    public String requestListSort(Model model, @PathVariable String imgBy) {
        Page<Request> pageRequestList;
        //sortBy = (sortBy == null || sortBy.equals("")) ? "id" : sortBy;
        if (imgBy.equals("sort-up")) {
            pageRequestList = requestRepository.findAll(new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "title")));
            //pageRequestList = requestRepository.findAll(new PageRequest(0, 10, new Sort(Sort.Direction.ASC, sortBy)));
            imgBy = "sort-down";
        } else {
            imgBy = "sort-up";
            pageRequestList = requestRepository.findAll(new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "title")));
            //pageRequestList = requestRepository.findAll(new PageRequest(0, 10, new Sort(Sort.Direction.DESC, sortBy)));
        }
        model.addAttribute("imgBy", imgBy);
        //pageRequestList.getSort().toString();
        model.addAttribute("requestList", pageRequestList);
        model.addAttribute("headerTitle", REQUEST_LIST_HEADER_TITLE_LIST);
        return "requestList";
    }


/*    @GetMapping({"/list3"})
    public String departmentListPage3(Model model) {
        Page<Department> pageDepartmentList = departmentRepository.findAllByDeletedIsFalse(new PageRequest(0, 10, new Sort(Sort.DEFAULT_DIRECTION, "id")));
        final Sort sort = pageDepartmentList.getSort();
        model.addAttribute("departmentList", pageDepartmentList.getContent());
        model.addAttribute("headerTitle", DEPARTMENT_TYPE_LIST_HEADER_TITLE_LIST);
        return "departmentList";
    }
    */


    @GetMapping("/add")
    public String requestForm(Model model) {
        model.addAttribute("allRequestTypes", requestTypeRepository.findAllByDeletedIsFalse());
        model.addAttribute("request", new Request());
        model.addAttribute("headerTitle", REQUEST_LIST_HEADER_TITLE_ADD);
        return "requestForm";
    }

    @PostMapping("/save")
    public String requestSubmit(@ModelAttribute Request request) {
        requestService.save(request);
        return REQUEST_REDIRECT_TO_LIST;
    }

    @GetMapping("/edit/{id}")
    public String requestEdit(Model model, @PathVariable long id) {
        model.addAttribute("allRequestTypes", requestTypeRepository.findAllByDeletedIsFalse());
        //model.addAttribute("allRequestStatus", requestStatusRepository.findAllByDeletedIsFalse());
        model.addAttribute("allRequestStatus", requestStatusRepository.findAll());
        Request request = requestRepository.findOne(id);
        model.addAttribute("request", request);
        model.addAttribute("headerTitle", REQUEST_LIST_HEADER_TITLE_EDIT);
        return "requestForm";
    }

    @GetMapping("/delete/{id}")
    public String requestDelete(@PathVariable long id) {
        requestService.requestDelete(id);
        return REQUEST_REDIRECT_TO_LIST;
    }


}

