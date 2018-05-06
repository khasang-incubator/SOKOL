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
import io.khasang.sokol.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/department")
public class DepartmentController {
    private static final String REDIRECT_TO_LIST = "redirect:/sokol/department/list";
    private static final String DEPARTMENT_TYPE_LIST_HEADER_TITLE_LIST = "Департаменты";
    private static final String DEPARTMENT_TYPE_LIST_HEADER_TITLE_ADD = "Добавление департамента";
    private static final String DEPARTMENT_TYPE_LIST_HEADER_TITLE_EDIT = "Редактирование департамента";

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping({"/list"})
    public String departmentList(Model model) {
        List<Department> departmentList = departmentService.findAllByDeletedIsFalse();
        model.addAttribute("departmentList", departmentList);
        model.addAttribute("headerTitle", DEPARTMENT_TYPE_LIST_HEADER_TITLE_LIST);
        return "departmentList";
    }

    @GetMapping("/add")
    public String departmentForm(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("headerTitle", DEPARTMENT_TYPE_LIST_HEADER_TITLE_ADD);
        return "department";
    }

    @PostMapping("/add")
    public String departmentSubmit(@ModelAttribute Department department) {
        departmentService.saveDepartment(department);
        return REDIRECT_TO_LIST;
    }

    @GetMapping("/edit/{id}")
    public String departmentEdit(Model model, @PathVariable long id) {
        Department department = departmentService.findOne(id);
        model.addAttribute("department", department);
        model.addAttribute("headerTitle", DEPARTMENT_TYPE_LIST_HEADER_TITLE_EDIT);
        return "department";
    }

    @PostMapping("/edit/{id}")
    public String departmentPost(@PathVariable long id, Department departmentDetails) {
        Department department = departmentService.findOne(id);
        department.setTitle(departmentDetails.getTitle());
        department.setUpdatedDate(new Date());
        return "department";
    }

/*    @GetMapping("/delete/{id}")
    public String departmentDelete(@PathVariable long id) {
        departmentRepository.delete(id);
        return REDIRECT_TO_LIST;
    }*/

    @GetMapping("/delete/{id}")
    public String departmentDelete(@PathVariable long id) {
        departmentService.departmentDelete(id);
        return REDIRECT_TO_LIST;
    }
}
