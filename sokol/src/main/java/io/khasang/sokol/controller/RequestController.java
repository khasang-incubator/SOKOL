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
//import org.jsoup.Jsoup;
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
    DepartmentDao departmentDao;
    @Autowired
    private Environment environment;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String requestListPage(Model requestPageModel,
                                  @RequestParam(value = "pageNumber", required = false) String pageNumber,
                                  @RequestParam(value = "sortBy", required = false) String sortBy,
                                  @RequestParam(value = "sortOrder", required = false) String sortOrder,
                                  @RequestParam(value = "findText", required = false) String findText) {
        pageNumber = (pageNumber == null || pageNumber.equals("")) ? "1" : pageNumber;
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
            requestAll = requestDao.sortingBy((Integer.parseInt(pageNumber) - 1) * pageRows, pageRows, sortBy, sortOrder);
        } else {
            Integer countLineOfTable = requestDao.getCountLineOfTable(findText); // кол-во записей в таблице
            Integer lastPageNumber = ((countLineOfTable / pageRows) + 1);
            pageNumbers = totalOfPages(lastPageNumber);
            requestAll = requestDao.sortingBy((Integer.parseInt(pageNumber) - 1) * pageRows, pageRows, sortBy, sortOrder, findText);
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

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String requestAddPage(Model requestAddModel, PagingParameters pagingParameters) {
        List<RequestType> requestTypeAll = requestTypeDao.getAll();
        List<Department> departmentAll = departmentDao.getAll();
        requestAddModel.addAttribute("requestTypeAll", requestTypeAll);
        requestAddModel.addAttribute("departmentAll", departmentAll);
        requestAddModel.addAttribute("pagingParameters", pagingParameters);
        requestAddModel.addAttribute("headerTitle", "ЗАПРОСЫ. НОВЫЙ ЗАПРОС");
        return "requestAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String requestAdd(Request request,
                             @RequestParam("requestTypeId") Integer requestTypeId,
                             @RequestParam("departmentId") Integer departmentId,
                             PagingParameters pagingParameters,
                             @RequestParam("attachedFile") MultipartFile attachedFile) throws IOException {
        RequestStatus status = requestStatusDao.getById(1);
        request.setStatus(status);
        request.setVersion(1);
        request.setFileName(attachedFile.getOriginalFilename());
        request.setFile(attachedFile.getBytes());
        request.setCreatedDate(new Date());
        RequestType requestType = requestTypeDao.getById(requestTypeId);
        request.setRequestType(requestType);
        Department department = departmentDao.getById(departmentId);
        request.setDepartment(department);
        SecurityContext context = SecurityContextHolder.getContext();
        request.setCreatedBy(context.getAuthentication().getName());
        request.setUpdatedBy(context.getAuthentication().getName());
        requestDao.save(request);
        return "redirect:/requestList/list?pageNumber=" + pagingParameters.getPageNumber() + "&sortBy="
                + pagingParameters.getSortBy() + "&sortOrder=" + pagingParameters.getSortOrder() + "&sortOrderHeader=" + pagingParameters.getSortOrder();
    }

    // добавление запроса на редактирование

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String requestEditPage(Model requestEditModel, PagingParameters pagingParameters,
                                  @RequestParam("requestid") String requestid) {
        Request request = requestDao.getByRequestId(Integer.parseInt(requestid));
        requestEditModel.addAttribute("request", request);
        List<RequestStatus> requestStatusAll = requestStatusDao.getAll();
        List<RequestType> requestTypeAll = requestTypeDao.getAll();
        requestEditModel.addAttribute("requestTypeAll", requestTypeAll);
        requestEditModel.addAttribute("requestStatusAll", requestStatusAll);
        List<Department> departmentAll = departmentDao.getAll();
        requestEditModel.addAttribute("departmentAll", departmentAll);
        requestEditModel.addAttribute("pagingParameters", pagingParameters);
        requestEditModel.addAttribute("headerTitle", "ЗАПРОСЫ. РЕДАКТИРОВАНИЕ ЗАПРОСА");
        return "requestEdit";
    }

/*    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String requestEditPage(Model requestEditModel, @RequestParam("idRequest") String idRequest,
                                  @RequestParam("pageNumber") String pageNumber,
                                  @RequestParam("sortBy") String sortBy,
                                  @RequestParam("sortOrder") String sortOrder,
                                  @RequestParam("sortOrderHeader") String sortOrderHeader) {
        Request request = requestDao.getByRequestId(Integer.parseInt(idRequest));
        requestEditModel.addAttribute("request", request);
        List<RequestStatus> requestStatusAll = requestStatusDao.getAll();
        List<RequestType> requestTypeAll = requestTypeDao.getAll();
        requestEditModel.addAttribute("requestTypeAll", requestTypeAll);
        List<Department> departmentAll = departmentDao.getAll();
        requestEditModel.addAttribute("departmentAll", departmentAll);
        requestEditModel.addAttribute("requestStatusAll", requestStatusAll);
        requestEditModel.addAttribute("pageNumber", pageNumber);
        requestEditModel.addAttribute("sortBy", sortBy);
        requestEditModel.addAttribute("sortOrder", sortOrder);
        requestEditModel.addAttribute("sortOrderHeader", sortOrderHeader);
        requestEditModel.addAttribute("headerTitle", "ЗАПРОСЫ. РЕДАКТИРОВАНИЕ ЗАПРОСА");
        return "requestEdit";
    }*/

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String addRequestPerformer(HttpServletRequest httpServletRequest,
                                      PagingParameters pagingParameters,
                                      @RequestParam("file") final MultipartFile file) throws IOException {
        ModelAndView model = new ModelAndView();
        Request request = requestDao.getByRequestId(Integer.parseInt(httpServletRequest.getParameter("requestid")));
        request.setTitle(httpServletRequest.getParameter("title"));
        request.setDescription(httpServletRequest.getParameter("description"));
        RequestStatus status = requestStatusDao.getByRequestStatusId(Integer.parseInt(httpServletRequest.getParameter("requestStatusid")));
        request.setStatus(status);
        request.setFile(file.getBytes());
        request.setUpdatedDate(new Date());
        RequestType requestType = requestTypeDao.getById(Integer.parseInt(httpServletRequest.getParameter("requestTypeid")));
        request.setRequestType(requestType);
        Department department = departmentDao.getById(Integer.parseInt(httpServletRequest.getParameter("departmentid")));
        request.setDepartment(department);
        request.setFileName(file.getOriginalFilename());
        SecurityContext context = SecurityContextHolder.getContext();
        request.setUpdatedBy(context.getAuthentication().getName());
        requestDao.saveOrUpdate(request);
        model.setViewName("requestEdit");
        return "redirect:/requestList/list?pageNumber=" + pagingParameters.getPageNumber() + "&sortBy=" + pagingParameters.getSortBy()
                + "&sortOrder=" + pagingParameters.getSortOrder() + "&sortOrderHeader=" + pagingParameters.getSortOrderHeader();
    }

/*    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String addRequestPerformer(@RequestParam("requestid") String requestid,
                                      @RequestParam("requestStatusid") String requestStatusid,
                                      RequestPojo requestPojo,
                                      PagingParameters pagingParameters,
                                      @RequestParam("file") final MultipartFile file) throws IOException {
        ModelAndView model = new ModelAndView();
        Request request = requestDao.getByRequestId(Integer.parseInt(requestid));
        request.setTitle(requestPojo.getTitle());
        request.setDescription(requestPojo.getDescription());
        RequestStatus status = requestStatusDao.getByRequestStatusId(Integer.parseInt(requestStatusid));
        request.setStatus(status);
        request.setFile(file.getBytes());
        request.setUpdatedDate(new Date());
        RequestType requestType = requestTypeDao.getById(Integer.parseInt(requestPojo.getRequestTypeid()));
        request.setRequestType(requestType);
        Department department = departmentDao.getById(Integer.parseInt(requestPojo.getDepartmentid()));
        request.setDepartment(department);
        request.setFileName(file.getOriginalFilename());
        SecurityContext context = SecurityContextHolder.getContext();
        request.setUpdatedBy(context.getAuthentication().getName());
        requestDao.saveOrUpdate(request);
        model.setViewName("requestEdit");
        return "redirect:/requestList/list?pageNumber=" + pagingParameters.getPageNumber() + "&sortBy=" + pagingParameters.getSortBy()
                + "&sortOrder=" + pagingParameters.getSortOrder() + "&sortOrderHeader=" + pagingParameters.getSortOrderHeader();
    }*/

/*        @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String addRequestPerformer(@RequestParam("idrequest") String idrequest,
                                      @RequestParam("title") String name,
                                      @RequestParam("description") String description,
                                      @RequestParam("idrequesttypes") String idrequesttypes,
                                      @RequestParam("iddepartment") String iddepartment,
                                      @RequestParam("idrequeststatus") String idrequeststatus,
                                      PagingParameters pagingParameters,
                                      @RequestParam("file") final MultipartFile file) throws IOException {
        ModelAndView model = new ModelAndView();
        Request request = requestDao.getByRequestId(Integer.parseInt(idrequest));
        request.setTitle(name);
        request.setDescription(description);
        RequestStatus status = requestStatusDao.getByRequestStatusId(Integer.parseInt(idrequeststatus));
        request.setStatus(status);
        request.setFile(file.getBytes());
        request.setUpdatedDate(new Date());
        RequestType requestType = requestTypeDao.getById(Integer.parseInt(idrequesttypes));
        request.setRequestType(requestType);
        Department department = departmentDao.getById(Integer.parseInt(iddepartment));
        request.setDepartment(department);
        request.setFileName(file.getOriginalFilename());
        SecurityContext context = SecurityContextHolder.getContext();
        request.setUpdatedBy(context.getAuthentication().getName());
        requestDao.saveOrUpdate(request);
        model.setViewName("requestEdit");
        return "redirect:/requestList/list?pageNumber=" + pagingParameters.getPageNumber() + "&sortBy=" + pagingParameters.getSortBy()
                + "&sortOrder=" + pagingParameters.getSortOrder() + "&sortOrderHeader=" + pagingParameters.getSortOrderHeader();
    }*/

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delRequestPage(Model delRequest, @RequestParam("requestid") String requestid) {
        Request request = requestDao.getByRequestId(Integer.parseInt(requestid));
        requestDao.delete(request);
        delRequest.addAttribute("request", request);
        return REDIRECT_TO_LIST;
    }

    @RequestMapping(value = "/assignedTo", method = RequestMethod.GET) // назначение ответственного за выполнение заявки
    public String assignedToRequest(@RequestParam("requestid") String requestid) {
        Request request = requestDao.getByRequestId(Integer.parseInt(requestid));
        SecurityContext context = SecurityContextHolder.getContext();
        User user = userDao.getByLogin(context.getAuthentication().getName());
        request.setAssignedTo(user);
        request.setAssignedDate(new Date());
        requestDao.saveOrUpdate(request);
        return REDIRECT_TO_LIST;
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public HttpServletResponse downloadFile(HttpServletResponse response, @RequestParam("requestid") String requestid)
            throws Exception {
        Request request = requestDao.getByRequestId(Integer.parseInt(requestid));
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
