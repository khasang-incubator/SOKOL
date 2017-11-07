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
import io.khasang.sokol.entity.Department;
import io.khasang.sokol.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/department")
public class DepartmentController {
    private static final String REDIRECT_TO_LIST = "redirect:/department/list";
    private static final String FORM_VIEW = "departmentForm";
    private static final String LIST_VIEW = "departmentList";
    private static final String LIST_MAP = "/department/list";
    private DepartmentDao departmentDao;
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentDao departmentDao, DepartmentService departmentService) {
        this.departmentDao = departmentDao;
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showAll(final Model model) {
        model.addAttribute("departmentList", departmentDao.getAll());
        model.addAttribute("headerTitle", "departments");
        return LIST_VIEW;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(final Model model, @PathVariable int id) {
        Department department = departmentDao.getById(id);
        String editDepartment = "edit_department";
        model.addAttribute("cancelUrl", LIST_MAP);
        model.addAttribute("department", department);
        model.addAttribute("headerTitle", editDepartment);
        return FORM_VIEW;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String saveOrUpdateDepartment(@PathVariable int id, final Department department) {
        departmentService.saveOrUpdateDepartment(department);
        return REDIRECT_TO_LIST;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addDepartment(final Model model) {
        String departmentNew = "new_department";
        model.addAttribute("department", new Department());
        model.addAttribute("headerTitle", departmentNew);
        return FORM_VIEW;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
        return REDIRECT_TO_LIST;
    }
}
