package io.khasang.sokol.service;

import io.khasang.sokol.entity.Department;
import org.springframework.ui.Model;

public interface DepartmentService {
    void deleteDepartment(int id);
    void addDepartment(Model model);
    void showAllDepartment(Model model);
    void addOrEditDepartment(Model model, int id);
    void saveOrUpdateDepartment(int id, Department department);
}

