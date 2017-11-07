package io.khasang.sokol.service;

import io.khasang.sokol.entity.Department;
import org.springframework.ui.Model;

public interface DepartmentService {
    void deleteDepartment(int id);
    Department saveOrUpdateDepartment(Department department);
}

