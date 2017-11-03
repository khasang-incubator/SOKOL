package io.khasang.sokol.service.impl;

import io.khasang.sokol.dao.DepartmentDao;
import io.khasang.sokol.entity.Department;
import io.khasang.sokol.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private static final String LIST_MAP = "/department/list";
    private DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public void deleteDepartment(int id) {
        Department updated = departmentDao.getById(id);
        updated.setUpdatedDate(new Date());
        updated.setDeleted(true);
        SecurityContext context = SecurityContextHolder.getContext();
        updated.setUpdatedBy(context.getAuthentication().getName());
        departmentDao.update(updated);
    }

    public void addDepartment(Model model) {
        model.addAttribute("department", new Department());
        String departmentNew = "new_department";
        model.addAttribute("headerTitle", departmentNew);
    }

    public void showAllDepartment(Model model) {
        model.addAttribute("departmentList", departmentDao.getAll());
        model.addAttribute("headerTitle", "departments");
    }

    public void addOrEditDepartment(Model model, int id) {
       Department department = departmentDao.getById(id);
        model.addAttribute("cancelUrl", LIST_MAP);
        model.addAttribute("department", department);
        String editDepartment = "edit_department";
        model.addAttribute("headerTitle", editDepartment);
    }

    public void saveOrUpdateDepartment(int id, Department department){
        if (id == 0) {
            SecurityContext context = SecurityContextHolder.getContext();
            department.setCreatedBy(context.getAuthentication().getName());
            department.setUpdatedBy(context.getAuthentication().getName());
            departmentDao.save(department);
        } else {
            Department updated = departmentDao.getById(id);
            updated.setTitle(department.getTitle());
            updated.setUpdatedDate(new Date());
            SecurityContext context = SecurityContextHolder.getContext();
            updated.setUpdatedBy(context.getAuthentication().getName());
            departmentDao.update(department);
        }
    }
}


