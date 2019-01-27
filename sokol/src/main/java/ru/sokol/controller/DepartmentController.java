package ru.sokol.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.sokol.dto.department.CreateDepartmentRequest;
import ru.sokol.dto.department.DepartmentDto;
import ru.sokol.dto.department.UpdateDepartmentRequest;
import ru.sokol.service.DepartmentService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/api/departments")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> result = departmentService.findAllDepartments();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/")
    public String Hello() {
        return "Hello";
    }

    @GetMapping("/api/departments/{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@Positive @PathVariable("id") Integer departmentId) {
        DepartmentDto result = departmentService.findDepartmentById(departmentId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/api/departments")
    public ResponseEntity<DepartmentDto> createDepartment(@Valid @NotNull @RequestBody CreateDepartmentRequest request) {
        DepartmentDto result = departmentService.createDepartment(request);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/api/departments")
    public ResponseEntity<DepartmentDto> updateDepartment(@Valid @NotNull @RequestBody UpdateDepartmentRequest request) {
        DepartmentDto result = departmentService.updateDepartment(request);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/api/departments/{id}")
    public ResponseEntity deleteDepartment(@Positive @PathVariable("id") Integer departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
