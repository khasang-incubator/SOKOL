package io.khasang.sokol.controller;

import io.khasang.sokol.model.Department;
import io.khasang.sokol.repository.DepartmentRepository;
import io.khasang.sokol.service.DepartmentService;
import io.khasang.sokol.service.RequestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/sokol/department")
public class DepartmentController {
    private static final String REDIRECT_TO_LIST = "redirect:/sokol/department/list";
    private static final String DEPARTMENT_TYPE_LIST_HEADER_TITLE_LIST = "Департаменты";
    private static final String DEPARTMENT_TYPE_LIST_HEADER_TITLE_ADD = "Добавление департамента";
    private static final String DEPARTMENT_TYPE_LIST_HEADER_TITLE_EDIT = "Редактирование департамента";

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping({"/list"})
    public String departmentList(Model model) {
        List<Department> departmentList = departmentRepository.findAllByIsDeletedIsFalse();
        //List<Department> departmentList = departmentRepository.findAll();
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
        departmentRepository.save(department);
        return REDIRECT_TO_LIST;
    }

    @GetMapping("/edit/{id}")
    public String departmentEdit(Model model, @PathVariable long id) {
        Department department = departmentRepository.findOne(id);
        model.addAttribute("department", department);
        model.addAttribute("headerTitle", DEPARTMENT_TYPE_LIST_HEADER_TITLE_EDIT);
        return "department";
    }

    @PostMapping("/edit/{id}")
    public String departmentPost(@PathVariable long id, Department departmentDetails) {
        Department department = departmentRepository.getOne(id);
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
