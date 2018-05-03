package io.khasang.sokol.service;

import io.khasang.sokol.model.Department;
import io.khasang.sokol.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public void departmentDelete(long id) {
        Department department = departmentRepository.getOne(id);
        department.setDeleted(true);
        department.setUpdatedDate(new Date());
        departmentRepository.save(department);
    }
}