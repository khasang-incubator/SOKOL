/*
 * Copyright 2016-2018 Sokol Development Team
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

import io.khasang.sokol.model.Department;
import io.khasang.sokol.repository.DepartmentRepository;
import io.khasang.sokol.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/department")
public class DepartmentController {
    public static final String REDIRECTION_PATH = "/admin/department/list";
    private static final String REDIRECT_TO_LIST = "redirect:" + REDIRECTION_PATH;
    private static final String DEPARTMENT_TYPE_LIST_HEADER_TITLE_LIST = "Департаменты";
    private static final String DEPARTMENT_TYPE_LIST_HEADER_TITLE_ADD = "Добавление департамента";
    private static final String DEPARTMENT_TYPE_LIST_HEADER_TITLE_EDIT = "Редактирование департамента";

    private DepartmentService departmentService;
    private DepartmentRepository departmentRepository;


/*    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }*/

    @Autowired
    public DepartmentController(DepartmentService departmentService, DepartmentRepository departmentRepository) {
        this.departmentService = departmentService;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping({"/list"})
    public String departmentList(Model model) {
        List<Department> departmentList = departmentService.findAllByDeletedIsFalse();
        model.addAttribute("departmentList", departmentList);
        model.addAttribute("headerTitle", DEPARTMENT_TYPE_LIST_HEADER_TITLE_LIST);
        return "departmentList";
    }

    @GetMapping({"/list2"})
    public String departmentListPage(Model model) {
        Page<Department> pageDepartmentList = departmentRepository.findAllByDeletedIsFalse(new PageRequest(0, 10, new Sort(Sort.DEFAULT_DIRECTION, "title")));
        model.addAttribute("departmentList", pageDepartmentList.getContent());
        model.addAttribute("headerTitle", DEPARTMENT_TYPE_LIST_HEADER_TITLE_LIST);
        return "departmentList";
    }

    @GetMapping({"/list3"})
    public String departmentListPage3(Model model) {
        Page<Department> pageDepartmentList = departmentRepository.findAllByDeletedIsFalse(new PageRequest(0, 10, new Sort(Sort.DEFAULT_DIRECTION, "id")));
        final Sort sort = pageDepartmentList.getSort();
        model.addAttribute("departmentList", pageDepartmentList.getContent());
        model.addAttribute("headerTitle", DEPARTMENT_TYPE_LIST_HEADER_TITLE_LIST);
        return "departmentList";
    }



    @GetMapping("/add")
    public String departmentForm(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("headerTitle", DEPARTMENT_TYPE_LIST_HEADER_TITLE_ADD);
        return "departmentForm";
    }

    @PostMapping("/save")
    public String departmentSubmit(@ModelAttribute Department department) {
        departmentService.save(department);
        return REDIRECT_TO_LIST;
    }

    @GetMapping("/edit/{id}")
    public String departmentEdit(Model model, @PathVariable long id) {
        Department department = departmentService.findOne(id);
        model.addAttribute("department", department);
        model.addAttribute("headerTitle", DEPARTMENT_TYPE_LIST_HEADER_TITLE_EDIT);
        return "departmentForm";
    }

    @GetMapping("/delete/{id}")
    public String departmentDelete(@PathVariable long id) {
        departmentService.departmentDelete(id);
        return REDIRECT_TO_LIST;
    }
}
