package io.khasang.sokol.service.Impl;

import io.khasang.sokol.model.Department;
import io.khasang.sokol.repository.DepartmentRepository;
import io.khasang.sokol.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }
}
