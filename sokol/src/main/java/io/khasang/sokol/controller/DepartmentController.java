package io.khasang.sokol.controller;

import io.khasang.sokol.model.Department;
import io.khasang.sokol.repository.DepartmentRepository;
import io.khasang.sokol.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/sokol/department")
public class DepartmentController {
    private static final String REDIRECT_TO_LIST = "redirect:/sokol/department/list";

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping({"/list"})
    public String departmentList(Model model) {
        //List<Department> departmentList = departmentService.getAll();
        List<Department> departmentList = departmentRepository.findAll();
        model.addAttribute("departmentList", departmentList);
        return "departmentList";
    }

    @GetMapping("add")
    public String departmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "department";
    }

    @GetMapping("/edit/{id}")
    public String departmentEdit(Model model, @PathVariable long id) {
        Department department = departmentRepository.findOne(id);
        model.addAttribute("department", department);
        return "department";
    }

    @PostMapping("/edit/{id}")
    public String departmentEdit(@PathVariable long id, Department departmentDetails) {
        Department department = departmentRepository.getOne(id);
        department.setTitle(departmentDetails.getTitle());
        return "department";
    }

    @PostMapping("/add")
    public String departmentSubmit(@ModelAttribute Department department) {
        departmentRepository.save(department);
        return REDIRECT_TO_LIST;
    }

    @GetMapping("/delete/{id}")
    public String departmentDelete(@PathVariable long id) {
        departmentRepository.delete(id);
        return REDIRECT_TO_LIST;
    }

}
