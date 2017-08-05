/*
 * Copyright 2016-2017 Sokol Development Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.khasang.sokol.controller;

import io.khasang.sokol.controller.parameter.PagingParameters;
import io.khasang.sokol.dao.*;
import io.khasang.sokol.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@PropertySource(value = {"classpath:hibernate.properties"})
@Controller
@RequestMapping(value = "/requestList")
public class RequestController {
    private static final String REDIRECT_TO_LIST = "redirect:/requestList/list?pageNumber=1&sortBy=id&sortOrder=";
    private static final String LIST_VIEW = "requestList";

    @Autowired
    RequestDao requestDao;
    @Autowired
    RequestTypeDao requestTypeDao;
    @Autowired
    RequestStatusDao requestStatusDao;
    @Autowired
    UserDao userDao;
    @Autowired
    private Environment environment;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String requestListPage(Model requestPageModel,
                                  @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                  @RequestParam(value = "sortBy", required = false) String sortBy,
                                  @RequestParam(value = "sortOrder", required = false) String sortOrder,
                                  @RequestParam(value = "findText", required = false) String findText) {
        pageNumber = (pageNumber == null) ? 1 : pageNumber;
        sortOrder = (sortOrder == null || sortOrder.equals("")) ? "" : sortOrder;
        sortBy = (sortBy == null || sortBy.equals("")) ? "id" : sortBy;
        String imgBy = "";
        String sortOrderHeader = "";
        List<Request> requestAll;
        ArrayList<Integer> pageNumbers;
        Integer pageRows = Integer.parseInt(environment.getRequiredProperty("page.size")); // кол-во записей на странице
        if (findText == null || findText.equals("")) {
            Integer countLineOfTable = requestDao.getCountLineOfTable(); // кол-во записей в таблице
            Integer lastPageNumber = ((countLineOfTable / pageRows) + 1);
            pageNumbers = totalOfPages(lastPageNumber);
            requestAll = requestDao.sortingBy((pageNumber - 1) * pageRows, pageRows, sortBy, sortOrder);
        } else {
            Integer countLineOfTable = requestDao.getCountLineOfTable(findText); // кол-во записей в таблице
            Integer lastPageNumber = ((countLineOfTable / pageRows) + 1);
            pageNumbers = totalOfPages(lastPageNumber);
            requestAll = requestDao.sortingBy((pageNumber - 1) * pageRows, pageRows, sortBy, sortOrder, findText);
        }
        if (sortOrder.equals("ASC")) {
            imgBy = "sort-up";
            sortOrderHeader = "DESC";
        } else if (sortOrder.equals("DESC")) {
            imgBy = "sort-down";
            sortOrderHeader = "ASC";
        } else {
            sortOrderHeader = "ASC";
            sortOrder = "ASC";
        }
        requestPageModel.addAttribute("requestAll", requestAll);
        requestPageModel.addAttribute("pageTotal", pageNumbers);
        requestPageModel.addAttribute("sortBy", sortBy);
        requestPageModel.addAttribute("imgBy", imgBy);
        requestPageModel.addAttribute("sortOrder", sortOrder);
        requestPageModel.addAttribute("sortOrderHeader", sortOrderHeader);
        requestPageModel.addAttribute("pageNumber", pageNumber);
        requestPageModel.addAttribute("findText", findText);
        requestPageModel.addAttribute("headerTitle", "ЗАПРОСЫ");
        return LIST_VIEW;
    }




   /* @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String requestListPage(Model requestPageModel,
                                  @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                  @RequestParam(value = "sortBy", required = false) String sortBy,
                                  @RequestParam(value = "sortOrder", required = false) String sortOrder,
                                  @RequestParam(value = "findText", required = false) String findText) {
        pageNumber = (pageNumber == null || pageNumber.equals("")) ? 1 : pageNumber;
        sortOrder = (sortOrder == null || sortOrder.equals("")) ? "" : sortOrder;
        sortBy = (sortBy == null || sortBy.equals("")) ? "id" : sortBy;
        String imgBy = "";
        String sortOrderHeader = "";
        List<Request> requestAll;
        ArrayList<Integer> pageNumbers;
        Integer pageRows = Integer.parseInt(environment.getRequiredProperty("page.size")); // кол-во записей на странице
        if (findText == null || findText.equals("")) {
            Integer countLineOfTable = requestDao.getCountLineOfTable(); // кол-во записей в таблице
            Integer lastPageNumber = ((countLineOfTable / pageRows) + 1);
            pageNumbers = totalOfPages(lastPageNumber);
            requestAll = requestDao.sortingBy((pageNumber - 1) * pageRows, pageRows, sortBy, sortOrder);
        } else {
            Integer countLineOfTable = requestDao.getCountLineOfTable(findText); // кол-во записей в таблице
            Integer lastPageNumber = ((countLineOfTable / pageRows) + 1);
            pageNumbers = totalOfPages(lastPageNumber);
            requestAll = requestDao.sortingBy((pageNumber - 1) * pageRows, pageRows, sortBy, sortOrder, findText);
        }
        if (sortOrder.equals("ASC")) {
            imgBy = "sort-up";
            sortOrderHeader = "DESC";
        } else if (sortOrder.equals("DESC")) {
            imgBy = "sort-down";
            sortOrderHeader = "ASC";
        } else {
            sortOrderHeader = "ASC";
            sortOrder = "ASC";
        }
        requestPageModel.addAttribute("requestAll", requestAll);
        requestPageModel.addAttribute("pageTotal", pageNumbers);
        requestPageModel.addAttribute("sortBy", sortBy);
        requestPageModel.addAttribute("imgBy", imgBy);
        requestPageModel.addAttribute("sortOrder", sortOrder);
        requestPageModel.addAttribute("sortOrderHeader", sortOrderHeader);
        requestPageModel.addAttribute("pageNumber", pageNumber);
        requestPageModel.addAttribute("findText", findText);
        requestPageModel.addAttribute("headerTitle", "ЗАПРОСЫ");
        return LIST_VIEW;
    }*/

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String doGetRequestPageAdd(Model model, PagingParameters pagingParameters) {
        List<RequestType> requestTypeAll = requestTypeDao.getAll();
        model.addAttribute("requestTypeAll", requestTypeAll);
        model.addAttribute("pagingParameters", pagingParameters);
        model.addAttribute("headerTitle", "Новый запрос");
        return "requestAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String doPostRequestPageAdd(Request request,
                                       PagingParameters pagingParameters,
                                       @RequestParam("requestTypeId") Integer requestTypeId,
                                       @RequestParam("attachedFile") MultipartFile attachedFile) throws IOException {
        RequestStatus status = requestStatusDao.getById(1);
        RequestType requestType = requestTypeDao.getById(requestTypeId);
        request.setStatus(status);
        request.setVersion(1);
        request.setFileName(attachedFile.getOriginalFilename());
        request.setFile(attachedFile.getBytes());
        request.setCreatedDate(new Date());
        request.setRequestType(requestType);
        //   request.setDepartment(department);
        SecurityContext context = SecurityContextHolder.getContext();
        request.setCreatedBy(context.getAuthentication().getName());
        request.setUpdatedBy(context.getAuthentication().getName());
        requestDao.save(request);
        return "redirect:/requestList/list?pageNumber=" + pagingParameters.getPageNumber() + "&sortBy="
                + pagingParameters.getSortBy() + "&sortOrder=" + pagingParameters.getSortOrder() + "&sortOrderHeader=" + pagingParameters.getSortOrder();
    }

    // добавление запроса на редактирование

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String doGetRequestPageEdit(Model model, PagingParameters pagingParameters,
                                       @RequestParam("requestId") Integer requestId) {
        Request request = requestDao.getByRequestId(requestId);
        List<RequestStatus> requestStatusAll = requestStatusDao.getAll();
        List<RequestType> requestTypeAll = requestTypeDao.getAll();
        model.addAttribute("request", request);
        model.addAttribute("requestTypeAll", requestTypeAll);
        model.addAttribute("requestStatusAll", requestStatusAll);
        model.addAttribute("pagingParameters", pagingParameters);
        model.addAttribute("headerTitle", String.format("Запрос: %s", request.getTitle()));
        return "requestEdit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String doPostRequestPageEdit(Request request,
                                        PagingParameters pagingParameters,
                                        @RequestParam("requestId") Integer requestId,
                                        @RequestParam("requestStatusId") Integer requestStatusId,
                                        @RequestParam("requestTypeId") Integer requestTypeId,
                                        @RequestParam("attachedFile") MultipartFile attachedFile) throws IOException {
        Request update = requestDao.getByRequestId(requestId);
        RequestStatus status = requestStatusDao.getByRequestStatusId(requestStatusId);
        RequestType requestType = requestTypeDao.getById(requestTypeId);
        update.setTitle(request.getTitle());
        update.setDescription(request.getDescription());
        update.setStatus(status);
        update.setRequestType(requestType);
        update.setFile(attachedFile.getBytes());
        update.setFileName(attachedFile.getOriginalFilename());
        update.setUpdatedDate(new Date());
        SecurityContext context = SecurityContextHolder.getContext();
        update.setUpdatedBy(context.getAuthentication().getName());
        requestDao.saveOrUpdate(update);
        return "redirect:/requestList/list?pageNumber=" + pagingParameters.getPageNumber() + "&sortBy=" + pagingParameters.getSortBy()
                + "&sortOrder=" + pagingParameters.getSortOrder() + "&sortOrderHeader=" + pagingParameters.getSortOrderHeader();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delRequestPage(Model delRequest, @RequestParam("requestId") Integer requestId) {
        Request request = requestDao.getByRequestId(requestId);
        requestDao.delete(request);
        delRequest.addAttribute("request", request);
        return REDIRECT_TO_LIST;
    }

    @RequestMapping(value = "/assignedTo", method = RequestMethod.GET) // назначение ответственного за выполнение заявки
    public String assignedToRequest(@RequestParam("requestId") Integer requestId) {
        Request request = requestDao.getByRequestId(requestId);
        SecurityContext context = SecurityContextHolder.getContext();
        User user = userDao.getByLogin(context.getAuthentication().getName());
        request.setAssignedTo(user);
        request.setAssignedDate(new Date());
        requestDao.saveOrUpdate(request);
        return REDIRECT_TO_LIST;
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public HttpServletResponse downloadFile(HttpServletResponse response, @RequestParam("requestId") Integer requestId)
            throws Exception {
        Request request = requestDao.getByRequestId(requestId);
        String fileName = request.getFileName();
        byte[] file = request.getFile();
        InputStream input = new ByteArrayInputStream(file);
        OutputStream output = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setContentType("application/download");
        try {
            int read = 0;
            while ((read = input.read()) != -1) {
                output.write(read);
            }
        } finally {
            input.close();
            output.flush();
            output.close();
        }
        return null;
    }

    private ArrayList<Integer> totalOfPages(int lastPageNumber) { //общее количество страниц для paging
        ArrayList<Integer> totalOfPages = new ArrayList<>();
        for (int i = 0; i < lastPageNumber; i++) {
            totalOfPages.add(i + 1);
        }
        return totalOfPages;
    }
}
