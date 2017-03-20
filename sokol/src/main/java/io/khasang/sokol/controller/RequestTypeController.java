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

import io.khasang.sokol.dao.DepartmentDao;
import io.khasang.sokol.dao.RequestTypeDao;
import io.khasang.sokol.entity.Department;
import io.khasang.sokol.entity.RequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
@RequestMapping(value = "/requestType")
public class RequestTypeController {
    private static final String REDIRECT_TO_LIST = "redirect:/requestType/list";
    private static final String LIST_URL = "/requestType/list";
    private static final String REQUEST_TYPE_VIEW = "requestTypeForm";
    private static final String REQUEST_TYPE_LIST_VIEW = "requestTypeList";

    @Autowired
    RequestTypeDao requestTypeDao;

    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showRequestTypes(Model model) {
        model.addAttribute("requestTypes", requestTypeDao.getAll());
        model.addAttribute("headerTitle", "Типы запросов");
        return REQUEST_TYPE_LIST_VIEW;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String editRequestType(@PathVariable int id, Model model) {
        model.addAttribute("requestType", requestTypeDao.getById(id));
        model.addAttribute("departments", departmentDao.getAll());
        model.addAttribute("headerTitle", "Редактирование типа запроса");
        configureCancelUrl(model);
        return REQUEST_TYPE_VIEW;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String updateRequestType(@PathVariable int id, RequestType requestType, Department department) {
        RequestType updated = requestTypeDao.getById(id);
        updated.setDescription(requestType.getDescription());
        updated.setTitle(requestType.getTitle());
        updated.setDepartment(department);
        updated.setUpdatedDate(new Date());
        updated.setDepartment(requestType.getDepartment());
        requestTypeDao.update(requestType);
        return REDIRECT_TO_LIST;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String newRequestType(Model model) {
        model.addAttribute("requestType", new RequestType());
        model.addAttribute("departments", departmentDao.getAll());
        model.addAttribute("headerTitle", "Новый тип запроса");
        configureCancelUrl(model);
        return REQUEST_TYPE_VIEW;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveRequestType(RequestType requestType) {
        Date now = new Date();
        requestType.setCreatedDate(now);
        requestType.setUpdatedDate(now);
        requestTypeDao.save(requestType);
        return REDIRECT_TO_LIST;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteRequestType(@PathVariable int id) {
        requestTypeDao.delete(requestTypeDao.getById(id));
        return REDIRECT_TO_LIST;
    }

    private void configureCancelUrl(Model model) {
        model.addAttribute("cancelUrl", LIST_URL);
    }
}
