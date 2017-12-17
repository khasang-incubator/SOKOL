package io.khasang.sokol.controller;


import io.khasang.sokol.model.Department;
import io.khasang.sokol.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/sokol")
public class AppController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/save")
    public String save() {
        departmentRepository.save(new Department("buh5"));
        departmentRepository.save(new Department("buh6"));
        departmentRepository.save(new Department("buh7"));
        return "Done";
    }

    @GetMapping("/findall")
    public String findall() {
        return departmentRepository.findAll().toString();
    }
}







