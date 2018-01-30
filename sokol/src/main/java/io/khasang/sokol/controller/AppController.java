package io.khasang.sokol.controller;

import io.khasang.sokol.model.Department;
import io.khasang.sokol.repository.DepartmentRepository;
import io.khasang.sokol.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
@RequestMapping(value = "/sokol")
public class AppController {

    @Autowired
    private DepartmentService departmentService;

/*    @GetMapping("/save")
    public String save() {
        departmentRepository.save(new Department("buh5"));
        departmentRepository.save(new Department("buh6"));
        departmentRepository.save(new Department("buh7"));
        departmentRepository.save(new Department("buh8"));
        return "Done";
    }*/

    @RequestMapping({"/list"})
    public String departmentList(Model model) {
        List<Department> departmentList = departmentService.getAll();
        model.addAttribute("departmentList", departmentList);
        return "departmentList";
    }
}







