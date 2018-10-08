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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping(value = "/admin/request")
public class RequestController {
    private static final String REQUEST_REDIRECT_TO_LIST = "redirect:/admin/request/list";
    private static final String REQUEST_LIST_HEADER_TITLE_LIST = "Запросы";
    private static final String REQUEST_LIST_HEADER_TITLE_ADD = "Добавление запроса";
    private static final String REQUEST_LIST_HEADER_TITLE_EDIT = "Редактирование запроса";
    private static int currentPage = 1;

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
        Page<Request> pageRequestList = requestRepository.findAll(new PageRequest(0, 5, new Sort(Sort.Direction.ASC, "id")));
        model.addAttribute("requestList", pageRequestList);
        model.addAttribute("imgBy", "sort-up");
        model.addAttribute("sortBy", "id");
        model.addAttribute("sortByNext", "id");
        model.addAttribute("headerTitle", REQUEST_LIST_HEADER_TITLE_LIST);
        int totalPages = pageRequestList.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "requestList";
    }

    @GetMapping("/listSort")
    public String requestListSort(Model model,
                                  @RequestParam("imgBy") String imgBy,
                                  @RequestParam("sortBy") String sortBy,
                                  @RequestParam("sortByNext") String sortByNext) {
        Sort.Direction direction;
        if (sortBy.equals(sortByNext)) {
            if (imgBy.equals("sort-up")) {
                direction = Sort.Direction.DESC;
                imgBy = "sort-down";
            } else {
                imgBy = "sort-up";
                direction = Sort.Direction.ASC;
            }
        } else {
            direction = Sort.Direction.ASC;
            imgBy = "sort-up";
            sortBy = sortByNext;
        }
        Page<Request> pageRequestList = requestRepository.findAll(new PageRequest(0, 5, new Sort(direction, sortByNext)));
        model.addAttribute("imgBy", imgBy);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortByNext", sortByNext);
        model.addAttribute("requestList", pageRequestList);
        model.addAttribute("headerTitle", REQUEST_LIST_HEADER_TITLE_LIST);
        int totalPages = pageRequestList.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "requestList";
    }

    @GetMapping("/listPagination")
    public String requestListPagination(Model model,
                                        @RequestParam("imgBy") String imgBy,
                                        @RequestParam("sortBy") String sortBy,
                                        @RequestParam(value = "pageNumber") Integer pageNumber,
                                        @RequestParam(value = "pageNumbers") List<Integer> pageNumbers) {
        Page<Request> pageRequestList = requestRepository.findAll(new PageRequest(pageNumber, 5));
        model.addAttribute("imgBy", imgBy);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("requestList", pageRequestList);
        model.addAttribute("pageNumbers", pageNumbers);
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

